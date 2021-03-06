package net.brothertoad.home.music.service;

import java.util.List;

import net.brothertoad.home.music.bean.SongDao;
import net.brothertoad.home.music.bean.SongUpdateDao;

public interface ISongService {
	
	public List<SongDao> getSongsByArtist(Integer artistId);
	public List<SongDao> getSongsByState(Integer state);
	public List<SongDao> getSongsByState(Integer albumId, Integer state);
	public String updateSongs(SongUpdateDao updateInfo);

}
