// import Mill dependency
import mill._
import mill.scalalib._
import mill.scalalib.scalafmt.ScalafmtModule
import mill.scalalib.TestModule.Utest
// support BSP
import mill.bsp._

object Feng_3000 extends ScalaModule with ScalafmtModule { m =>
  override def scalaVersion = "2.13.12"
  override def scalacOptions = Seq(
    "-language:reflectiveCalls",
    "-deprecation",
    "-feature",
    "-Xcheckinit"
  )

  def sources = T.sources {
    super.sources() ++ Seq(
      PathRef(os.pwd / "rvdecoderdb" / "rvdecoderdb")
    )
  }

  val chiselVersion = "6.5.0"

  override def ivyDeps = Agg(
    ivy"org.chipsalliance::chisel:$chiselVersion"
  )

  override def scalacPluginIvyDeps = Agg(
    ivy"org.chipsalliance:::chisel-plugin:$chiselVersion"
  )

  def repositoriesTask = T.task {
    Seq(
      coursier.MavenRepository("http://mirrors.cloud.tencent.com/nexus/repository/maven-public"),
      coursier.MavenRepository(
        "https://repo.scala-sbt.org/scalasbt/maven-releases"
      )
    ) ++ super.repositoriesTask()
  }
}
