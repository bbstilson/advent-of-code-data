# Advent of Code Data

![Important Badge][badge] ![Version][version]

[badge]: https://cdn.rawgit.com/nikku/works-on-my-machine/v0.2.0/badge.svg
[version]: https://img.shields.io/static/v1?label=version&message=0.1.0&color=blue

Scala version of the [python library](https://github.com/wimglenn/advent-of-code-data) by the same name.

Requires [Mill](https://github.com/lihaoyi/mill/) 0.9.3.

## Usage

I'm too lazy to publish this on maven, so you will need to publish it locally:

```bash
git clone https://github.com/bbstilson/advent-of-code-data.git
cd advent-of-code-data
mill aocd.publishLocal
```

It's ready to use!

Add it to your library deps:

```scala
ivy"io.github.bbstilson::aocd:version" // mill
"io.github.bbstilson" %% "aocd" % "version" // sbt
```

Export your session token, which can be grabbed from your cookies after authenticating on the Advent of Code site.

```bash
export AOC_SESSION_TOKEN="whateveritis"
```

Now go solve the problem!

```scala
import aocd.Problem

object Day1 extends Problem(2020, 1) {
  def run(input: List[String]): Unit = {
    // you can do it!
  }
}
```

## Behind the Scenes

Your data is downloaded to `~/.aocd/<YYYY>/<DD>/input.txt`. The download is only done once, subsequent runs will just read the data from that directory.
