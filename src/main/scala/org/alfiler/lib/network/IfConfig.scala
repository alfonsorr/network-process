package org.alfiler.lib.network

case class Interface(name:String, encap:String, hwAddr:Option[String], inetAddr:String, bcast:Option[String])

object IfConfig {
  def get:Map[String,String] = {
    import scala.sys.process._



    Process("ifconfig",None, "LC_ALL" -> "C").lineStream.foldLeft((Map.empty[String,String],"")){
      case ((prev,acum), line) =>
        if (!line.isEmpty){
          (prev,acum + line)
        } else {
          val algo = acum.takeWhile(!_.isWhitespace)
          (prev + (algo -> acum), "")
        }
    }._1
    }

  def parsed:List[Interface] = {
    get.map(t => {
      val line = t._2
      val encap = line.drop(line.indexOf("encap:")+ "encap:".length).takeWhile(!_.isWhitespace)
      val inetAddr = line.drop(line.indexOf("inet addr:")+ "inet addr:".length).takeWhile(!_.isWhitespace)
      Interface(t._1,encap,None,inetAddr,None)
    }).toList
  }
}
