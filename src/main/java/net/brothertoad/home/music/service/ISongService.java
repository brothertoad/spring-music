package net.brothertoad.home.music.service;

import java.util.List;

import net.brothertoad.home.music.bean.SongDao;

public interface ISongService {
	
	public List<SongDao> getSongsByArtist(int artistId);
	public List<SongDao> getSongsByState(int state);

}
