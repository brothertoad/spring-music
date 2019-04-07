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
        Here are the artists:
        <ol>
        	<#list artists as artist>
        		<li><a href="artist?id=${artist.id?c}">${artist.name}</a></li>
        	</#list>
        </ol>
    </body>
</html>
