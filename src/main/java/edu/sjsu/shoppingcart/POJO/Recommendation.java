package edu.sjsu.shoppingcart.POJO;

import java.util.List;

public class Recommendation {

	private String itemType=null;
	private String itemId=null;
	private String algorithm=null;
	private List<Recommended> recommendedItems=null;
	
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public List<Recommended> getRecommendedItems() {
		return recommendedItems;
	}
	public void setRecommendedItems(List<Recommended> recommendedItems) {
		this.recommendedItems = recommendedItems;
	}
}
