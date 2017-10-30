package org.alfiler.lib.network.ifconfig

import org.scalatest.{FlatSpecLike, Matchers}

class IfConfigTest extends FlatSpecLike with Matchers{


  "IfConfig" should "retreive the string of the command parsed" in {
    val result = IfConfig.parsed

    result.size should be >= 4
    //result.map(_.name) should contain (List("docker0","enp2s0", "lo", "wlp3s0"))
    //result.withFilter(a => Set("docker0", "wlp3s0").contains(a.name)).foreach(a => a should matchPattern{case Interface(_,_,Some(_),_,Some(_)) =>})
    //result.withFilter(a => Set("enp2s0").contains(a.name)).foreach(_.hwAddr should not matchPattern{case Interface(_,_,Some(_),_,None) =>})
    //result.withFilter(a => Set("lo").contains(a.name)).foreach(_.hwAddr should not matchPattern{case Interface(_,_,Some(_),_,None) =>})
    result.foreach(println)
  }
}
