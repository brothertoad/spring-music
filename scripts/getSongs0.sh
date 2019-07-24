#!/bin/bash

curl -o - http://localhost:9902/songs/state?state=0 --header "Accept: application/json" | python -m json.tool > songs0.json
