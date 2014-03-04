// Scalariform to autoformat code
addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.2.1")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.10.2")


resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

resolvers += Resolver.url("sbt-assembly-resolver-0", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
