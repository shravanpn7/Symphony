package edu.sjsu.symphony.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.sjsu.symphony.DB.DBConnection;
import edu.sjsu.symphony.POJO.CreditCard;

public class CreditCardDAO {

	public List<CreditCard> getCreditCardDetails(String customerID){
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		List<CreditCard> cardList=new ArrayList<CreditCard>();
		String query="select * from CreditCards where CustomerID=?";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1,customerID);
			result=stmt.executeQuery();
			while(result.next()){
				CreditCard tempCard=new CreditCard();
				tempCard.setCreditCardNumber(result.getString("CreditCardNumber"));
				tempCard.setDateOfExpiry(result.getString("DateOfExpiry"));
				tempCard.setType(result.getString("CardType"));
				tempCard.setNameOnCard(result.getString("NameOnCard"));
				tempCard.setCvn(result.getString("CVN"));
				cardList.add(tempCard);
			}
			return cardList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public boolean addNewCard(String customerID, String cardNumber, String cardtype, String expiryDate, String holderName) {
		Connection db=new DBConnection("mysql").getmysqlDBConnection();
		PreparedStatement stmt=null;
		int result=0;
		String query="insert into CreditCards values(?,?,?,?,?,?)";
		try {
			stmt=db.prepareStatement(query);
			stmt.setString(1,cardNumber);
			stmt.setString(2,customerID);
			stmt.setString(3,cardtype);
			stmt.setString(4,"");
			stmt.setString(5,expiryDate);
			stmt.setString(6,holderName);
			result=stmt.executeUpdate();
			if(result>=1){
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				stmt.close();
				db.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}
