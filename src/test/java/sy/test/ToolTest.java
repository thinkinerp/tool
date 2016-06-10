package sy.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wang.tools.dao.toolMapper;

import com.alibaba.fastjson.JSONObject;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ToolTest {
	Logger log = Logger.getLogger(this.getClass());
	@Resource private toolMapper tm;
	@Test
	public void test() {
		log.info(JSONObject.toJSONString(tm.getTableInfo("kpi_bonus_monthly")));
	}

}
