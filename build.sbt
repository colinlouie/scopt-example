name := "scopt-example"

version := "0.1"

scalaVersion := "2.12.10"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

/** ------------------------- */
/** Required for application. */
/** ------------------------- */

// https://mvnrepository.com/artifact/com.github.scopt/scopt
libraryDependencies += "com.github.scopt" %% "scopt" % "4.0.0"

/** --------------------- */
/** Required for testing. */
/** --------------------- */

// Forking the JVM is required to pick up the custom configuration.
fork in Test := true
