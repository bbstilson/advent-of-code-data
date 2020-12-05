package aocd

abstract class Problem(year: Int, day: Int) {
  val assetsDir = os.home / ".aocd"
  val tokenFile = assetsDir / "token"
  val token: String =
    Option(System.getenv().get("AOC_SESSION_TOKEN")).getOrElse {
      if (os.exists(tokenFile)) os.read(tokenFile).strip()
      else throw new RuntimeException(
        "You must provide your Advent of Code session token as environment variable as AOC_SESSION_TOKEN or in ~/.aocd/token"
      )
    }
  val problemDir = assetsDir / year.toString() / day.toString()
  val inputData = problemDir / "input.txt"
  if (!os.exists(problemDir)) os.makeDir.all(problemDir)
  if (!os.exists(inputData)) {
    val r = requests.get(
      Problem.url(year, day) + "/input",
      cookies = Map("session" -> new java.net.HttpCookie("session", token)),
      headers = Map("User-Agent" -> Problem.USER_AGENT)
    )
    os.write(inputData, r.bytes)
  }

  def run(input: List[String]): Unit

  def main(args: Array[String]): Unit = {
    run(os.read.lines(inputData).toList)
  }
}

object Problem {
  def url(y: Int, d: Int) = s"https://adventofcode.com/$y/day/$d"
  val USER_AGENT = "advent-of-code-data-scala v1"
}
