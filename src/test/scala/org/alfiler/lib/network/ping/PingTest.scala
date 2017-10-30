package org.alfiler.lib.network.ping

import org.scalatest.{FlatSpecLike, Matchers}

import scala.concurrent.duration.Duration

class PingTest  extends FlatSpecLike with Matchers{

  "Ping" should "do ping" in {
    Ping.doPing("127.0.0.1")
  }

  it should "do ping with broadcast " in {
    Ping.doPing("192.168.42.255", broadcast = true)
  }

}
