package com.example.Springjdbc;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringjdbcApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
	//	SpringApplication.run(SpringjdbcApplication.class, args);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		ProductDAO productDAO = context.getBean("productDAO", ProductDAO.class);
		
		productDAO.delimitedProdCodes();
		
	}

}
