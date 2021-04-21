package shopping.dao;

import java.util.List;

import shopping.dto.Customer;
import shopping.dto.Product;
import shopping.dto.Sales;

public interface SalesDao {
	//MAIN
	List<Sales> selectMain();	
	List<Sales> selectMainByDate(Sales sales);
	
	//PRODUCT
	List<Sales> selectProduct();
	List<Sales> selectProductByPcode(Product product);
	List<Sales> selectProductByPname(String pName);
	
	//DETAIL
	List<Sales> selectDetail();
	List<Sales> selectDetailByCname(String cuName);
	
	//이름별 검색 고객
	
	List<Sales> selectDetailByProductAndCustoemr(String pName, String cuName);
	
	
	
	int insertSales(Sales sales);
	int updateSales(Sales sales);
	int deleteSales(Sales sales);
	
	

}
