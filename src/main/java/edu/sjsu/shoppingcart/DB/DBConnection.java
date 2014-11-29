package edu.sjsu.shoppingcart.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import redis.clients.jedis.Jedis;

public class DBConnection {

	Connection mysqlDBConnection=null;
	Jedis redisDBConnection=null;
	
	public DBConnection() {
	}
	
	public DBConnection(String database) {
		if(database.equalsIgnoreCase("mysql")){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				this.mysqlDBConnection=DriverManager.getConnection("jdbc:mysql://cmpe282.cmyhjohacafg.us-west-2.rds.amazonaws.com:3306/Symphony", "root", "root");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(database.equalsIgnoreCase("redis")){
			this.redisDBConnection=new Jedis("localhost");
		}
	}

	public Connection getmysqlDBConnection() {
		return mysqlDBConnection;
	}
	
	public Jedis getRedisDBConnection() {
		return redisDBConnection;
	}
	
	public void closeDBConnection(Connection mysqlDBConnection){
		try {
			mysqlDBConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDBConnection(Jedis redisDBConnection){
		redisDBConnection.close();
	}
	
}
