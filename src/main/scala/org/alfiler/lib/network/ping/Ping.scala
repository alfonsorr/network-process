package org.alfiler.lib.network.ping

import scala.sys.process.Process
import scala.util.Try

object Ping {

  def doPing(ipAdress: String, c:Int = 1, w:Int = 1, broadcast:Boolean = false): Unit = {
    Try {
      val b = if (broadcast) "-b " else ""
      val bashcommand = s"ping $ipAdress -c $c -w $w $b"
      println(bashcommand)
      Process(bashcommand, None, "LC_ALL" -> "C").lineStream.foreach(a => ())
    }.recover{case _:RuntimeException => Unit}
  }
}
