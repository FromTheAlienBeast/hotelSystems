package cn.instance;

import java.util.ResourceBundle;

// 工厂：创建dao或者service实例
public class BeanFactor {

	//加载配置文件
	private static ResourceBundle bun;
	static{
		bun=ResourceBundle.getBundle("instence");
	}
	
	//根据指定的key读取配置文件  获取类的全路径  最后在创建对象
	
	public static <T> T getInstance(String key,Class<T> clazz){
		String className=bun.getString(key);
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
