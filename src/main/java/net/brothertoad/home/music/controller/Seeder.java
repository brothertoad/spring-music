package net.brothertoad.home.music.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.brothertoad.home.music.bean.Album;
import net.brothertoad.home.music.bean.Artist;
import net.brothertoad.home.music.bean.Song;

@RestController
public class Seeder {
	
	private static final Logger logger = LoggerFactory.getLogger(Seeder.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private Map<String,Artist> artistMap = new HashMap<>();
	private Map<String,Album>  albumMap = new HashMap<>();
	private Map<String,Song>   songMap = new HashMap<>();
	
	@PostMapping("/seed")
	public String seed(@RequestBody List<Map<String,String>> entries) {
		if (entries == null) {
			return "Error\n";
		}
		clear();
		createArtistMap(entries);
		createAlbumMap(entries);
		createSongMap(entries);
		return "List has " + entries.size() + " entries\n";
	}
	
	private void createArtistMap(List<Map<String,String>> entries) {
		populateArtistMap(entries);
		// Now, read back the ID's and update the values in artistMap.
		jdbcTemplate.query("select id, name from artists", new Object[] {},
				(rs) -> {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					Artist artist = artistMap.get(name);
					if (artist != null) {
						artist.setId(id);
					}
					else {
						logger.error("Did not find artist for " + name);
					}
				});
	}
	
	@Transactional
	private void populateArtistMap(List<Map<String,String>> entries) {
		for (Map<String,String> map : entries) {
			String artistName = map.get("ARTIST");
			if (artistName == null) {
				logger.error("Couldn't find value for key ARTIST");
				continue; // shouldn't happen
			}
			Artist artist = artistMap.get(artistName);
			if (artist == null) {
				artist = new Artist(artistName);
				int n = jdbcTemplate.update("insert into artists (name, sortName) values (?, ?)", artist.getName(), artist.getSortName());
				if (n != 1) {
					logger.error("Couldn't add artist '" + artistName + "'");
					continue;
				}
				artistMap.put(artistName, artist);
			}
		}
		logger.info("found " + artistMap.size() + " artists");
	}
	
	private void createAlbumMap(List<Map<String,String>> entries) {
		populateAlbumMap(entries);
		// Now, read back the ID's and update the values in albumMap.
		jdbcTemplate.query("select albums.id, albums.name, artists.name from albums join artists " +
				"on albums.artist = artists.id", new Object[] {},
				(rs) -> {
					int id = rs.getInt(1);
					String albumName = rs.getString(2);
					String artistName = rs.getString(3);
					String albumKey = artistName + "-" + albumName;
					Album album = albumMap.get(albumKey);
					if (album != null) {
						album.setId(id);
					}
					else {
						logger.error("Did not find album for " + albumKey);
					}
				});
	}
	
	@Transactional
	private void populateAlbumMap(List<Map<String,String>> entries) {
		for (Map<String,String> map : entries) {
			String artistName = map.get("ARTIST");
			String albumName = map.get("ALBUM");
			String s = map.get("TRACK_TOTAL");
			int numTracks = s == null ? 0 : Integer.parseInt(s);
			if (artistName == null || albumName == null) {
				logger.error("Couldn't find value for either ARTIST or ALBUM");
				continue; // shouldn't happen
			}
			Artist artist = artistMap.get(artistName);
			if (artist == null) {
				logger.error("Couldn't find artist '" + artistName + "' in map");
				continue;
			}
			String albumKey = artistName + "-" + albumName;
			Album album = albumMap.get(albumKey);
			if (album == null) {
				album = new Album(albumName);
				album.setArtist(artist);
				album.setNumTracks(numTracks);
				int n = jdbcTemplate.update("insert into albums (artist, name, sortName, numTracks) values (?, ?, ?, ?)",
						artist.getId(), album.getName(), album.getSortName(), numTracks);
				if (n != 1) {
					logger.error("Couldn't add album '" + albumKey + "'");
					continue;
				}
				albumMap.put(albumKey, album);
			}
		}
		logger.info("found " + albumMap.size() + " albums");
	}
	
	private void createSongMap(List<Map<String,String>> entries) {
		populateSongMap(entries);
	}
	
	@Transactional
	private void populateSongMap(List<Map<String,String>> entries) {
		for (Map<String,String> map : entries) {
			String artistName = map.get("ARTIST");
			String albumName = map.get("ALBUM");
			String title = map.get("TITLE");
			if (title.length() > 250) {
				logger.error("Can't add song with title longer than 250 characters " + title.length());
				logger.error("Title is " + title);
				continue;
			}
			String s = map.get("TRACK");
			int trackNum = s == null ? 0 : Integer.parseInt(s);
			if (artistName == null || albumName == null) {
				logger.error("Couldn't find value for either ARTIST or ALBUM");
				continue; // shouldn't happen
			}
			Artist artist = artistMap.get(artistName);
			if (artist == null) {
				logger.error("Couldn't find artist '" + artistName + "' in map");
				continue;
			}
			String albumKey = artistName + "-" + albumName;
			Album album = albumMap.get(albumKey);
			if (album == null) {
				logger.error("Couldn't find album '" + albumKey + "' in map");
				continue;
			}
			Song song = new Song();
			song.setTitle(title);
			song.setTrackNum(trackNum);
			song.setAlbum(album);
			int n = jdbcTemplate.update("insert into songs (album, title, sortTitle, trackNum, ripped, discarded) " +
					"values (?, ?, ?, ?, ?, ?)",
					album.getId(), song.getTitle(), song.getSortTitle(), trackNum, false, false);
			if (n != 1) {
				logger.error("Couldn't add song '" + title + "'");
				continue;
			}
			String songKey = albumKey + "-" + title;
			songMap.put(songKey, song);
		}
		logger.info("found " + songMap.size() + " songs");
	}
	
	@Transactional
	private void clear() {
		jdbcTemplate.execute("delete from artists");
		artistMap.clear();
		albumMap.clear();
		songMap.clear();
	}

}
