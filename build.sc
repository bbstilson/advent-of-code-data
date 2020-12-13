import $ivy.`io.github.davidgregory084::mill-tpolecat:0.2.0`

import mill._
import mill.scalalib._
import mill.scalalib.publish._
import mill.scalalib.scalafmt._
import io.github.davidgregory084.TpolecatModule

object aocd extends ScalaModule with PublishModule with TpolecatModule with ScalafmtModule {
  def scalaVersion = "2.13.4"

  def publishVersion = "0.1.3"

  def pomSettings = PomSettings(
    description = "Advent of Code Data",
    organization = "io.github.bbstilson",
    url = "https://github.com/bbstilson/advent-of-code-data",
    licenses = Seq(License.MIT),
    versionControl = VersionControl.github("bbstilson", "advent-of-code-data"),
    developers = Seq(
      Developer("bbstilson", "Brandon Stilson", "https://github.com/bbstilson")
    )
  )

  def ivyDeps = Agg(
    ivy"com.lihaoyi::requests:0.6.5",
    ivy"com.lihaoyi::os-lib:0.7.1"
  )

  object test extends Tests {
    def forkEnv = Map("AOC_SESSION_TOKEN" -> "foobar")

    def ivyDeps = Agg(
      ivy"com.lihaoyi::utest:0.7.2",
      ivy"org.mockito::mockito-scala:1.16.3"
    )
    def testFrameworks = Seq("utest.runner.Framework")
  }
}
