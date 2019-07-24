package com.front.web;

import com.bootdo.common.exception.RRException;
import com.bootdo.common.utils.R;
import com.bootdo.getuiutil.NihaodamaPush;
import com.bootdo.welcome.domain.MemberStudentDO;
import com.bootdo.welcome.service.MemberParentService;
import com.bootdo.welcome.service.MemberStudentService;
import com.front.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/f/user")
public class NihaodamaLoginController {


    @Autowired
    private MemberStudentService memberStudentService;

    @Autowired
    private MemberParentService memberParentService;

    @RequestMapping(value = "login")
    public R login(@RequestParam(value = "mobile", defaultValue = "") String mobile,
                   @RequestParam(value = "password", defaultValue = "") String password,
                   @RequestParam(value = "clientId", defaultValue = "")String clientId,
                   @RequestParam(value = "systemType", defaultValue = "-1")String systemType,
                   @RequestParam(value = "identify", defaultValue = "-1")Integer identify,
                   HttpServletRequest request) {

        try {
            Assert.isBlank(mobile, "请输入手机号");
            Assert.isBlank(password, "请输入密码");
        } catch (RRException e) {
            e.printStackTrace();
            return R.error("参数错误");
        }

        if (identify == 1) {

            // 设置为学生登录
            MemberStudentDO student = memberStudentService.getByMobile(mobile);
            // 进行别名的绑定
            Map<String, Object> bindResult = NihaodamaPush.bildAlias(student.getMobile(), clientId);
            if ((boolean)bindResult.get("result")) {
                if (systemType.equals("android")) {
                    student.setAndroidClientId(clientId);
                    memberStudentService.save(student);
                } else if (systemType.equals("ios")) {
                    student.setIosClientId(clientId);
                    memberStudentService.save(student);
                } else {

                }
            } else {
                System.out.println(bindResult.get("msg").toString());
            }

        } else if (identify == 2) {

        } else {
            return R.error("您还没有权限进行登录");
        }
        return R.ok();
    }

    @RequestMapping("/checkYes")
    @ResponseBody
    public R checkYes(@RequestParam("id") Long id, @RequestParam("templeteId")Integer templeteId){
        // 进行消息推送
        //得到手机号
        System.out.println("到这个接口了");

        MemberStudentDO studentDO = memberStudentService.get(id);
//        NihaodamaPush.pushMsgSetExpireTime(1, studentDO.getMobile(), studentDO.getAndroidClientId(), "这是标题", "这是内容" +
//                "\ndklasdjklasdjklaskdlajklfdsfskf" +
//                "fsdkfl;dskfl;dskf;\n" +
//                "fdsfmlkd;sfl;dskfl;s", true, 30);
        NihaodamaPush.pushMsgSetExpireTime(4, studentDO.getMobile(), studentDO.getIosClientId(), "这是标题", "这是内容" +
                "到家啊圣诞节阿克苏登记卡\n" +
                "djslakdjlasjdalksdjaklsdjklsa\n" +
                "fdsfsfklsdfjkljfkljef", true, 30);

        // 发送大字体消息
//        NihaodamaPush.pushMsgSetExpireTime(3, studentDO.getMobile(), studentDO.getAndroidClientId(), "这是标题", "这是内容" +
//                "到家啊圣诞节阿克苏登记卡\n" +
//                "djslakdjlasjdalksdjaklsdjklsa\n" +
//                "fdsfsfklsdfjkljfkljef大十九打开垃圾袋克拉斯大量的骄傲凉快圣诞节是拉科技", true, 60*24*3);
        // NihaodamaPush.pushMsgSetExpireTime(3, studentDO.getMobile(), studentDO.getAndroidClientId(), "这是标题", "这是内容");
        System.out.println("完成了消息的发送");
        return R.ok();
    }


}
