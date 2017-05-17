package sy.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.intfocus.hdk.util.ComUtil;

public class UtilTest {

	@Test
	public void comUtilDateFormat() {
		System.out.println(ComUtil.dateFormat("2017-05-15 16:57:10"));	
	}

}
