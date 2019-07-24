select distinct album.id, album.name, album.sortName, album.numTracks, artist.name from albums album, artists artist
  where album.artist = 24616 and artist.id = album.artist and exists(select * from songs where album = album.id and state = 0)
  order by album.sortName asc;
