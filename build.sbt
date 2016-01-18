name := "play"

version := "1.0"

lazy val `play` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc ,  cache , javaWs , javaEbean,
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4" ,
  "javax.persistence" % "persistence-api" % "1.0.2" ,
  "org.avaje" % "ebean" % "2.8.1" ,
  "com.fasterxml.jackson.core" % "jackson-core" % "2.7.0"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  