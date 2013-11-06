//import sbt._
//import Keys._
//import sbtassembly.Plugin._
//import AssemblyKeys._
//import sbt.Package.ManifestAttributes
//
//object CourseraMLBaseBuild extends Build {
//
//  // The root project that aggregates everything else
//  lazy val coursera_mlbase = Project(
//    id = "coursera-mlbase",
//    base = file(".")
//  )/*.settings(scalaVersion := 2.9.3)*/ aggregate(common, ex2)
//
//  /*.settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)*/
//
//  // common utilities
//  lazy val common = Project(
//    id = "common",
//    base = file("common"),
//    settings = baseSettings ++ Seq(
//      libraryDependencies ++= commonLibraryDependencies
//    )
//  )/*.settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)*/
//
//  // week 2 assignment
//  lazy val ex2 = Project(
//    id = "ex2",
//    base = file("ex2"),
//    settings = baseSettings ++ Seq(
//      libraryDependencies ++= (ex2LibraryDependencies ++ commonLibraryDependencies)
//    )
//  ).dependsOn(common)/*.settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)*/
//
//  val commonLibraryDependencies = Seq(
//    "me.org.apache.spark" % "spark-core_2.9.3" % "0.8.1-incubating-SNAPSHOT",
//    "me.org.apache.spark" % "spark-mllib_2.9.3" % "0.8.1-incubating-SNAPSHOT",
////    "org.scalatest" %% "scalatest" % "1.9.1" % "test",
//    "me.edu.berkeley.cs.amplab" % "mli_2.9.3" % "1.0-SNAPSHOT"
//  )
//
//  val ex2LibraryDependencies = Seq(
//
//  )
//
//  lazy val baseSettings = Defaults.defaultSettings ++ assemblySettings ++ Seq(
//    resolvers := commonResolvers,
//    retrieveManaged := true,
//    parallelExecution in Test := false,
//    test in assembly := {},
//    shellPrompt := ShellPrompt.buildShellPrompt,
//    //organization := "com.quantifind",
//    //publishTo := Some("snapshots" at qf + "ext-snapshots"),
//    //mergeStrategy in assembly := quantiMerge _,
//    packageOptions in assembly := manifestProps
//  )
//
//  val commonResolvers = Seq(
//    "sbt-idea-repo" at "http://mpeltonen.github.com/maven/",
//    "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
//  )
//
//  object ShellPrompt {
//
//    object devnull extends ProcessLogger {
//      def info(s: => String) {}
//
//      def error(s: => String) {}
//
//      def buffer[T](f: => T): T = f
//    }
//
//    def currBranch = (
//      ("git status -sb" lines_! devnull headOption)
//        getOrElse "-" stripPrefix "## "
//      )
//
//    val buildShellPrompt = {
//      (state: State) => {
//        val currProject = Project.extract(state).currentProject.id
//        "%s:%s> ".format(
//          currProject, currBranch
//        )
//      }
//    }
//  }
//
//  lazy val manifestProps = Seq(ManifestAttributes(
//    // Only the implementation properties are accessible from Java
//    ("Implementation-Version", gitDescr),
//    ("Specification-Version", gitStatus),
//    ("git-sha", gitSha),
//    ("git-sha-clean", gitShaClean),
//    ("git-status", gitStatus),
//    ("git-descr", gitDescr),
//    ("user", userName),
//    ("hostname", hostName)))
//
//// make sure we get multiline and newline cleaned up
//  def trimLine(line: String) = {
//    val l = line.replaceAll("\n", " ").trim; l.substring(0, Math.min(l.length, 45))
//  }
//
//  lazy val gitSha = trimLine("git rev-parse --short HEAD" !!)
//  lazy val gitDiffTmp = trimLine("git diff --shortstat" !!)
//  lazy val gitStatus = gitDiffTmp + (if (gitDiffTmp.size == 0) {
//    "clean"
//  } else {
//    ""
//  })
//  lazy val gitDescr = trimLine("git describe --tags --always" !!) + (if (gitDiffTmp.size > 0) {
//    "-dirty"
//  } else {
//    ""
//  })
//  //emulating --dirty as it is only in git >1.7
//  lazy val gitShaClean = gitSha + (if (gitDiffTmp.size == 0) {
//    ""
//  } else {
//    "-dirty"
//  })
//
//  lazy val userName = trimLine("whoami" !!)
//  lazy val hostName = trimLine("hostname" !!)
//}
