package net.brothertoad.home.music.utils;

public class Utils {
	
	public static String createSortName(String name) {
		String sortName = name;
		String lcName = name.toLowerCase();
		if (lcName.startsWith("a ")) {
			sortName = name.substring(2) + ", " + name.substring(0, 1);
		}
		else if (lcName.startsWith("an ")) {
			sortName = name.substring(3) + ", " + name.substring(0, 2);
		}
		else if (lcName.startsWith("the ")) {
			sortName = name.substring(4) + ", " + name.substring(0, 3);
		}
		return sortName;
	}

}
