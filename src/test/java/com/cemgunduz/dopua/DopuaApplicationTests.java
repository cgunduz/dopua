package com.cemgunduz.dopua;

import com.cemgunduz.dopua.domain.history.persistence.HistoryDao;
import com.cemgunduz.dopua.domain.scum.persistence.ScumDao;
import com.cemgunduz.dopua.domain.scum.persistence.ScumDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DopuaApplication.class)
@WebAppConfiguration
public class DopuaApplicationTests {

	@Autowired
	ScumDao scumDao;

	@Autowired
	HistoryDao historyDao;

	@Test
	@Ignore
	public void contextLoads() {

		if(scumDao.exists(1)) return;

		scumDao.deleteAll();
		historyDao.deleteAll();


	}

	

}
