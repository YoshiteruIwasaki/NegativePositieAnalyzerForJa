import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "NegativePositieAnalyzerForJa"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    javaJpa,
    "org.twitter4j" % "twitter4j-core" % "3.0.3",
    "mysql" % "mysql-connector-java" % "5.1.26",
    "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final",
    "rome" % "rome" % "1.0",
    "jdom" % "jdom" % "1.0")

  val common = play.Project(appName + "-common", appVersion, appDependencies, path = file("modules/common"))

  lazy val akb = play.Project(appName + "-akb", appVersion, appDependencies, path = file("modules/akb")).dependsOn(common)

  lazy val main = play.Project(appName, appVersion, appDependencies).settings( // Add your own project settings here
  ).dependsOn(common, akb).aggregate(common, akb)

}
