package net.brothertoad.home.music.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.brothertoad.home.music.bean.AlbumDao;
import net.brothertoad.home.music.utils.Utils;

@Service
public class AlbumService implements IAlbumService {

	private static final Logger logger = LoggerFactory.getLogger(AlbumService.class);
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<AlbumDao> getAlbumsByState(Integer artistId, Integer state) {
		if (state == null || state == Utils.ALL_STATES) {
			return getAlbums(artistId);
		}
		List<AlbumDao> albums = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct album.id, album.name, album.sortName, album.numTracks, artist.name ");
		sb.append("from albums album, artists artist ");
		sb.append("where album.artist = ");
		sb.append(artistId);
		sb.append(" and album.artist = artist.id ");
		sb.append("and exists (select * from songs where album = album.id and state = ");
		sb.append(state);
		sb.append(") order by album.sortName asc");
		jdbc.query(sb.toString(), new Object[] {}, (rs) -> {
			AlbumDao album = new AlbumDao();
			album.setId(rs.getInt(1));
			album.setTitle(rs.getString(2));
			album.setNumTracks(rs.getInt(4));
			album.setArtist(rs.getString(5));
			albums.add(album);
		});
		// logger.info("Found " + albums.size() + " artists.");
		return albums;
	}

	@Override
	public List<AlbumDao> getAlbums(Integer artistId) {
		List<AlbumDao> albums = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct album.id, album.name, album.sortName, album.numTracks, artist.name ");
		sb.append("from albums album, artists artist ");
		sb.append("where album.artist = ");
		sb.append(artistId);
		sb.append(" and album.artist = artist.id order by album.sortName asc");
		jdbc.query(sb.toString(), new Object[] {}, (rs) -> {
			AlbumDao album = new AlbumDao();
			album.setId(rs.getInt(1));
			album.setTitle(rs.getString(2));
			album.setNumTracks(rs.getInt(4));
			album.setArtist(rs.getString(5));
			albums.add(album);
		});
		// logger.info("Found " + albums.size() + " artists.");
		return albums;
	}

}
