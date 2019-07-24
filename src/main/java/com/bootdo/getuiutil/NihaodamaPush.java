package com.bootdo.getuiutil;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.AbstractNotifyStyle;
import com.gexin.rp.sdk.template.style.Style0;
import com.gexin.rp.sdk.template.style.Style4;
import com.gexin.rp.sdk.template.style.Style6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.getuiutil.AppPush.houtainotificationTemplate;

public class NihaodamaPush {

    // 这是自己的项目id
    private static String appId = "0m6va7RCKWAEuANt9pcEW7";
    private static String appKey = "YBVbxwi1OT8CQ016Jhemm8";
    private static String masterSecret = "nXN4y52hts7oKZGEixtix4";
    private static String host = "http://sdk.open.api.igexin.com/apiex.htm";


    // 得到模板
    public static NotificationTemplate getNotificationTemplate(String msg) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(msg);
        return template;
    }

    // 得到普通消息模板
    public static NotificationTemplate notificationTemplate(String title, String msg) {

        NotificationTemplate template = getNotificationTemplate(msg);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        // 为模板设置样式
        template.setStyle(getStyle0(title, msg));
        return template;
    }

    // 得到透传消息模板
    public  static TransmissionTemplate transmissionTemplate(String content){
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);

        template.setTransmissionContent(content);

        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        //添加苹果信息
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setCategory("$由客户端定义");

        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg("这是内容"));

        //字典模式使用APNPayload.DictionaryAlertMsg
        //payload.setAlertMsg(getDictionaryAlertMsg());

        // 添加多媒体资源
        payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
                .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
                .setOnlyWifi(true));

        template.setAPNInfo(payload);

        return template;
    }


    // 得到普通消息模板带样式的
    /*
       flag : 代表的是个推提供的三个模板，分别是0， 4， 6，默认为0
            传0 代表了只用传入title和msg
            传4 代表了需要传入logo和banner
            传6 代表了需要传入title、msg、
     */
    public static NotificationTemplate notificationTemplate(String title, String msg, int flag) {
        NotificationTemplate template = getNotificationTemplate(msg);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        // 为模板设置样式

        switch (flag) {
            case 0 :
                template.setStyle(getStyle0(title, msg));
                break;
            case 4:
                template.setStyle(getStyle4("", ""));
                break;
            case 6:
                // 这里注意style6中所需参数比较多
                // 有三个style，一般是使用style2，后面使用什么样式，根据实际情况去改
                template.setStyle(getStyle6(title, msg, "", "", 2, "", ""));
                break;
            default:
                template.setStyle(getStyle0(title, msg));
                break;
        }

        return template;
    }

    // 得到带外部链接的template
    public static LinkTemplate linkTemplate(String title, String msg, String url) {

        // 设置APPID与APPKEY
        LinkTemplate linkTemplate = new LinkTemplate();
        linkTemplate.setAppkey(appKey);
        linkTemplate.setAppId(appId);

        linkTemplate.setStyle(getStyle0(title, msg));

        // 设置打开的网址地址
        linkTemplate.setUrl(url);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return linkTemplate;
    }


    public static void setStyle(NotificationTemplate template, Integer flag, String logo, String bannerUrl) {
        template.setStyle(getStyle4(logo, bannerUrl));
    }
    public static void setStyle(NotificationTemplate template, Integer flag, String title, String text, String logo, String logoUrl, Integer bigStyle, String bigImageUrl, String bannerUrl) {
        template.setStyle(getStyle6(title, text, logo, logoUrl, bigStyle, bigImageUrl, bannerUrl));
    }




    // style0和style1模式
    public static AbstractNotifyStyle getStyle0(String title, String msg) {
        Style0 style = new Style0();

        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(msg);

        // 配置通知栏图标
        style.setLogo("icon.png");

        // 配置通知栏网络图标
        style.setLogoUrl("");

        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        return style;
    }

    // style4模式
    public static AbstractNotifyStyle getStyle4(String logo, String bannerUrl) {
        Style4 style = new Style4();
        style.setBanner_url(bannerUrl);
        style.setLogo(logo);

        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        return style;
    }

    // style6模式

    public static AbstractNotifyStyle getStyle6(String title, String text, String logo, String logoUrl, int bigStyle, String bigImageUrl, String bannerUrl) {
        Style6 style = new Style6();

        style.setTitle(title);
        style.setText(text);
        style.setLogoUrl(logoUrl);
        style.setLogo(logo);

        switch (bigStyle) {
            case 1 :
                style.setBigStyle1(bigImageUrl);
                break;
            case 2 :
                style.setBigStyle2(text);
                break;
            case 3 :
                style.setBigStyle3(bigImageUrl, bannerUrl);
                break;
            default:
                style.setBigStyle2(text);
                break;
        }

        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        return style;
    }



    public static Map<String, Object> bildAlias(String Alias, String CID) {
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        IAliasResult bindSCid = push.bindAlias(appId, Alias, CID);
        System.out.println(appId);
        System.out.println(host);
        System.out.println(appKey);
        System.out.println(masterSecret);
        // System.out.println("绑定结果：" + bindSCid.getResult() + "错误码:" + bindSCid.getErrorMsg());
        Map<String, Object> result = new HashMap<>();
        result.put("result", bindSCid.getResult());
        result.put("msg", bindSCid.getErrorMsg());

        return  result;
    }

    /*
     * 可设置是否离线推送  以及离线推送时间
     * id 为1 ：代表普通的消息
     *    为2 ：代表透传消息模板，一般用于苹果的消息显示
     *    为3 ：代表的是带有样式的消息模板
//     *    为4 ：代表的是
      isExpireTime 一般设为true，允许其离线发送消息
      min ： 以分钟为单位
     */
    public static void pushMsgSetExpireTime(Integer id,String alias, String clientId, String title,String msg,boolean isExpireTime,Integer min){

        IGtPush push = new IGtPush(host, appKey, masterSecret);
        SingleMessage message = new SingleMessage();
        message.setOffline(isExpireTime);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(min * 60 * 1000);
        switch (id) {
            case 1:
                message.setData(notificationTemplate(title, msg));
                break;
            case 2:
                message.setData(transmissionTemplate(msg));
                break;
            case 3 :
                message.setData(notificationTemplate(title, msg, 6));
                break;
            case 5:
                message.setData(houtainotificationTemplate(title, msg));
                break;
            default:
                message.setData(notificationTemplate(title, msg));
                break;
        }
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(clientId);
        target.setAlias(alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }

    // android 进行按照别名进行集体的发送，设置离线时间为3天
    public static Map<String, Object> pushAndroidMsgSetExpireTimeList(int flag, List<Target> targetList, String title, String content) {

        IGtPush push = new IGtPush(host, appKey, masterSecret);
        ListMessage listMessage = new ListMessage();

        if (flag == 0) {
            listMessage.setData(notificationTemplate(title, content));
        }
        listMessage.setData(notificationTemplate(title, content));
        listMessage.setPushNetWorkType(0);
        // 设置消息离线，并设置离线时间
        listMessage.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        listMessage.setOfflineExpireTime(72 * 1000 * 3600);

        System.out.println("这是消息————listmessage");
        System.out.println(listMessage);
        System.out.println(listMessage.getData());
        String contentId = push.getContentId(listMessage);
        IPushResult iPushResult = push.pushMessageToList(contentId, targetList);

        return iPushResult.getResponse();
    }

    // 得到推送结果
    public static Map<String, Object> getResult(String contentId) {

        IGtPush push = new IGtPush(host,appKey, masterSecret);
        Map<String, Object> res = (Map<String, Object>) push.getPushResult(contentId).getResponse();
        for(Map.Entry<String,Object> entry: res.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        return res;
    }

}
