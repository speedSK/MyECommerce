package com.information.project.bank.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {
	private static ApplicationContext ctx;

	public static Object lookupService(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	public static Object getService(Class serviceInrterface) {
		return getService(serviceInrterface.getName());
	}

	public static Object getService(String serviceInterfaceName) {
		if (serviceInterfaceName == null) {
			throw new RuntimeException("serviceInterfaceName cannot be null . Please check it carefully.");
		} else {
			String beanName = null;

			try {
				beanName = serviceInterfaceName.substring(serviceInterfaceName.lastIndexOf(".") + 1);
				return getApplicationContext().getBean(beanName);
			} catch (Exception var3) {
				var3.printStackTrace();
				throw new RuntimeException("An unknown service name : " + beanName
						+ " . Please make sure you have such a bean name is in spring configuration.");
			}
		}
	}

	private static ApplicationContext getApplicationContext() {
		if (ctx == null) {
			String[] paths = new String[]{"/config/spring/aContext_db.xml", "/config/spring/aContext_ccbofdl.xml",
					"/config/spring/aContext_bankset.xml"};
			ctx = new ClassPathXmlApplicationContext(paths);
		}

		return ctx;
	}

	public static void main(String[] args) {
		System.out.println(getService("bankSetService"));
	}
}