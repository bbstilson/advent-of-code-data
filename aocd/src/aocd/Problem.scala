package aocd

abstract class Problem(
  year: Int,
  day: Int,
  api: Api = Api,
  assetsDir: os.Path = Problem.assetsDir
) {
  val problemDir = assetsDir / year.toString() / day.toString()
  val inputData = problemDir / "input.txt"

  if (!os.exists(problemDir)) os.makeDir.all(problemDir)

  if (!os.exists(inputData)) {
    val token = Problem.getTokenFrom(Problem.getTokenFile(assetsDir))
    os.write.over(inputData, api.getData(year, day, token).bytes)
  }

  def main(args: Array[String]): Unit = run(os.read.lines(inputData).toList)

  def run(input: List[String]): Unit

  final def part1[A](f: => A): A = time("part 1", f, withResult = true)
  final def part2[A](f: => A): A = time("part 2", f, withResult = true)

  final def time[A](prefix: String = "", block: => A, withResult: Boolean = false): A = {
    val start = System.nanoTime()
    val result = block
    val end = System.nanoTime()
    val (took, unit, color) = (end - start).toDouble / 1000000 match {
      case ms if ms > 1000 => ((ms / 1000).toString(), "s", Console.RED)
      case ms if ms > 100  => (ms.toString(), "ms", Console.YELLOW)
      case ms              => (ms.toString(), "ms", Console.GREEN)
    }
    val timed = s"$prefix: " + color + s"${"%-3.4s".format(took)}$unit" + Console.RESET
    if (withResult) {
      println(s"$timed - $result")
    } else {
      println(s"$timed")
    }
    result
  }

}

object Problem {
  val TOKEN_ENV = "AOC_SESSION_TOKEN"

  val assetsDir = os.home / ".aocd"

  def getTokenFile(dir: os.Path): os.Path = dir / "token"

  def getTokenFrom(tokenFile: os.Path, envvar: String = TOKEN_ENV): String =
    scala.sys.env.get(envvar).getOrElse {
      if (os.exists(tokenFile)) os.read(tokenFile).trim()
      else throw TokenNotFound()
    }
}
