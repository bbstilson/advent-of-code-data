package aocd

abstract class Problem(year: Int, day: Int) {

  val token = Option(System.getenv().get("AOC_SESSION_TOKEN")).getOrElse {
    throw new RuntimeException(
      "You must export your Advent of Code session token: AOC_SESSION_TOKEN"
    )
  }
  val problemDir = os.home / ".aocd" / year.toString() / day.toString()
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
