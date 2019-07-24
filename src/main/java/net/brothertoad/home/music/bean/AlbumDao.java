package net.brothertoad.home.music.bean;

public class AlbumDao {

	private Integer id;
	private String title;
	private String artist;
	private Integer numTracks;
	
	public AlbumDao() {
		
	}

	public Integer getId() {
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
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getNumTracks() {
		return numTracks;
	}

	public void setNumTracks(Integer numTracks) {
		this.numTracks = numTracks;
	}
	
}
