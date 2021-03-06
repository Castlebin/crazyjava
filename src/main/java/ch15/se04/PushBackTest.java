package ch15.se04;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

// 实现功能：打印出目标字符串出现之前的内容

// 推回流的使用
// PushbackInputStream和PushbackReader是java提供的推回输入流
// 它们实现奥秘在于它们拥有一个推回缓冲区，当程序调用unread()方法时，其实是将内容放到了推回缓冲区中
// 而程序从中读取数据时，实际上是先去推回缓冲区中拿数据，当退回缓冲器中的数据不够时，才去原输入流中读取数据
public class PushBackTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try(	// 创建一个PushbackReader，并指定推回缓冲区大小为64(当推回缓冲区满了之后，会引发Pushback buffer overflow的IOException异常)
				PushbackReader pr = new PushbackReader(new FileReader("src/main/java/ch15/se04/PushBackTest.java"), 64)) {
			char[] buf = new char[32];
			String lastContent = "";// 记录上次读取到的内容
			int hasRead = 0;
			while((hasRead=pr.read(buf)) > 0) {
				// 将读取到的内容转化为字符串
				String content = new String(buf, 0, hasRead);
				int targetIndex = 0;
				// 将上次读取的字符串和这次读取的字符串拼起来，查看是否包含目标字符串，如果包含的话
				if((targetIndex=(lastContent+content).indexOf("new PushbackReader")) > 0) {
					// 将本次内容和上次内容一起推回缓冲区
					pr.unread((lastContent+content).toCharArray());
					// 重新定义一个长度为targetIndex的char数组
					if(targetIndex > buf.length) {
						buf = new char[targetIndex];
					}
					
					// 重新读取指定长度的内容（也就是目标字符串之前的内容）
					pr.read(buf, 0, targetIndex);
					
					// 打印
					System.out.print(new String(buf, 0, targetIndex));
					System.exit(0);
				} else {
					// 打印上次读取到的内容
					System.out.print(lastContent);
					// 将本次内容设置为上次读取到的内容
					lastContent = content;
				}
			}
		}
	}
}
