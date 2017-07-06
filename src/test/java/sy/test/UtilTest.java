package sy.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.intfocus.hdk.util.ComUtil;
import com.intfocus.hdk.util.JuheDemo;

public class UtilTest {

	@Test
	public void tet(){
		Map<String , String> params = new HashMap<String , String>();
        params.put("uuid","0123");
        params.put("keyid","null");
		try {
			JuheDemo.net("http://172.17.11.112:81/HDDataCenterSvr.dll/ShopVerify", params,"GET",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void comUtilDateFormat() {
		System.out.println(ComUtil.dateFormat("2017-05-15 16:57:10"));	
	}
	@Test
	public void apacheUitljoinTest(){
		//List<String> urls = new ArrayList<String>();
		System.out.println(ComUtil.savePicture(null, ""));
	}
}
