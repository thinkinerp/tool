package sy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.junit.Test;

import com.intfocus.hdk.util.JuheDemo;
import com.intfocus.hdk.vo.SalesData;

public class CheckTest {

//	@Test
//	public void test() {
//		JuheDemo.check("","");	
//	}

	@Test
	public void test() throws ParseException{
		SalesData sd = new SalesData();
		
		sd.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-04-23 14:09:00"));
		sd.setSalesAmount(123.4f);
		sd.setSalesDate("2017-04-23");
		sd.setStoreUuid("523");
		Map<String,String> mp = JuheDemo.uplaodSalesData(sd );
		System.out.println(mp.get("message") + mp.get("result"));
	}
	
}
