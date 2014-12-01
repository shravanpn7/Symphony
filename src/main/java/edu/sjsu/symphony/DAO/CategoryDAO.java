package edu.sjsu.symphony.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.sjsu.symphony.DB.DBConnection;
import edu.sjsu.symphony.POJO.Album;
import edu.sjsu.symphony.POJO.Tracks;

public class CategoryDAO {

	
	public List<String> getTopNList(String customerID){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		
		List<String> itemIdList=new ArrayList<String>();
		String query="select ITEM from USER_RECO where USER_ID = "+customerID;
		try {
			stmt=db.prepareStatement(query);
			result=stmt.executeQuery();
			while(result.next())
				itemIdList.add(result.getString("ITEM"));
			return itemIdList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public List<String> getItemList(String category){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<String> albumIdList=new ArrayList<String>();
		String query="select AlbumId from "+category+" limit 50";
		try {
			stmt=db.prepareStatement(query);
			result=stmt.executeQuery();
			while(result.next())
				albumIdList.add(result.getString("AlbumId"));
			return albumIdList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public List<String> getTrackList(String category){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<String> trackIdList=new ArrayList<String>();
		String query="select TrackId from "+category+" limit 50";
		try {
			stmt=db.prepareStatement(query);
			result=stmt.executeQuery();
			while(result.next())
				trackIdList.add(result.getString("TrackId"));
			return trackIdList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	public List<String> getArtistsList(String category){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<String> artistIdList=new ArrayList<String>();
		String query="select ArtistId from "+category+" limit 50";
		try {
			stmt=db.prepareStatement(query);
			result=stmt.executeQuery();
			while(result.next())
				artistIdList.add(result.getString("ArtistId"));
			return artistIdList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	public List<String> getGenreList(String category){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<String> genreList=new ArrayList<String>();
		String query="select GenreId from "+category+" limit 50";
		try {
			stmt=db.prepareStatement(query);
			result=stmt.executeQuery();
			while(result.next())
				genreList.add(result.getString("GenreId"));
			return genreList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	
	public List<String> getSearchDetails(String category, String searchString){
	  Connection db=new DBConnection("mysql").getmysqlDBConnection();
    PreparedStatement stmt=null;
    ResultSet result=null;
    List<String> searchResult=new ArrayList<String>();
    
	  if(category.equalsIgnoreCase("Albums")){
	    
	    
	    String query="select AlbumId from Albums where AlbumId ="+searchString;
	    try {
	      stmt=db.prepareStatement(query);
	      result=stmt.executeQuery();
	      while(result.next())
	        searchResult.add(result.getString("AlbumId"));
	      
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
	    finally{
	      try {
	        db.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	           
    }
	    }
	  }
    if(category.equalsIgnoreCase("Tracks")){

      String query="select TrackId from Tracks where TrackId ="+searchString;
      try {
        stmt=db.prepareStatement(query);
        result=stmt.executeQuery();
        while(result.next())
          searchResult.add(result.getString("TrackId"));

      } catch (SQLException e) {
        e.printStackTrace();
        return null;
      }
      finally{
        try {
          db.close();
        } catch (SQLException e) {
          e.printStackTrace();
          return null;
             
    }
      }
    }
    if(category.equalsIgnoreCase("Artists")){
 
      String query="select ArtistId from Artists where ArtistId ="+searchString;
      try {
        stmt=db.prepareStatement(query);
        result=stmt.executeQuery();
        while(result.next())
          searchResult.add(result.getString("ArtistId"));
 
      } catch (SQLException e) {
        e.printStackTrace();
        return null;
      }
      finally{
        try {
          db.close();
        } catch (SQLException e) {
          e.printStackTrace();
          return null;
             
    }
      }
    }
    return searchResult;
  }

	public List<Tracks> getArtistDetails(String productId) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<Tracks> trackList=new ArrayList<Tracks>();
		String query="select * from Tracks where ArtistId=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, productId);
			result=stmt.executeQuery();
			while(result.next()){
				Tracks trackObject=new Tracks();
				List<String> tmplist=new ArrayList<String>();
				trackObject.setTrackId(result.getString("TrackId"));
				trackObject.setAlbumId(result.getString("AlbumID"));
				trackObject.setArtistId(result.getString("ArtistId"));
				tmplist.add(result.getString("Genre1"));
				tmplist.add(result.getString("Genre2"));
				tmplist.add(result.getString("Genre3"));
				tmplist.add(result.getString("Genre4"));
				tmplist.add(result.getString("Genre5"));
				tmplist.add(result.getString("Genre6"));
				tmplist.add(result.getString("Genre7"));
				tmplist.add(result.getString("Genre8"));
				tmplist.add(result.getString("Genre9"));
				trackObject.setGenreIdList(tmplist);
				trackList.add(trackObject);
			}
			return trackList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public Tracks getTrackDetails(String productId) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		Tracks trackObject=new Tracks();
		String query="select * from Tracks where TrackId=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, productId);
			result=stmt.executeQuery();
			while(result.next()){
				List<String> tmplist=new ArrayList<String>();
				trackObject.setTrackId(result.getString("TrackId"));;
				trackObject.setAlbumId(result.getString("AlbumId"));
				trackObject.setArtistId(result.getString("ArtistId"));
				tmplist.add(result.getString("Genre1"));
				tmplist.add(result.getString("Genre2"));
				tmplist.add(result.getString("Genre3"));
				tmplist.add(result.getString("Genre4"));
				tmplist.add(result.getString("Genre5"));
				tmplist.add(result.getString("Genre6"));
				tmplist.add(result.getString("Genre7"));
				tmplist.add(result.getString("Genre8"));
				tmplist.add(result.getString("Genre9"));
				trackObject.setGenreIdList(tmplist);
			}
			return trackObject;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public Album getAlbumDetails(String productId) {
		System.out.println("ProductID"+productId);
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		Album albumObject=new Album();
		String query="select * from Album where AlbumId=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, productId);
			result=stmt.executeQuery();
			while(result.next()){
				List<String> tmplist=new ArrayList<String>();
				albumObject.setAlbumId(result.getString("AlbumId"));
				albumObject.setArtistId(result.getString("ArtistId"));
				System.out.println(result.getString("ArtistId"));
				tmplist.add(result.getString("Genre1"));
				System.out.println(result.getString("Genre1"));
				tmplist.add(result.getString("Genre2"));
				tmplist.add(result.getString("Genre3"));
				tmplist.add(result.getString("Genre4"));
				tmplist.add(result.getString("Genre5"));
				tmplist.add(result.getString("Genre6"));
				tmplist.add(result.getString("Genre7"));
				tmplist.add(result.getString("Genre8"));
				tmplist.add(result.getString("Genre9"));
				albumObject.setGenreIdList(tmplist);
				albumObject.setTrackIdList(this.getTracksWithAlbumId(productId));
			}
			return albumObject;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private List<String> getTracksWithAlbumId(String productId) {
		List<String> listTracks=new ArrayList<String>();
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		String query="select TrackId from Tracks where AlbumId=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, productId);
			result=stmt.executeQuery();
			while(result.next()){
				System.out.println(result.getString("TrackId"));
				listTracks.add(result.getString("TrackId"));
			}
			return listTracks;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public Tracks getGenreDetails(String productId) {
		return null;
	}
	
}
