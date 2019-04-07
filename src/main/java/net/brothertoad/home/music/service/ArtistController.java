package net.brothertoad.home.music.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.brothertoad.home.music.bean.Artist;

@RestController
public class ArtistController {

	private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/artists")
	public List<Artist> getArtists() {
		List<Artist> artists = new ArrayList<>();
		jdbcTemplate.query("select id, name from artists order by sortname asc", new Object[] {}, (rs) -> {
			Artist artist = new Artist();
			artist.setId(rs.getInt(1));
			artist.setName(rs.getString(2));
			artists.add(artist);
		});
		logger.info("Found " + artists.size() + " artists.");
		return artists;
	}
	
}
