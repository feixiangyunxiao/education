import com.bootdo.BootdoApplication;
import com.bootdo.common.redisutil.JedisUtils;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.welcome.domain.MemberParentDO;
import com.bootdo.welcome.service.MemberParentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootdoApplication.class)
public class test {

    @Autowired
    private MemberParentService memberParentService;

    @Test
    public void test1() {
        System.out.println("这是在运行测试方法1了");
        System.out.println(memberParentService);
        MemberParentDO parentDO = memberParentService.get(1L);
        System.out.println(parentDO);
    }


    public static void main(String[] args) {
        System.out.println("这是个测试");
        // System.out.println(Integer.parseInt(""));
        String admin = MD5Utils.encrypt("admin", "123456");
        System.out.println(admin);
    }

    @Test
    public void getMap() {
        boolean result = JedisUtils.exists("openid");
        List<String> openid = JedisUtils.getList("openid");
        for (String id : openid) {
            System.out.println(id);
        }
    }
}
