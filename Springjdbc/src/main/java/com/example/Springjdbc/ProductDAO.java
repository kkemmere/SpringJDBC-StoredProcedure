package com.example.Springjdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class ProductDAO {
	
	
	private String driver;
	private String url;
	private String userName;
	private String password;
	
	
	// setters
	public void setDriver(String driver)
	{
		this.driver = driver;
		
	}
	
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	// Random number generator
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
				throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	// Delimited MySQL codes
	public void delimitedProdCodes() throws ClassNotFoundException, SQLException
	{
			Class.forName(driver);
	
			// Open connection to MySQL Manager
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection is valid: " + conn.isValid(2));
			System.out.println();
			
			String delimString ="";
			
			// Don't know size of array so use ArrayList
			ArrayList<String> codesList = new ArrayList<String>();
			
			// Get codes with query
			ResultSet rs = null;
			String sqlCodes = "select distinct productCode\n" + "from products;\n"; 
			PreparedStatement psCodes = conn.prepareStatement(sqlCodes, rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_UPDATABLE);
			
			rs = psCodes.executeQuery();
			
			// Get all codes
			while (rs.next())
			{
				codesList.add(rs.getString("productCode"));
			}
			
			//Print product codes
			System.out.println(Arrays.toString(codesList.toArray()));
			System.out.println();
			
			// Create random
			Random rand = new Random();
			
			// Get random int in range from 2-6 using getRandomNumberInRange() method
			int ranNum = getRandomNumberInRange(2,6);
			
			// Using ranNum(random number in range from 2-6) and rand(random method) to get random string from product code list
			for (int i = 0; i < ranNum; i++)
			{
				delimString += codesList.get(rand.nextInt(codesList.size())) + " ";
			}
			
			System.out.println("Delimited String: " + delimString);
			
			// Stored Procedure Query
			String ProdCodesProcedure = "CALL classicmodels.getProductsPerCodes(?)";
			CallableStatement csProdCodes = conn.prepareCall(ProdCodesProcedure);
			
			// Get number of current codes in string
			int delimCodesNum = 0;
			StringTokenizer tokens = new StringTokenizer(delimString);
			delimCodesNum = tokens.countTokens();
			System.out.println("Number of codes in delimited string: " + delimCodesNum);
			
			// Create string array to be used to feed query
			String[] delimAry = delimString.split(" ");
			
			System.out.println();
			System.out.println("Getting product code information...");
			
			// For loop to pass query each product code 1 by 1
			// Not the most efficient could somehow pass the whole delimited string instead
			for (int i = 0; i < delimCodesNum; i++)
			{
				// Passing delimited product code to procedure
				csProdCodes.setString(1, delimAry[i]);
				rs = csProdCodes.executeQuery();
			
				String productCode = "";
				String productName = "";
				String productLine = "";
				String productVendor = "";
				int quantityinstock;
				
				while (rs.next())
				{
					// Info from stored procedure (Product Codes)
					productCode = rs.getString("productCode");
					productName = rs.getString("productName");
					productLine = rs.getString("productLine");
					productVendor = rs.getString("productVendor");
					quantityinstock = rs.getInt("quantityInStock");
					
					System.out.println();
					System.out.println("Info from this product code: " + productCode);
					System.out.println("Product Name: " + productName + " | " + "Product Line: " + productLine + " | " + "Product Vendor: " + productVendor + " | " + "Quantity In Stock: " + quantityinstock);;
					System.out.println();
				}
			
			}
			
			conn.close();
		
	}
}
