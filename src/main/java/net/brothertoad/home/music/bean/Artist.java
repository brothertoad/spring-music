package net.brothertoad.home.music.bean;

import net.brothertoad.home.music.utils.Utils;

public class Artist {

	private Integer id;

	private String name;
	private String sortName;

	public Artist() {

	}

	public Artist(String name) {
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

	public String getSortName() {
		return sortName;
	}

}
