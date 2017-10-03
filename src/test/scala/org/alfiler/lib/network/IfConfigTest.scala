package org.alfiler.lib.network

import org.scalatest.{FlatSpecLike, Matchers}

class IfConfigTest extends FlatSpecLike with Matchers{
  "IfConfig" should "retreive the string of the command" in {
    val result = IfConfig.get

    result.size should be (4)
    result.keySet should be (Set("docker0","enp2s0", "lo", "wlp3s0"))
    result.values.foreach(println)
  }
  it should "retreive the string of the command parsed" in {
    val result = IfConfig.parsed

    result.size should be (4)
    result.map(_.name) should be (List("docker0","enp2s0", "lo", "wlp3s0"))
    result.foreach(println)
  }
}
