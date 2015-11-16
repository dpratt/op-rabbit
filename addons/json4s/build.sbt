import Dependencies._

name := "op-rabbit-json4s"

val json4sVersion = "3.2.11"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-ast"     % json4sVersion,
  "org.json4s" %% "json4s-core"    % json4sVersion,
  "org.json4s" %% "json4s-jackson" % json4sVersion % "provided",
  "org.json4s" %% "json4s-native"  % json4sVersion % "provided"
)
libraryDependencies ++= Dependencies.commonTestDependencies
