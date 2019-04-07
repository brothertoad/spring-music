<!DOCTYPE html>
<html>
<head>
  <title>Music Library</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body>
  <a href="index">Back to library</a>
  <table>
    <#list songs as song>
    <tr><td>${song.artist}</td><td>${song.album}</td><td>${song.title}</td></tr>
    </#list>
  </table>
</body>
</html>
