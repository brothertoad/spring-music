#!/bin/bash

if [ $# -eq 0 ]; then
    echo Need at least an artist Id.
    exit
fi
ARTIST=$1

URL="http://localhost:9902/albums/$ARTIST"
if [ $# -gt 1 ]; then
    URL=$URL"/"$2
fi

curl -o - $URL --header "Accept: application/json" | python -m json.tool > albums.json
