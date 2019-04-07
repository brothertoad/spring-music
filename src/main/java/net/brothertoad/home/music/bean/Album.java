package net.brothertoad.home.music.bean;

import net.brothertoad.home.music.utils.Utils;

public class Album {

	private Integer id;

	private String name;
	private String sortName;
	private Integer numTracks;
	
	private Artist artist;

	public Album() {

	}

	public Album(String name) {
		// Use the setName method so the sortName is populated.
		setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		sortName = Utils.createSortName(name);
	}

	public Integer getNumTracks() {
		return numTracks;
	}

	public void setNumTracks(Integer numTracks) {
		this.numTracks = numTracks;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getSortName() {
		return sortName;
	}

}
