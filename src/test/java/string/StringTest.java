package string;

import org.junit.Test;

public class StringTest {
	
	// 如果涉及字符串相加时，要么初始化的字符串设置为空字符串，不要用null，要么使用StringBuilder或者StringBuffer
	@Test
	public void testNullPlusString() {
		String s1 = null;
		String s2 = "hehe";
		System.out.println(s1+s2);// 输出nullhehe
		
		String s3 = s1+s2;// 相加后变为nullhehe（知道真相的我眼泪流下来）
		System.out.println(s3);// 输出nullhehe
	}
	
	@Test
	public void testNullPlusStringBuilder() {
		StringBuilder s1 = null;
		StringBuilder s2 = new StringBuilder("hehe");// 同样的。所以不应该用null来初始化字符串
		System.out.println(s2.append(s1));// 输出hehenull
	}

}
