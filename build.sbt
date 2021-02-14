name := "test-scala"

version := "0.1"

scalaVersion := "2.13.4"

resolvers += "https://jitpack.io" at "https://jitpack.io"

val AkkaVersion = "2.6.11"
val fs2Version = "2.5.0"
val http4sVersion = "0.21.15"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.1.1",
  "org.typelevel" %% "cats-effect" % "2.1.1",
  "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
  "com.github.Cloudmersive" % "Cloudmersive.APIClient.Java.RestTemplate" % "4.0.2",
  "com.github.Cloudmersive" % "Cloudmersive.APIClient.Java" % "3.62",
  "co.fs2" %% "fs2-core" % fs2Version, // For cats 2 and cats-effect 2,
  "co.fs2" %% "fs2-io" % fs2Version,
  "co.fs2" %% "fs2-reactive-streams" % fs2Version,
  "com.chuusai" %% "shapeless" % "2.3.3" withSources(),
  "org.http4s" %% "http4s-dsl" % http4sVersion withSources(),
  "org.http4s" %% "http4s-blaze-server" % http4sVersion withSources(),
  "org.http4s" %% "http4s-blaze-client" % http4sVersion withSources()
)
