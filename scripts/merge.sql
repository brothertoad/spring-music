select songs.id, songs.title, songs.sorttitle, songs.tracknum, songs.state,
  albums.artist as artistid, albums.id as albumid, albums.name as album, albums.sortname as sortalbum, albums.numtracks
  into table songs2
  from songs inner join albums on songs.album = albums.id;

select songs2.id, songs2.title, songs2.sorttitle, songs2.albumid, songs2.album, songs2.sortalbum, songs2.tracknum, songs2.numtracks,
  songs2.artistid, artists.name as artist, artists.sortname as sortartist,
  songs2.state
  into table msongs
  from songs2 inner join artists on songs2.artistid = artists.id;

drop table songs2;
