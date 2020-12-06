# Advent of Code Data

![Important Badge][badge] ![Version][version]

[badge]: https://cdn.rawgit.com/nikku/works-on-my-machine/v0.2.0/badge.svg
[version]: https://img.shields.io/static/v1?label=version&message=0.1.1&color=blue

Scala version of the [python library](https://github.com/wimglenn/advent-of-code-data) by the same name.

Requires [Mill](https://github.com/lihaoyi/mill/) 0.9.3.

## Usage

I haven't publish on maven yet, so it will need to be published locally:

```bash
git clone https://github.com/bbstilson/advent-of-code-data.git
cd advent-of-code-data
mill aocd.publishLocal
```

It's ready to use!

Add it to your library deps:

```scala
ivy"io.github.bbstilson::aocd:0.1.1" // mill
"io.github.bbstilson" %% "aocd" % "0.1.1" // sbt
```

Next, export your session token or add it to `~/.aocd/token`.

```bash
export AOC_SESSION_TOKEN="yoursessionstoken"
```

or

```bash
mkdir -p ~/.aocd
echo "yoursessionstoken" > ~/.aocd/token
```

Your token can be be found in your browser cookies after authenticating on the Advent of Code site.

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
