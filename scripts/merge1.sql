select songs.title, songs.sorttitle, songs.tracknum, songs.state,
  albums.artist, albums.name as album, albums.sortname as sortalbum, albums.numtracks
  into table songs2
  from songs inner join albums on songs.album = albums.id;
