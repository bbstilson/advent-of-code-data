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
