package net.brothertoad.home.music.bean;

import java.util.List;

public class SongUpdateDao {
	
	private Integer state;
	private List<Integer> songIds;
	
	public SongUpdateDao() {
		
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Integer> getSongIds() {
		return songIds;
	}

	public void setSongIds(List<Integer> songIds) {
		this.songIds = songIds;
	}

}
