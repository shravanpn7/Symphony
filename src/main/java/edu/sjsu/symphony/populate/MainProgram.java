package edu.sjsu.symphony.populate;

import java.sql.Connection;
import java.sql.SQLException;

import edu.sjsu.shoppingcart.DB.DBConnection;

public class MainProgram {

	public static void main(String[] args) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		new PopulateDB().populate("/home/rakshatha/Desktop/Yahoo/Webscope_C15/ydata-ymusic-kddcup-2011-track1/albumData1.txt",db);
		try {
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
