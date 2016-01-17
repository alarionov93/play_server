name := "play"

version := "1.0"

lazy val `play` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( javaJdbc ,  cache , javaWs , javaEbean,
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4" ,
  "javax.persistence" % "persistence-api" % "1.0.2"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  