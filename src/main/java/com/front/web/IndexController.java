package com.front.web;

import com.bootdo.common.redisutil.JedisUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.common.vo.JsonModel;
import com.bootdo.welcome.domain.*;
import com.bootdo.welcome.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/f/index")
public class IndexController {

    @Autowired
    private MycarouselService mycarouselService;

    @Autowired
    private SchoolNewsService schoolNewsService;

    // 得到轮播图
    @RequestMapping("getCarouselList")
    @ResponseBody
    public JsonModel getCarousel() {
        JsonModel resultModel = new JsonModel();
        Map<String, Object> map = new HashMap<>();
        map.put("offset",0);
        map.put("limit", 3);
        List<MycarouselDO> list = mycarouselService.list(map);
        resultModel.setCode(200);
        resultModel.setMessage("查询成功");
        resultModel.setData(list);
        return resultModel;
    }

    // 得到消息列表
    @RequestMapping("getNewsList")
    @ResponseBody
    public JsonModel getNewsList(HttpServletRequest request) {
        JsonModel resultModel = new JsonModel();
        Map<String, Object> map = new HashMap<>();
        String offset = request.getParameter("offset");
        String limit = request.getParameter("limit");
        if (StringUtils.isNotEmpty(offset) && StringUtils.isNotEmpty(limit)) {
            map.put("offset", Integer.parseInt(request.getParameter("offset")));
            map.put("limit", Integer.parseInt(request.getParameter("limit")));
        } else {
            map.put("offset", null);
            map.put("limit", null);
        }
        List<SchoolNewsDO> list = schoolNewsService.list(map);
        resultModel.setCode(200);
        resultModel.setMessage("查询成功");
        resultModel.setData(list);
        return resultModel;
    }
}
