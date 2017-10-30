name := "network-process"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= {
  val monocleVersion = "1.4.0"
  Seq(
    "org.typelevel" %% "cats-core" % "1.0.0-MF",
    "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion,
    "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion,
    "com.chuusai" %% "shapeless" % "2.3.2",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
}