package org.alfiler.lib.network.getip

import org.scalatest.{FlatSpecLike, Matchers}

class GetIpTest extends FlatSpecLike with Matchers{
  "GetIp" should "return all relations of ip and macs" in {
    println(GetIp.getAll())
  }
}
