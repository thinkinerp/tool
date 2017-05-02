package sy.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import org.junit.Test;

import com.intfocus.hdk.util.StaticVariableUtil;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/applicationContext.xml")
public class CheckTest {

//	@Test
//	public void test() {
//		JuheDemo.check("","");	
//	}
    private   static  String param1;    
    private   static  String param2;    
    
    @Test
    public void testStatic(){
    	System.out.println(StaticVariableUtil.APPID);
    	System.out.println(StaticVariableUtil.BASE_URL);
    }
    
	@Test
	public void test() throws ParseException{

		Properties prop =  new  Properties();
	        
	        try  {    
	            InputStream in = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/weixinInfo.properties" ));    
	            prop.load(in);    
	            param1 = prop.getProperty( "appid" ).trim();    
	            param2 = prop.getProperty( "secret" ).trim();    
	            System.out.println(param1);
	            System.out.println(param2);
	        }  catch  (IOException e) {    
	            e.printStackTrace();    
	        }    
//		SalesData sd = new SalesData();
//		
//		sd.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-04-23 14:09:00"));
//		sd.setSalesAmount(123.4f);
//		sd.setSalesDate("2017-04-23");
//		sd.setStoreUuid("523");
//		Map<String,String> mp = JuheDemo.uplaodSalesData(sd );
//		System.out.println(mp.get("message") + mp.get("result"));
	}

}