package org.alfiler.lib.network.ifconfig



case class Interface(name:String, encap:String, hwAddr:Option[String], inetAddr:String, bcast:Option[String])