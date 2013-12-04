// Scalariform to autoformat code
addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.0.1")


resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

resolvers += Resolver.url("sbt-assembly-resolver-0", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
