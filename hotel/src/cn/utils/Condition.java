package cn.utils;

/**
 * 分装条件
 * @author Taeyeon
 *
 */
public class Condition {

	// 菜的类别作为条件
	private int foodTypeId;
	// 菜的名称作为条件
	private String foodName;
	
	public int getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(int foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
}
