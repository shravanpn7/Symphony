package edu.sjsu.symphony.POJO;

import java.util.List;

public class Album {

	private String albumId=null;
	private String artistId=null;
	private List<String> genreIdList=null;
	private List<String> trackIdList=null;

	
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public List<String> getGenreIdList() {
		return genreIdList;
	}
	public void setGenreIdList(List<String> genreIdList) {
		this.genreIdList = genreIdList;
	}
	public List<String> getTrackIdList() {
		return trackIdList;
	}
	public void setTrackIdList(List<String> trackIdList) {
		this.trackIdList = trackIdList;
	}
	
}
