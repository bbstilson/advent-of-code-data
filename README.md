# Advent of Code Data

![Important Badge][badge] ![Maven][maven]

[badge]: https://img.shields.io/badge/works-on%20my%20machine-success?style=for-the-badge
[maven]: https://img.shields.io/maven-central/v/io.github.bbstilson/aocd_2.13?color=blue&style=for-the-badge

Scala version of the [python library](https://github.com/wimglenn/advent-of-code-data) by the same name.

## Usage

```scala
ivy"io.github.bbstilson::aocd:0.1.2" // mill
"io.github.bbstilson" %% "aocd" % "0.1.2" // sbt
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

## Publishing

### Locally

Ensure you have at least [Mill](https://github.com/lihaoyi/mill/) 0.9.3.

```bash
git clone https://github.com/bbstilson/advent-of-code-data.git
cd advent-of-code-data
mill aocd.publishLocal
```

This is useful if you want to make edits and test them out.

### Maven

1) Update the `publishVersion` in `build.sc` (and in this readme)
2) Run `./bin/release.sh`
