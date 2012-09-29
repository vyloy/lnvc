package com.lorent.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:com/lorent/config/applicationContext-dao.xml",
		"classpath:com/lorent/config/applicationContext-service.xml",
		"classpath:com/lorent/config/applicationContext-security.xml",
		"classpath:com/lorent/config/applicationContext-action.xml",
		"classpath:com/lorent/config/applicationContext-xmlrpc.xml",
		"classpath:com/lorent/config/applicationContext-mcuweb.xml"
		})
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{
	
}
