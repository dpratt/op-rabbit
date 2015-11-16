
val assertNoApplicationConf = taskKey[Unit]("Makes sure application.conf isn't packaged")

organization in ThisBuild  := "io.dpratt"
scalaVersion in ThisBuild := "2.11.7"

scalacOptions in ThisBuild ++= List("-target:jvm-1.6")
crossScalaVersions in ThisBuild := Seq("2.11.7", "2.10.5")

resolvers in ThisBuild ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "SpinGo OSS" at "http://spingo-oss.s3.amazonaws.com/repositories/releases",
  "Sonatype Releases"  at "http://oss.sonatype.org/content/repositories/releases"
)


val commonSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val repo = if (version.value.trim.endsWith("SNAPSHOT")) "snapshots" else "releases"
    Some(repo at s"s3://spingo-oss/repositories/$repo")
  }
)

def rabbitProject(id: String, loc: String) = Project(id, file(loc)).settings(commonSettings: _*)

lazy val `op-rabbit` = rabbitProject("op-rabbit", ".")
  .settings(commonSettings: _*)
  .settings(unidocSettings: _*)
  .settings(
    description := "The opinionated Rabbit-MQ plugin",
    name := "op-rabbit")
  .dependsOn(core)
  .aggregate(core, `play-json`, airbrake, `akka-stream`, json4s, `spray-json`)


lazy val core = rabbitProject("core", "./core")

lazy val json4s = rabbitProject("json4s", "./addons/json4s")
  .dependsOn(core)

lazy val `play-json` = rabbitProject("play-json", "./addons/play-json")
  .dependsOn(core)

lazy val `spray-json` = rabbitProject("spray-json", "./addons/spray-json")
  .dependsOn(core)

lazy val airbrake = rabbitProject("airbrake", "./addons/airbrake/")
  .dependsOn(core)

lazy val `akka-stream` = rabbitProject("akka-stream", "./addons/akka-stream")
  .dependsOn(core % "test->test;compile->compile")
