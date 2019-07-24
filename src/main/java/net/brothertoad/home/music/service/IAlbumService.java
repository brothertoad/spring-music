package net.brothertoad.home.music.service;

import java.util.List;

import net.brothertoad.home.music.bean.AlbumDao;

public interface IAlbumService {
	
	public List<AlbumDao> getAlbumsByState(Integer artistId, Integer state);
	public List<AlbumDao> getAlbums(Integer artistId);

}
