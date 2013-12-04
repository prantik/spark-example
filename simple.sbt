name := "Simple Project"

version := "1.0"

scalaVersion := "2.9.3"

libraryDependencies += "org.apache.spark" %% "spark-core" % "0.8.0-incubating"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "0.8.0-incubating"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "0.8.0-incubating"

sbtPlugin := true

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

scalariformSettings
