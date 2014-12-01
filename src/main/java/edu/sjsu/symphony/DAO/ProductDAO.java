package edu.sjsu.symphony.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.sjsu.symphony.DB.DBConnection;
import edu.sjsu.symphony.POJO.Product;
import redis.clients.jedis.Jedis;

public class ProductDAO {

	public List<Product> getProductOverviewInCategory(String category){
		List<Product> productsList=new ArrayList<Product>();
		Jedis db=new DBConnection("redis").getRedisDBConnection();
		Set<String> keys=db.keys("*");
		Iterator<String> setIterator=keys.iterator();
		while(setIterator.hasNext()){
			String tempKey=setIterator.next();
			if(category.equalsIgnoreCase(db.hget(tempKey,"Category"))){
				Product tempProduct=new Product();
				tempProduct.setProductNumber(tempKey);
				tempProduct.setProductName(db.hget(tempKey, "Product Name"));
				tempProduct.setPrice(Double.parseDouble(db.hget(tempKey, "Price")));
				productsList.add(tempProduct);
			}
		}
		return productsList;
	}
	
	public Product getProductfromID(String ProductID){
		Jedis db=new DBConnection("redis").getRedisDBConnection();
		Product tempProduct=new Product();
		tempProduct.setProductNumber(ProductID);
		tempProduct.setProductName(db.hget(ProductID, "Product Name"));
		tempProduct.setPrice(Double.parseDouble(db.hget(ProductID, "Price")));
		tempProduct.setBrandName(db.hget(ProductID, "Brand Name"));
		tempProduct.setCategory(db.hget(ProductID, "Category"));
		tempProduct.setDescription(db.hget(ProductID, "Description"));
		db.close();
		return tempProduct;
	}
	
	public double getPrice(String productID){
		Jedis db=new DBConnection("redis").getRedisDBConnection();
		double unitPrice=Double.parseDouble(db.hget(productID, "Price"));
		db.close();
		return unitPrice;
	}

	public boolean addProduct(String productName, String brandName,	String category, String description, String price) {
		Jedis jedis=new Jedis("localhost");
		String productNo="";
		do{
			int randomnumber=new Random().nextInt(99999);
			productNo=brandName+String.format("%04d", randomnumber);
		}while(!jedis.hgetAll(productNo).isEmpty());
		System.out.println("Product Number" + productNo);
		jedis.hset(productNo, "Category", category);
		jedis.hset(productNo, "Brand Name", brandName);
		jedis.hset(productNo, "Product Name", productName);
		jedis.hset(productNo, "Price", price);
		jedis.hset(productNo, "Description", description);
		jedis.close();
		return true;
	}
}
