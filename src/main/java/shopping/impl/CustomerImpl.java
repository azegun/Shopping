package shopping.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shopping.dao.CustomerDao;
import shopping.dto.Customer;
import shopping.dto.Product;
import shopping.dto.Sales;
import shopping.dto.Sign;
import shopping.util.JdbcConn;

public class CustomerImpl implements CustomerDao {

	private static final CustomerImpl instance = new CustomerImpl();
	

	public static CustomerImpl getInstance() {
		return instance;
	}

	

	@Override
	public List<Customer> selectByCustomerByAll() {
		String sql = "select cu_no, cu_name, birth, phone, sex, ID from customer_information";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Customer> list = new ArrayList<>();
				do {
					list.add(getCustomer(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Customer getCustomer(ResultSet rs) {
		int cuNo = 0;
		String cuName = null;
		String birth = null;
		String phone = null;
		String sex = null;
		Sign id = null;
		try {
			cuNo = rs.getInt("cu_no");
		} catch (SQLException e) {}

		try {
			cuName = rs.getString("cu_name");
		} catch (SQLException e) {}

		try {
			birth = rs.getString("birth");
		} catch (SQLException e) {}

		try {
			phone = rs.getString("phone");
		} catch (SQLException e) {}

		try {
			sex = rs.getString("sex");
		} catch (SQLException e) {}

		try {
			id = new Sign(rs.getString("ID"));
		} catch (SQLException e) {}
		return new Customer(cuNo, cuName, birth, phone, sex, id);

	}

	@Override
	public Customer selectCustomerByNo(Customer customer) {
		String sql = "select cu_no,cu_name,birth,phone,sex,ID from customer_information where cu_no = ?";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customer.getCuNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getCustomer(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertCustomer(Customer customer) {
		String sql = "insert into customer_information"
				+ "	(ID, cu_name, birth, sex , phone) values" 
				+ "	(?, ?, ?, ?, ?)";
		try (Connection con = JdbcConn.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, customer.getId().getId());
			pstmt.setString(2, customer.getCuName());
			pstmt.setString(3, customer.getBirth());			
			pstmt.setString(4, customer.getSex());
			pstmt.setString(5, customer.getPhone());
		
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteCustomer(Customer customer) {
		String sql = "delete from customer_information where cu_no = ?";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customer.getCuNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Customer> selectByName() {
		String sql = "select cu_name from customer_information;";
		try (Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				ArrayList<Customer> list = new ArrayList<Customer>();
				do {
					list.add(getCustomer(rs));
				} while (rs.next());
				return list;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
