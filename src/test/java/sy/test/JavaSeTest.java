package sy.test;

import java.util.List;

import org.junit.Test;
import org.wang.tools.util.ColumnLoader;
import org.wang.tools.vo.ColProperty;

import com.alibaba.fastjson.JSONObject;

public class JavaSeTest {

	@Test
	public void test() {
		List<ColProperty> cols = ColumnLoader.sqlGenerator("importSome.xml", ColumnLoader.class, "sys_users");
	
	    System.out.println(JSONObject.toJSON(cols)) ;
	
	}
    @Test
	public void test2(){
		String s = "@d@ddddd wwwww";
		System.out.println( s.replaceAll("@d@","s"));
		System.out.println(s);
	}
}
