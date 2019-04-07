package net.brothertoad.home.music.bean;

import net.brothertoad.home.music.utils.Utils;

public class Song {
	
	private Integer id;
	
	private String title;
	private String sortTitle;
	private Integer trackNum;
	private Boolean ripped;
	private Boolean discarded;
	
	private Album album;
	
	public Song() {
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		sortTitle = Utils.createSortName(title);
	}

	public String getSortTitle() {
		return sortTitle;
	}

	public Integer getTrackNum() {
		return trackNum;
	}

	public void setTrackNum(Integer trackNum) {
		this.trackNum = trackNum;
	}

	public Boolean getRipped() {
		return ripped;
	}

	public void setRipped(Boolean ripped) {
		this.ripped = ripped;
	}

	public Boolean getDiscarded() {
		return discarded;
	}

	public void setDiscarded(Boolean discarded) {
		this.discarded = discarded;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

}
