package net.brothertoad.home.music.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.brothertoad.home.music.bean.SongDao;
import net.brothertoad.home.music.utils.Utils;

@Service
public class SongService implements ISongService {

	private static final Logger logger = LoggerFactory.getLogger(SongService.class);
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<SongDao> getSongsByArtist(Integer artistId) {
		List<SongDao> songs = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select song.id, song.title, song.trackNum, ");
		sb.append("album.name, album.sortName, artist.name, artist.sortName from ");
		sb.append("songs song join albums album on song.album = album.id ");
		sb.append("join artists artist on album.artist = artist.id ");
		sb.append("where artist.id = ");
		sb.append(artistId);
		sb.append(" and song.ripped = false and song.discarded = false and song.replace = false ");
		sb.append("order by artist.sortName asc, album.sortName asc, song.trackNum asc");
		jdbc.query(sb.toString(), new Object[] {}, (rs) -> {
			SongDao dao = new SongDao();
			dao.setId(rs.getInt(1));
			dao.setTitle(rs.getString(2));
			dao.setTrackNum(rs.getInt(3));
			dao.setAlbum(rs.getString(4));
			dao.setArtist(rs.getString(6));
			songs.add(dao);
		});
		logger.info("Found {} songs", songs.size());
		return songs;
	}
	
	@Override
	public List<SongDao> getSongsByState(Integer albumId, Integer state) {
		if (state == null || state == Utils.ALL_STATES) {
			return getSongsByArtist(albumId);
		}
		logger.info("Getting songs from album {} with state {}", albumId, state);
		List<SongDao> songs = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select song.id, song.title, song.trackNum, song.state from songs song ");
		sb.append("where song.album = ");
		sb.append(albumId);
		sb.append(" and song.state = ");
		sb.append(state);
		sb.append(" order by song.trackNum asc");
		jdbc.query(sb.toString(), new Object[] {}, (rs) -> {
			SongDao dao = new SongDao();
			dao.setId(rs.getInt(1));
			dao.setTitle(rs.getString(2));
			dao.setTrackNum(rs.getInt(3));
			dao.setState(rs.getInt(4));
			songs.add(dao);
		});
		logger.info("Found {} songs", songs.size());
		return songs;
	}
	
	@Override
	public List<SongDao> getSongsByState(Integer state) {
		logger.info("Getting songs with state {}", state);
		List<SongDao> songs = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select song.id, song.title, song.trackNum, song.state, ");
		sb.append("album.name, album.sortName, artist.name, artist.sortName from ");
		sb.append("songs song join albums album on song.album = album.id ");
		sb.append("join artists artist on album.artist = artist.id ");
		sb.append("where song.state = ");
		sb.append(state);
		sb.append(" order by artist.sortName asc, album.sortName asc, song.trackNum asc");
		jdbc.query(sb.toString(), new Object[] {}, (rs) -> {
			SongDao dao = new SongDao();
			dao.setId(rs.getInt(1));
			dao.setTitle(rs.getString(2));
			dao.setTrackNum(rs.getInt(3));
			dao.setState(rs.getInt(4));
			dao.setAlbum(rs.getString(5));
			dao.setArtist(rs.getString(7));
			songs.add(dao);
		});
		logger.info("Found {} songs", songs.size());
		return songs;
	}

}
