package com.front.web;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.redisutil.JedisUtils;
import com.bootdo.common.utils.HttpUtil;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.WeiChatInfo;
import com.bootdo.common.vo.IdModel;
import com.bootdo.common.vo.JsonModel;
import com.bootdo.welcome.domain.CheckStudyNumberDO;
import com.bootdo.welcome.domain.MemberParentDO;
import com.bootdo.welcome.domain.MemberStudentDO;
import com.bootdo.welcome.service.CheckStudyNumberService;
import com.bootdo.welcome.service.MemberParentService;
import com.bootdo.welcome.service.MemberStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/f/user")
public class UserSetController {


    @Autowired
    private CheckStudyNumberService checkOpenidService;

    @Autowired
    private MemberStudentService memberStudentService;

    @Autowired
    private MemberParentService memberParentService;

    // 登录操作
    @RequestMapping("/getUserOpenid")
    @ResponseBody
    public JsonModel userLogin(String code) {

        JsonModel resultModel = new JsonModel();

        System.out.println("用户的code为 " + code);
        // 通过用户传过来的code值获取到用户的唯一标识，主要是将用户的openid返回给小程序端，为了下一次的登录做准备
        try {
            System.out.println("https://api.weixin.qq.com/sns/jscode2session?appid=" + WeiChatInfo.appid + "&secret=" + WeiChatInfo.secret + "&js_code=" + code + "&grant_type=" + WeiChatInfo.grant_type);
            String result = HttpUtil.doGet("https://api.weixin.qq.com/sns/jscode2session?appid=" + WeiChatInfo.appid + "&secret=" + WeiChatInfo.secret + "&js_code=" + code + "&grant_type=" + WeiChatInfo.grant_type);
            IdModel idModel = JSON.parseObject(result, IdModel.class);

            // 通过用户的code获取到用户的openid，然后将其返回到前台
            resultModel.setCode(200);
            resultModel.setMessage("成功了");
            List list = new ArrayList();
            list.add(idModel.getOpenid());
            resultModel.setData(list);
        } catch (Exception e) {
            resultModel.setCode(201);
            resultModel.setMessage("失败了");
            e.printStackTrace();
        }
       return resultModel;
    }

    // 进行用户的登录前操作
    @RequestMapping("userLogin")
    @ResponseBody
    public JsonModel checkHaveUser(Integer studyNumber, String openid, String password, Integer type) {
        System.out.println("学号为" + studyNumber);
        System.out.println("openid为" + openid);
        System.out.println("密码" + password);
        System.out.println("类别为" + type);
        JsonModel resultModel = new JsonModel();
        List list = new ArrayList<>();

        // 根据用户的学号进行redis的查询，如果有就得到用户的身份和号码

        boolean exists = JedisUtils.exists(studyNumber.toString());
        if (exists) {
            // 用户存在，得到用户的学号和身份
            List<String> listRedis = JedisUtils.getList(studyNumber.toString());

            if ("0".equals(listRedis.get(1))) {
                // 为0代表学生，然后得到学生对象，验证密码是否正确
                MemberStudentDO byOpenid = memberStudentService.getByStudyNumber(openid, Integer.parseInt(listRedis.get(0)));
                String sqlPassword = MD5Utils.encrypt(password);
                if (Objects.equals(password, sqlPassword)) {
                    resultModel.setCode(200);
                    resultModel.setMessage("查询成功");
                    list.add(byOpenid);
                    resultModel.setData(list);
                } else {
                    resultModel.setCode(201);
                    resultModel.setMessage("用户名或密码错误");
                }

            } else {

                // 否则为家长，保存用户的openid，并且验证用户名和密码

                MemberParentDO byOpenid = memberParentService.getByStudyNumber(openid, Integer.parseInt(listRedis.get(0)));
                String sqlPassword = MD5Utils.encrypt(byOpenid.getPassword());
                if (Objects.equals(sqlPassword, password)) {
                    resultModel.setCode(200);
                    resultModel.setMessage("查询成功");
                    list.add(byOpenid);
                    resultModel.setData(list);
                } else {
                    resultModel.setCode(201);
                    resultModel.setMessage("用户名或者密码错误");
                }
            }
        } else {
            // redis中不存在，可能是redis挂了，也可能是真的没有
            if (type == 0) {
                MemberStudentDO byStudyNumber = memberStudentService.getByStudyNumber(openid, studyNumber);
                if (Objects.equals(null, byStudyNumber)) {
                    resultModel.setCode(201);
                    resultModel.setMessage("用户不存在");
                } else {
                    List redisList = new ArrayList();
                    redisList.add(studyNumber.toString());
                    redisList.add(type.toString());
                    JedisUtils.setList("studyNumber", redisList, -1);

                    resultModel.setCode(200);
                    resultModel.setMessage("查询成功");
                    list.add(byStudyNumber);
                    resultModel.setData(list);
                }
            } else {

            }
           /* CheckStudyNumberDO byOpenid = checkOpenidService.getByOpenid(openid);
            if (null == byOpenid) {
                CheckStudyNumberDO checkStudyNumberDO = new CheckStudyNumberDO();
                checkStudyNumberDO.setOpenId(openid);
                checkStudyNumberDO.setStatus(0);
                checkOpenidService.save(checkStudyNumberDO);
                resultModel.setCode(201);
                resultModel.setMessage("请完善个人信息");
            } else {
                if (byOpenid.getStatus() == 0) {
                    resultModel.setCode(201);
                    resultModel.setMessage("请完善个人信息");
                } else {
                    Integer type = byOpenid.getType();
                    if ("0".equals(type)) {
                        MemberStudentDO studentDO = memberStudentService.getByStudyNumber(byOpenid.getOpenId());
                        resultModel.setCode(200);
                        resultModel.setMessage("查询成功");
                        list.add(studentDO);
                        resultModel.setData(list);
                    } else {
                        MemberParentDO parentDO = memberParentService.getByOpenid(byOpenid.getOpenId());
                        resultModel.setCode(200);
                        resultModel.setMessage("查询成功");
                        list.add(parentDO);
                        resultModel.setData(list);
                    }
                }
            }*/
        }
        return resultModel;
    }

