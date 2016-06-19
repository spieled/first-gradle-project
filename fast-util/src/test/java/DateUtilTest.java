import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yanqu on 2016/6/15.
 */
public class DateUtilTest {
    @Test
    public void format() throws Exception {
        String str = DateUtil.format();
        Assert.assertEquals("string length not valid", 19, str.length());
    }

}