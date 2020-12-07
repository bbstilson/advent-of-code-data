#!/usr/bin/env bash

set -eux

rm -rf out/

mill mill.scalalib.PublishModule/publishAll \
    __.publishArtifacts \
    --gpgArgs --passphrase=$GPG_PASSWORD,--batch,--yes,-a,-b \
    --sonatypeCreds bbstilson:$SONATYPE_PASSWORD \
    --readTimeout 600000 \
    --release true \
    --signed true