    // 完善个人信息
    //
    @RequestMapping("completeUser")
    @ResponseBody
    public JsonModel completeUser(@RequestParam("type")Integer type, @RequestParam("openid")String openid, @RequestParam("number")Integer number,  MemberParentDO memberParentDO) {

        JsonModel resultModel = new JsonModel();
        List list = new ArrayList<>();

        MemberParentDO parentToSql = new MemberParentDO();
        MemberStudentDO studentToSql = new MemberStudentDO();

        if (type == 1) {
            parentToSql = memberParentDO;
            parentToSql.setParOpenid(openid);
            parentToSql.setParNumber(number);
        } else {
            studentToSql.setId(memberParentDO.getId());
            studentToSql.setHeadPhoto(memberParentDO.getHeadPhoto());
            studentToSql.setNickName(memberParentDO.getNickName());
            studentToSql.setSignature(memberParentDO.getSignature());
            studentToSql.setSpare(memberParentDO.getSpare());
            studentToSql.setStuNumber(number);
            studentToSql.setStuOpenid(openid);
        }

        // 校验阶段，传入的openid是否为空，是否已经在check表中存在，如果不存在就直接返回一个错误值

        // elseif 判断是不是在check表中的状态值，如果已经存在了就执行更新操作，否则执行新增操作
        CheckStudyNumberDO byOpenid = checkOpenidService.getByOpenid(openid);
        if (Objects.equals(null, byOpenid)) {
            resultModel.setCode(201);
            resultModel.setMessage("请先进行登录操作");
            return resultModel;
        } else if (byOpenid.getStatus() == 0){

            // 更新操作
            // 判断传入的type值，如果是0代表是学生，name就将实体类中的各个属性重新放到一个学生的实体类中，然后保存数据库
            // 如果是1的话，代表是家长，那么就将实体类的openid进行赋值1，并且保存到数据库中
            if (type == 1) {
                memberParentService.update(parentToSql);
            } else {
                memberStudentService.update(studentToSql);
            }
        } else {
            // 新增操作
            // 进行添加到对应的表的操作，然后将数据更新到redis，然后将状态置为1
            if (type == 1) {
                memberParentService.save(parentToSql);
                CheckStudyNumberDO checkStudyNumberDO = new CheckStudyNumberDO();
                checkStudyNumberDO.setOpenId(openid);
                checkStudyNumberDO.setStatus(1);
                checkOpenidService.update(checkStudyNumberDO);
            } else {
                memberStudentService.save(studentToSql);
            }
        }
        return null;
    }

    @RequestMapping("test")
    @ResponseBody
    public JsonModel test(@RequestParam("type")Integer type) {
        System.out.println(type);
        return  null;
    }
}
