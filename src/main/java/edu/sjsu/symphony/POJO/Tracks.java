package edu.sjsu.symphony.POJO;

import java.util.List;

public class Tracks {

	private String trackId=null;
	private String albumId=null;
	private String artistId=null;
	private List<String> genreIdList=null;
	
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
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
}
