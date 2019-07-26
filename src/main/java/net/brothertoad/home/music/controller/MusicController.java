package net.brothertoad.home.music.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.brothertoad.home.music.bean.AlbumDao;
import net.brothertoad.home.music.bean.ArtistDao;
import net.brothertoad.home.music.bean.SongDao;
import net.brothertoad.home.music.bean.SongUpdateDao;
import net.brothertoad.home.music.service.IAlbumService;
import net.brothertoad.home.music.service.IArtistService;
import net.brothertoad.home.music.service.ISongService;

@RestController
public class MusicController {
	
	private static final Logger logger = LoggerFactory.getLogger(MusicController.class);
	
	@Autowired
	private IArtistService artistService;
	
	@Autowired
	private IAlbumService albumService;
	
	@Autowired
	private ISongService songService;
	
	@CrossOrigin()
	@GetMapping("/songs/{albumId}/{state}")
	public List<SongDao> songsByState(@PathVariable Integer albumId, @PathVariable Integer state) {
		List<SongDao> songs = songService.getSongsByState(albumId, state);
		logger.info("Controller is returning {} songs.", songs.size());
		return songs;
	}

	@CrossOrigin()
	@GetMapping("/allsongs/{state}")
	public List<SongDao> songsByState(@PathVariable Integer state) {
		List<SongDao> songs = songService.getSongsByState(state);
		logger.info("all songs, controller is returning {} songs.", songs.size());
		return songs;
	}

	@CrossOrigin()
	@PostMapping("/updatesongs")
	public String updateSongs(@RequestBody SongUpdateDao updateInfo) {
		return songService.updateSongs(updateInfo);
	}

	@CrossOrigin()
	@GetMapping("/artists/{state}")
	public List<ArtistDao> artistsByState(@PathVariable Integer state) {
		List<ArtistDao> artists = artistService.getArtistsByState(state);
		// logger.info("Controller is returning {} artists.", artists.size());
		return artists;
	}

	@CrossOrigin()
	@GetMapping({"/albums/{artistId}", "/albums/{artistId}/{state}"})
	public List<AlbumDao> albumsByState(@PathVariable Integer artistId, @PathVariable(required=false) Integer state) {
		List<AlbumDao> albums = albumService.getAlbumsByState(artistId, state);
		// logger.info("Controller is returning {} albums.", albums.size());
		return albums;
	}

}
