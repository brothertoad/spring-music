package net.brothertoad.home.music.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.brothertoad.home.music.bean.SongDao;
import net.brothertoad.home.music.service.IArtistService;
import net.brothertoad.home.music.service.ISongService;

@RestController
public class MusicController {
	
	private static final Logger logger = LoggerFactory.getLogger(MusicController.class);
	
	@Autowired
	private IArtistService artistService;
	
	@Autowired
	private ISongService songService;
	
	/*
	
	@GetMapping(value= {"/","/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		List<Artist> artists = artistService.get();
		mv.getModel().put("artists", artists);
		return mv;
	}
	
	@GetMapping("/artist")
	public ModelAndView artist(@RequestParam(name="id", required=true) Integer artistId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("artist");
		List<SongDao> songs = songService.getSongsByArtist(artistId);
		mv.getModel().put("songs", songs);
		return mv;
	}
	
	*/

	@CrossOrigin()
	@GetMapping("/songs/state")
	public List<SongDao> songByState(@RequestParam(name="state", required=true) Integer state) {
		List<SongDao> songs = songService.getSongsByState(state);
		logger.info("Controller is returning {} songs.", songs.size());
		return songs;
	}

}
