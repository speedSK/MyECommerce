package com.information;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.information.project.bank.server.SocketServer;

/**
 * 启动程序
 * 
 * @author LiuNing
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.information.project.*.*.mapper")
public class InformationApplication {
	public static void main(String[] args) {
		// System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(InformationApplication.class, args);
//		SocketServer.getInstance();//银行socket服务
		System.out.println(
				"(♥◠‿◠)ﾉﾞ  系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" + "◇◇◇◇◇◇◇◇◇◇◇◇◆◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◆◆◆◆◆◆◇◆◆◆◆◆◆◆◆◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◇◇◇◆◆◆◆◆◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◆◆◆◆◇◇◇◆◆◆◆◆◆◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◇◇◆◆◆◆◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◆◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◆◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◆◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◇◆◆◆◆◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◇◇◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◇◇◆◆◆◆◇◇◇◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◆◆◇◇◇◇◇◆◆◆◆◆◇◇◇◇◇◇◇◇ \n"
						+ "◇◇◇◇◇◇◇◇◇◇◆◆◆◆◆◆◆◆◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◇◆◆◆◆◆◆◆◆◇◇◆◆◆◆◆◆◆◆◇◇◇◇◇◇");
	}
}