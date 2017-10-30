package org.alfiler.lib.network.ifconfig

import scala.util.Try

object IfConfig {
  def get: Stream[String] = {
    import scala.sys.process._

    Process("ifconfig", None, "LC_ALL" -> "C").lineStream
      .scanLeft((None: Option[String], "")) {
        case ((prev, acum), line) =>
          if (!line.isEmpty) {
            (None, acum + line)
          } else {
            (Some(acum), "")
          }
      }
      .flatMap(_._1)
  }

  def parsed: Stream[Interface] = {
    get.map(line => {
      val name = line.takeWhile(!_.isWhitespace)
      val encap = line
        .drop(line.indexOf("encap:") + "encap:".length)
        .takeWhile(!_.isWhitespace)
      val inetAddr = line
        .drop(line.indexOf("inet addr:") + "inet addr:".length)
        .takeWhile(!_.isWhitespace)
      val hwAddr = Try(
        line
          .drop(line.indexOf("HWaddr ") + "HWaddr ".length)
          .takeWhile(!_.isWhitespace)).toOption.filter(a =>
        a.nonEmpty && a.length >= 7)
      val bcast = Try(
        line
          .drop(line.indexOf("Bcast:") + "Bcast:".length)
          .takeWhile(!_.isWhitespace)).toOption.filter(a =>
        a.nonEmpty && a.length >= 7)
      Interface(name, encap, hwAddr, inetAddr, bcast)
    })
  }
}
