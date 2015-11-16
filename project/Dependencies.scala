import sbt._

object Dependencies {

  def akka(artifact: String) = "com.typesafe.akka" %%  s"akka-$artifact" % "2.3.14"

  val shapeless = "com.chuusai" %%  "shapeless" % "2.2.3"
  val typesafeConfig = "com.typesafe" % "config" % "1.3.0"
  val akkaRabbitMQ = "com.thenewmotion.akka" %% "akka-rabbitmq" % "1.2.7"
  val slf4j = "org.slf4j" % "slf4j-api" % "1.7.12"
  val logback = "ch.qos.logback" % "logback-classic" % "1.1.3"
  val scalatest = "org.scalatest" %% "scalatest" % "2.2.1"
  val scopedFixtures = "com.spingo" %% "scoped-fixtures" % "1.0.0"


  val commonTestDependencies = Seq(
    logback % "test",
    scalatest % "test",
    scopedFixtures % "test",
    akka("testkit") % "test",
    akka("slf4j") % "test"
  )

}
