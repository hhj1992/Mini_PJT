package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.prouduct.ProductService;
import com.model2.mvc.service.user.UserService;

/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
								"classpath:config/context-transaction.xml" })
public class ProductServiceTest {
 
	// ==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("ProductServiceImpl")
	private ProductService productservice;

	//@Test
	public void testAddProduct() throws Exception {

		Product product = new Product();
		product.setProdName("��Ǳ�");
		product.setProdDetail("�˰�����");
		product.setManuDate("0315");
		product.setPrice(999);
		product.setFileName("����");

		productservice.addProduct(product);

		product = productservice.getProduct(10018);
		
		System.out.println("product:"+product);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
//		Assert.assertEquals("testUserId", user.getUserId());
//		Assert.assertEquals("testUserName", user.getUserName());
//		Assert.assertEquals("testPasswd", user.getPassword());
//		Assert.assertEquals("111-2222-3333", user.getPhone());
//		Assert.assertEquals("��⵵", user.getAddr());
//		Assert.assertEquals("test@test.com", user.getEmail());
	}

	//@Test
	public void testGetProduct() throws Exception {

		Product product = new Product();
		// ==> �ʿ��ϴٸ�...
//		user.setUserId("testUserId");
//		user.setUserName("testUserName");
//		user.setPassword("testPasswd");
//		user.setSsn("1111112222222");
//		user.setPhone("111-2222-3333");
//		user.setAddr("��⵵");
//		user.setEmail("test@test.com");

		product = productservice.getProduct(10020);

		// ==> console Ȯ��
		System.out.println("product:"+product);
		System.out.println(product.getProdNo());

		// ==> API Ȯ��
//		Assert.assertEquals("testUserId", user.getUserId());
//		Assert.assertEquals("testUserName", user.getUserName());
//		Assert.assertEquals("testPasswd", user.getPassword());
//		Assert.assertEquals("111-2222-3333", user.getPhone());
//		Assert.assertEquals("��⵵", user.getAddr());
//		Assert.assertEquals("test@test.com", user.getEmail());

//		Assert.assertNotNull(userService.getUser("user02"));
//		Assert.assertNotNull(userService.getUser("user05"));
	}

	//@Test
	public void testUpdateProduct() throws Exception {

		Product product = productservice.getProduct(10020);
//		Assert.assertNotNull(user);
//		
//		Assert.assertEquals("testUserName", user.getUserName());
//		Assert.assertEquals("111-2222-3333", user.getPhone());
//		Assert.assertEquals("��⵵", user.getAddr());
//		Assert.assertEquals("test@test.com", user.getEmail());

		product.setProdName("����");
		product.setProdDetail("����");
		product.setManuDate("1234");
		product.setPrice(5);
		product.setFileName("����");
		product.setProdNo(10020);

		productservice.updateProduct(product);

		product = productservice.getProduct(10020);
//		Assert.assertNotNull(user);

		// ==> console Ȯ��
		// System.out.println(user);

		// ==> API Ȯ��
//		Assert.assertEquals("change", user.getUserName());
//		Assert.assertEquals("777-7777-7777", user.getPhone());
//		Assert.assertEquals("change", user.getAddr());
//		Assert.assertEquals("change@change.com", user.getEmail());
	}

	// ==> �ּ��� Ǯ�� �����ϸ�....
	@Test
	public void testGetProductList() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productservice.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console Ȯ��
		// System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");
 
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productservice.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console Ȯ��
		// System.out.println(list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println("totalcount:"+totalCount);
	}
}
