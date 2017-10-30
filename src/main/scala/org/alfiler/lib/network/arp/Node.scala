package org.alfiler.lib.network.arp

import monocle.{Lens, Optional}
import monocle.macros.GenLens


object Node {
  val empty = Node(None,"",None,None,"")
  val name: Optional[Node, String] = Optional[Node, String](a => a.name)(name => {val toPut = if (name == "?") None else Some(name)
    node => node.copy(name = toPut)
  })
  val ip: Lens[Node, String] = GenLens[Node](_.ip)
  val interface: Lens[Node, String] = GenLens[Node](_.interface)
}

case class Node(name:Option[String], ip:String, hwAddrs:Option[String], hwType:Option[String], interface:String)
