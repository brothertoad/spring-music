package net.brothertoad.home.music.service;

import java.util.List;

import net.brothertoad.home.music.bean.ArtistDao;

public interface IArtistService {
	
	public List<ArtistDao> getArtistsByState(Integer state);
	public List<ArtistDao> getAll();

}
