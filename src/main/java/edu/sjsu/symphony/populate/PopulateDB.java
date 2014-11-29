package edu.sjsu.symphony.populate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PopulateDB {

	public void populate(String path, Connection db) {
		PreparedStatement stmt=null;
		int result=0;
		BufferedReader reader=null;
		try {
			String query="insert into Album values (?,?,?)";
			reader=new BufferedReader(new FileReader(new File(path)));
			String line="";
			while((line=reader.readLine())!=null){
				String[] values=line.replace("|", "\t").split("\t");
				List<String> listValues=Arrays.asList(values);
				try {
					stmt=db.prepareStatement(query);
					stmt.setString(1, listValues.get(0));
					String artist=listValues.get(1);
					if(this.existArtist(db,artist))
						stmt.setString(2, artist);
					else
						stmt.setString(2, null);
					String genre="";
					for(int i=2;i<listValues.size();i++)
						genre+=listValues.get(i)+"|";
					stmt.setString(3, genre);
					result=stmt.executeUpdate();
					if(result>=1)
						System.out.println("Done");
					else
						System.out.println("insertion not done");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean existArtist(Connection db, String artist) {
		PreparedStatement stmt=null;
		ResultSet result=null;
		String query="select * from Artists where ArtistId=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1, artist);
			result=stmt.executeQuery();
			if(result.next()){
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
