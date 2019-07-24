select distinct album.id, album.name, album.sortName, album.numTracks from albums album
  where album.artist = 24616 and exists(select * from songs where album = album.id and state = 0)
  order by album.sortName asc;
