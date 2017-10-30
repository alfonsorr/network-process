package org.alfiler.lib.network.getip

import org.alfiler.lib.network.arp.Arp
import org.alfiler.lib.network.ifconfig.IfConfig
import org.alfiler.lib.network.ping.Ping

object GetIp {
  private def pingAll() = {
    IfConfig.parsed.flatMap(_.bcast).filter(_ != "0.0.0.0")
      .foreach(Ping.doPing(_, broadcast = true))
  }

  def get(mac:String):Option[String] = {
      pingAll()
    Arp.doArp().find(_.hwAddrs == mac).map(_.ip)
  }

  def getAll():Map[String,String] = {
    pingAll()
    Arp.doArp().flatMap(n => n.hwAddrs.map(_ -> n.ip)).toMap
  }
}
