import spray.boilerplate.BoilerplatePlugin.Boilerplate

import Dependencies._

scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt")

name := "op-rabbit-core"

libraryDependencies ++= Seq(
  shapeless,
  typesafeConfig,
  akka("actor"),
  akkaRabbitMQ,
  slf4j
)
libraryDependencies ++= Dependencies.commonTestDependencies


Boilerplate.settings
