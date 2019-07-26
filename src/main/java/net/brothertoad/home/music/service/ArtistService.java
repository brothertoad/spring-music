package net.brothertoad.home.music.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.brothertoad.home.music.bean.ArtistDao;
import net.brothertoad.home.music.utils.Utils;

@Service
public class ArtistService implements IArtistService {
	
	private static final Logger logger = LoggerFactory.getLogger(ArtistService.class);
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<ArtistDao> getArtistsByState(Integer state) {
		if (state == null || state == Utils.ALL_STATES) {
			return getAll();
		}
		List<ArtistDao> artists = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct artist.id, artist.name, artist.sortname ");
		sb.append("from artists artist join albums album ");
		sb.append("on artist.id = album.artist ");
		sb.append("inner join songs song on album.id = song.album ");
		sb.append("where song.state = ");
		sb.append(state);
		sb.append(" order by artist.sortname asc");
		jdbc.query(sb.toString(), new Object[] {}, (rs) -> {
			ArtistDao artist = new ArtistDao();
			artist.setId(rs.getInt(1));
			artist.setName(rs.getString(2));
			artists.add(artist);
		});
		return artists;
	}

	@Override
	public List<ArtistDao> getAll() {
		List<ArtistDao> artists = new ArrayList<>();
		jdbc.query("select id, name from artists order by sortname asc", new Object[] {}, (rs) -> {
			ArtistDao artist = new ArtistDao();
			artist.setId(rs.getInt(1));
			artist.setName(rs.getString(2));
			artists.add(artist);
		});
		return artists;
	}

}
