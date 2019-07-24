#!/bin/bash

URL="http://localhost:9902/artists"
if [ $# -ge 0 ]; then
    URL=$URL"/"$1
fi

curl -o - $URL --header "Accept: application/json" | python -m json.tool > artists.json
