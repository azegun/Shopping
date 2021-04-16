package shopping.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shopping.dao.ProductDao;
import shopping.dto.Product;
import shopping.util.JdbcConn;

public class ProductImpl implements ProductDao {
	
	private static final ProductImpl instance = new ProductImpl();
	
	public static ProductImpl getInstance() {
		return instance;
	}

	@Override
	public List<Product> selectProductByAll() {
		String sql = "select p_code,p_name,price,stock from product_information";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				List<Product> list = new ArrayList<>();
				do {
					list.add(getProduct(rs));
				}while(rs.next());
				return list;
				}				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		String pCode = rs.getString("p_code");
		String pName = rs.getString("p_name");
		int price = rs.getInt("price");
		int stock = rs.getInt("stock");
		return new Product(pCode, pName, price, stock);
	}

	@Override
	public Product selectProductByNo(Product product) {
		String sql = "select p_code,p_name,price,stock from product_information where p_code = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, product.getpCode());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getProduct(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertProduct(Product product) {
		String sql = "insert into product_information values(?, ?, ?, ?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, product.getpCode());
			pstmt.setString(2, product.getpName());
			pstmt.setInt(3, product.getPrice());
			pstmt.setInt(4, product.getStock());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		String sql = "update product_information set p_name = ?, price = ?, stock = ? where p_code = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, product.getpName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setInt(3, product.getStock());
			pstmt.setString(4, product.getpCode());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteProduct(Product product) {
		String sql = "delete from product_information where p_code = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, product.getpCode());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
