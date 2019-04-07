package net.brothertoad.home.music.service;

import java.util.List;

import net.brothertoad.home.music.bean.Artist;

public interface IArtistService {
	
	public List<Artist> get();
	public List<Artist> getAll();

}
