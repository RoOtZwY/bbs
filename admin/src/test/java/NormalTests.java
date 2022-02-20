import com.rootzwy.bbs.admin.util.MD5Util;
import org.junit.jupiter.api.Test;

/**
 * @author zwy
 * @date 2022/2/19
 */
public class NormalTests {

    @Test
    public void testMd5() {
        System.out.println(MD5Util.md5("123456", "ud@dasdsaacsacasdij&"));
    }

}
