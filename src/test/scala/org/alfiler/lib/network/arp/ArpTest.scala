package org.alfiler.lib.network.arp

import org.scalatest.{FlatSpecLike, Matchers}


class ArpTest extends FlatSpecLike with Matchers{
  "Arp command" should "parse the lines and retreave them" in {
    Arp.doArp().foreach(_ => ())
  }
}
