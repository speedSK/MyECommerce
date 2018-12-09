package com.ruoyi;

import com.google.common.base.Utf8;
import sun.nio.cs.ext.GBK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class test {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//客户端
		//1、创建客户端Socket，指定服务器地址和端口
		Socket socket =new Socket("127.0.0.1",8094);
		//2、获取输出流，向服务器端发送信息
		OutputStream os = socket.getOutputStream();//字节输出流
//		PrintWriter pw =new PrintWriter(os);//将输出流包装成打印流
        String str = "01833061YKT092018092930000522000000353906            0300002              乔舜                420381197708144072139991234566213360746646581368             6228483668753162073             ";
        os.write(str.getBytes("GBK"));
        os.flush();
//		pw.write(str);
//		pw.flush();
		socket.shutdownOutput();
		//3、获取输入流，并读取服务器端的响应信息
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while((info=br.readLine())!=null){
		 System.out.println("我是客户端，服务器说："+info);
		}
	
		//4、关闭资源
		br.close();
		is.close();
//		pw.close();
		os.close();
		socket.close();
	}
}
