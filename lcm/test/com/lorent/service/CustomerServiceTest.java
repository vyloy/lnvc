package com.lorent.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.lorent.model.CustomerBean;

@ContextConfiguration(locations={"../config/applicationContext-dao.xml",
		"../config/applicationContext-service.xml"})
public class CustomerServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private CustomerService service;
	
	@Before
	public void before(){

		
	}
	
	@Test
	public void testCanRemoveCustomer(){
		String sql = "insert into sys_user(user_name, customer_id, status) values('rgm', 99, 1)";
		this.simpleJdbcTemplate.getJdbcOperations().update(sql);
		
		List<CustomerBean> customers = new ArrayList<CustomerBean>();
		CustomerBean customer = new CustomerBean();
		customer.setId(99);
		customers.add(customer);
		boolean result = service.canRemoveCustomer(customers);
		Assert.assertEquals(false, result);
	}
	
//	@Test
//	public void testCreateCustomer(){
//		CustomerBean customer = new CustomerBean();
//		customer.setCustomerName("aaaaaaaaaaa");
//		try{
//			service.createCustomer(customer);
//		}catch(Exception e){
//			Assert.fail(e.getMessage());
//		}
//		String sql = "select count(*) from sys_customer where customer_name = 'aaaaaaaaaaa'";
//		long c = this.simpleJdbcTemplate.getJdbcOperations().queryForLong(sql);
//		Assert.assertEquals(1, c);
//		
//	}
}
