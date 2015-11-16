
name := "op-rabbit-akka-stream"

scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt")

libraryDependencies ++= Seq(
  "com.timcharper"    %% "acked-stream" % "1.0-RC1",
  "com.typesafe.akka" %% "akka-stream-experimental" % "2.0-M1"
)

