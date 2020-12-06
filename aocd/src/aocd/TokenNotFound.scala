package aocd

case class TokenNotFound() extends Exception(TokenNotFound.msg) {}

object TokenNotFound {

  val msg =
    "You must provide your Advent of Code session token as environment variable as AOC_SESSION_TOKEN or in ~/.aocd/token"

}
