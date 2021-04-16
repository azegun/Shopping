package shopping.impl;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import shopping.dao.SalesDao;
import shopping.dto.Customer;
import shopping.dto.Product;
import shopping.dto.Sales;

public class SalesImplTest {
	
	private static SalesDao dao = SalesImpl.getInstance();

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}


	@Test
	public void testSelectMain() {
		System.out.printf("%s()%n", "testSelectMain");
		List<Sales> salesList = dao.selectMain();
		Assert.assertNotNull(salesList);
		for(Sales s : salesList) {
			System.out.println(s);
		}
	}

	@Test
	public void testSelectMainByDate() {
		System.out.printf("%s()%n", "testSelectMainByDate");
		Sales sales = new Sales("2012-04-14");
		List<Sales> list = dao.selectMainByDate(sales);
		Assert.assertNotNull(list);
		for(Sales s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void testSelectProduct() {
		System.out.printf("%s()%n", "testSelectProduct");
		List<Sales> salesList = dao.selectProduct();
		Assert.assertNotNull(salesList);
		for(Sales s : salesList) {
			System.out.println(s.toStringP());
		}
	}

	@Test
	public void testSelectProductByProInfo() {
		System.out.printf("%s()%n", "testSelectProductByProInfo");
		List<Sales> list = dao.selectProductByProInfo(new Product("PA"));
		Assert.assertNotNull(list);
		for(Sales s : list) {
			System.out.println(s.toStringP());
		}
	}

	@Test
	public void testSelectDetail() {
		System.out.printf("%s()%n", "testSelectDetail");
		List<Sales> detailList = dao.selectDetail();
		Assert.assertNotNull(detailList);
		for(Sales s : detailList) {
			System.out.println(s.toStringD());
		}
	}

	@Test
	public void testSelectDetailBycustomer() {
		System.out.printf("%s()%n", "testSelectDetailBycustomer");
		List<Sales> list =dao.selectDetailBycustomer(new Customer(12001));
		Assert.assertNotNull(list);
		for(Sales s : list) {
			System.out.println(s.toStringD());
		}
	}

	@Test
	public void testSelectDetailByProductAndCustoemr() {
		System.out.printf("%s()%n", "testSelectDetailByProductAndCustoemr");
		List<Sales> list = dao.selectDetailByProductAndCustoemr(new Customer(12001), new Product("PA"));
		Assert.assertNotNull(list);
		for(Sales s : list) {
			System.out.println(s.toStringD());
		}
	}

	@Test
	public void testInsertSales() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateSales() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteSales() {
		fail("Not yet implemented");
	}

}