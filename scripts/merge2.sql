select songs2.title, songs2.sorttitle, songs2.album, songs2.sortalbum, songs2.tracknum, songs2.numtracks,
  artists.name as artist, artists.sortname as sortartist,
  songs2.state
  into table songs3
  from songs2 inner join artists on songs2.artist = artists.id;
