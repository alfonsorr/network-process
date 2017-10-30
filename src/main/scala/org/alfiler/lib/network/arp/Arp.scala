package org.alfiler.lib.network.arp

import scala.sys.process.Process

object Arp {
  import Node._

  private def parseLineToNodeInfo(line:String):Node = {
    val prevAt :: postAt :: Nil = line.split(" at ").toList
    val prevOn :: postOn :: Nil = postAt.split(" on ").toList
    val node1 = prevAt.split(" ").zipWithIndex.foldLeft(Node.empty){
      case (prev, (lineSplitted, n)) => n match {
        case 0 => name.set(lineSplitted)(prev)
        case 1 => ip.set(lineSplitted.substring(1,lineSplitted.length-1))(prev)
      }
    }

    val node2 = if (!prevOn.startsWith("<")) {
      val hwAddrs :: hwType :: Nil = prevOn.split(" ").toList
      node1.copy(hwAddrs = Some(hwAddrs), hwType = Some(hwType.substring(1,hwType.length-1)))
    } else {
      node1
    }
    node2.copy(interface = postOn)
  }

  def doArp():Stream[Node] = {
    Process(s"arp -a",None, "LC_ALL" -> "C").lineStream.map(parseLineToNodeInfo)
  }
}
