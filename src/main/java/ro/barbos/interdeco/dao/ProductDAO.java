package ro.barbos.interdeco.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.barbos.interdeco.model.Product;

public class ProductDAO {

	public static List<Product> getProducts() {
		Logger logger = Logger.getLogger("dao");
		List<Product> products = null;
		
		StringBuilder sql = new StringBuilder("select ID, Label, Length, Width, Thick from Product");
		
		Connection con =null;
	    Statement stm =null;
	    ResultSet rs = null;
	    try {
	    	con = DataAccess.getInstance().getDatabaseConnection();
	    	con.setAutoCommit(true);
	    	stm = con.createStatement();
	    	logger.fine(sql.toString());
	    	rs = stm.executeQuery(sql.toString());
	    	products = new ArrayList<>();
	    	while(rs.next())
	    	{
	    		Product product = new Product();
	    		product.setId(rs.getLong("ID"));
	    		product.setName(rs.getString("Label"));
	    		product.setLength(rs.getLong("Length"));
	    		product.setWidth(rs.getLong("Width"));
	    		product.setThick(rs.getLong("Thick"));
	    		products.add(product);
	    	}
	    }catch(Exception e)
	    {
	       logger.warning(e.getMessage());	
	       logger.log(Level.INFO, "Error", e);
	    }
	    finally
		{
			if(rs!=null) try{rs.close();}catch(Exception e){}
			if(stm!=null) try{stm.close();}catch(Exception e){}
		}
	    return products;
	}
	
	public static Product create(Product product) {
		Logger logger = Logger.getLogger("dao");
		
		StringBuilder insertSql = new StringBuilder("insert into Product(Label, Length, Width, Thick) values('");
		insertSql.append(DataAccess.escapeString(product.getName())).append("',");
		insertSql.append(product.getLength()).append(",");
		insertSql.append(product.getWidth()).append(",");
		insertSql.append(product.getThick()).append(")");
		
		Connection con =null;
	    Statement stm =null;
	    ResultSet rs = null;
	    try {
	    	con = DataAccess.getInstance().getDatabaseConnection();
	    	con.setAutoCommit(true);
	    	stm = con.createStatement();
	    	logger.fine(insertSql.toString());
	    	int rez = 	stm.executeUpdate(insertSql.toString(), Statement.RETURN_GENERATED_KEYS);
	    	if(rez>0) {
	    		  rs = stm.getGeneratedKeys();
	    		  rs.next();
	    		  product.setId(rs.getLong(1));
	    	}
	    }catch(Exception e)
	    {
	       logger.warning(e.getMessage());	
	       logger.log(Level.INFO, "Error", e);
	       product = null;
	    }
	    finally
		{
			if(rs!=null) try{rs.close();}catch(Exception e){}
			if(stm!=null) try{stm.close();}catch(Exception e){}
		}
	    return product;
	}
	
	public static boolean delete(Product product) {
		Logger logger = Logger.getLogger("dao");
		boolean status = false;
			
		StringBuilder deleteSql = new StringBuilder("delete from Product where ID = ").append(product.getId());
		
		
		Connection con =null;
	    Statement stm =null;
	    
	    try {
	    	con = DataAccess.getInstance().getDatabaseConnection();
	    	con.setAutoCommit(true);
	    	stm = con.createStatement();
	    	logger.fine(deleteSql.toString());
	    	int rez = stm.executeUpdate(deleteSql.toString());
	    	if(rez>0) {
	    		  status = true;
	    	 }
	    }catch(Exception e)
	    {
	       logger.warning(e.getMessage());	
	       logger.log(Level.INFO, "Error", e);
	    }
	    finally
		{
			if(stm!=null) try{stm.close();}catch(Exception e){}
		}
	    return status;
	}
	
}
