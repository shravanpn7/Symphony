package edu.sjsu.shoppingcart.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.sjsu.shoppingcart.DB.DBConnection;
import edu.sjsu.shoppingcart.POJO.Album;
import edu.sjsu.shoppingcart.POJO.Tracks;

public class AlbumDAO {

	
	public List<String> getItemList(String category){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<String> albumIdList=new ArrayList<String>();
		String query="select AlbumId from "+category;
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
	
	
	public void getDetails(String category, String productId){
		if(category.equalsIgnoreCase("Album")){
			this.getAlbumDetails(productId);
		}
		if(category.equalsIgnoreCase("Tracks")){
			this.getTrackDetails(productId);
		}
		if(category.equalsIgnoreCase("Artists")){
			this.getArtistDetails(productId);
		}
	}

	public Tracks getArtistDetails(String productId) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		Tracks trackObject=new Tracks();
		String query="select * from Tracks where ArtistId=?";
		try {
			stmt=db.prepareStatement(query);
			result=stmt.executeQuery();
			while(result.next()){
				trackObject.setTrackId(result.getString("TrackId"));;
				trackObject.setAlbumId(result.getString("AlbumId"));
				trackObject.setArtistId(result.getString("ArtistId"));
				String[] genre=result.getString("GenreIdList").replace("|", "\t").split("\t");
				trackObject.setGenreIdList(Arrays.asList(genre));
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

	public Tracks getTrackDetails(String productId) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		Tracks trackObject=new Tracks();
		String query="select * from Tracks where TrackId=?";
		try {
			stmt=db.prepareStatement(query);
			result=stmt.executeQuery();
			while(result.next()){
				trackObject.setTrackId(result.getString("TrackId"));;
				trackObject.setAlbumId(result.getString("AlbumId"));
				trackObject.setArtistId(result.getString("ArtistId"));
				String[] genre=result.getString("GenreIdList").replace("|", "\t").split("\t");
				trackObject.setGenreIdList(Arrays.asList(genre));
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
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		Album albumObject=new Album();
		String query="select * from Album where AlbumId=?";
		try {
			stmt=db.prepareStatement(query);
			result=stmt.executeQuery();
			while(result.next()){
				albumObject.setAlbumId(result.getString("AlbumId"));
				albumObject.setArtistId(result.getString("ArtistId"));
				String[] genre=result.getString("GenreIdList").replace("|", "\t").split("\t");
				albumObject.setGenreIdList(Arrays.asList(genre));
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
	
}
