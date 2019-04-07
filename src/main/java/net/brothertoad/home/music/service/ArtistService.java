package net.brothertoad.home.music.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.brothertoad.home.music.bean.Artist;

@Service
public class ArtistService implements IArtistService {
	
	private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Artist> get() {
		List<Artist> artists = new ArrayList<>();
		// Need to modify to only return artists with unripped songs
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct artist.id, artist.name, artist.sortname ");
		sb.append("from artists artist join albums album ");
		sb.append("on artist.id = album.artist ");
		sb.append("inner join songs song on album.id = song.album ");
		sb.append("where song.ripped = false and song.discarded = false ");
		sb.append("order by artist.sortname asc");
		jdbc.query(sb.toString(), new Object[] {}, (rs) -> {
			Artist artist = new Artist();
			artist.setId(rs.getInt(1));
			artist.setName(rs.getString(2));
			artists.add(artist);
		});
		logger.info("Found " + artists.size() + " artists.");
		return artists;
	}

	@Override
	public List<Artist> getAll() {
		List<Artist> artists = new ArrayList<>();
		jdbc.query("select id, name from artists order by sortname asc", new Object[] {}, (rs) -> {
			Artist artist = new Artist();
			artist.setId(rs.getInt(1));
			artist.setName(rs.getString(2));
			artists.add(artist);
		});
		logger.info("Found " + artists.size() + " artists.");
		return artists;
	}

}
