package com.bootdo.getuiutil;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;


public class AppPush {

    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "0m6va7RCKWAEuANt9pcEW7";
    private static String appKey = "YBVbxwi1OT8CQ016Jhemm8";
    private static String masterSecret = "nXN4y52hts7oKZGEixtix4";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    //static String CID = "88e367ad2b6107d2749a0e108719f7af";
    
    public static void pushMsg(Integer id,String appCid,String title,String msg){
    	
    	IGtPush push = new IGtPush(url, appKey, masterSecret);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(1 * 3600 * 1000);
        switch (id) {
		case 1:
			 message.setData(notificationTemplate(title, msg));
			// System.out.println("notificationTemplate:.........");
			break;
		case 4:
			 message.setData(transmissionTemplate( msg));
		//	 System.out.println("transmissionTemplate:.........");
			break;
		default:
			 message.setData(notificationTemplate(title, msg));
			break;
		}
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(appCid);
        //target.setAlias(Alias);
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
    
    
    
    
    public static void IosSendMessage(Integer id,String appCid,String title,String msg,boolean isExpireTime,Integer min){
    	id = 6;
    	
    	IGtPush push = new IGtPush(url, appKey, masterSecret);
        SingleMessage message = new SingleMessage();
        message.setOffline(isExpireTime);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(min * 60 * 1000);
        switch (id) {
		case 1:
			 message.setData(notificationTemplate(title, msg));
			// System.out.println("notificationTemplate:.........");
			break;
		case 4:
			 message.setData(transmissionTemplate(msg));
		//	 System.out.println("transmissionTemplate:.........");
			break;
		case 5:
			 message.setData(houtainotificationTemplate(title, msg));
		//	 System.out.println("transmissionTemplate:.........");
			break;
		case 6:
			 message.setData(houtainotificationTemplate(title, msg));
		//	 System.out.println("transmissionTemplate:.........");
			break;	
		default:
			 message.setData(notificationTemplate(title, msg));
			break;
		}
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(appCid);
        //target.setAlias(Alias);
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
    
    
    
    
    
    
    
    
	/*
	 * 可设置是否离线推送  以及离线推送时间
	 */
    public static void pushMsgSetExpireTime(Integer id,String appCid,String title,String msg,boolean isExpireTime,Integer min){
    	
    	IGtPush push = new IGtPush(url, appKey, masterSecret);
        SingleMessage message = new SingleMessage();
        message.setOffline(isExpireTime);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(min * 60 * 1000);
        switch (id) {
		case 1:
			 message.setData(notificationTemplate(title, msg));
			// System.out.println("notificationTemplate:.........");
			break;
		case 4:
			 message.setData(transmissionTemplate(msg));
		//	 System.out.println("transmissionTemplate:.........");
			break;
		case 5:
			 message.setData(houtainotificationTemplate(title, msg));
		//	 System.out.println("transmissionTemplate:.........");
			break;
		default:
			 message.setData(notificationTemplate(title, msg));
			break;
		}
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(appCid);
        //target.setAlias(Alias);
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
    
    
    
    
    
    /**
     * 点击通知打开应用模板
     * @param appId
     * @param appkey
     * @return
     */
    public static NotificationTemplate notificationTemplate(String title,String msg) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent("请输入您要透传的内容");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

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
        template.setStyle(style);

        return template;
    }
    
    /**
     * 点击通知打开网页模板
     * @param appId
     * @param appKey
     * @return
     */
    public static LinkTemplate linkTemplateDemo() {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl("http://www.getui.com");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
    
    /**
     * 点击通知弹窗下载模板
     * @param appId
     * @param appKey
     * @return
     */
    public static NotyPopLoadTemplate notyPopLoadTemplateDemo() {
        NotyPopLoadTemplate template = new NotyPopLoadTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置弹框标题与内容
        template.setPopTitle("弹框标题");
        template.setPopContent("弹框内容");
        // 设置弹框显示的图片
        template.setPopImage("");
        template.setPopButton1("下载");
        template.setPopButton2("取消");
        // 设置下载标题
        template.setLoadTitle("下载标题");
        template.setLoadIcon("file://icon.png");
        //设置下载地址        
        template.setLoadUrl("http://gdown.baidu.com/data/wisegame/80bab73f82cc29bf/shoujibaidu_16788496.apk");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
    
    
    /**
     * 透传消息模版
     * @return
     */
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
    
    
    /**
     * 透传消息模版   后台推送  点击后跳转应用首页
     * @return
     */
    public static NotificationTemplate houtainotificationTemplate(String title, String msg) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(msg);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

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
        template.setStyle(style);

        return template;
    }
    public static void stopTask() {
    /*	   //请输入你的appid
       String appId = "nSJtifqVSI7HkPrKHlxhD6";
        //请输入你的appkey
        String appkey = "WlZGdJlcUB8ds32Y2Thn91";
        //请输入你的masterSecret
        String mastersecret = "5vjiwMEaij5VvYf7VhlGM4";*/
        String taskid = "taskid-";
       // String host = "http://sdk.open.api.igexin.com/apiex.htm";
    	
    	
    	IGtPush push = new IGtPush(url, appKey, masterSecret);
        boolean result = push.stop(taskid);
        System.out.println(result);
    }
    
    
    
    
    
    
  public static void main(String[] args) {
	 //pushMsg(5,"f21d74c5522e20dbf72a9833f082f28e", "转账成功123sdfsd", "您转账50元sdgfsdfdsfdsfs"); 
	  String cid1 = "491cddfbe09fba7c22bf973fb56ff7d0";
	
	  String cid2 = "f21d74c5522e20dbf72a9833f082f28e";
	 
	  //ios
	  
	  //iOS&aaa
	  pushMsgSetExpireTime(4,cid1, "111", "aa&这是穿透消息",true,1); 
	  // pushMsg(1, cid1,"有新订单了", "点击查看详情");
	  //  IosPushtoSingle.sendNotificationMsg(cid1,"ios模板","hello ios");
	  //pushMsgSetExpireTime(5,cid1, "android模板", "hello ios ",true,1); 
	
	
	 
	  
	  //安卓
	  pushMsgSetExpireTime(4,cid2,"有新订单了","这是穿透消息",true,1);
	  pushMsg(1, cid2,"有新订单了", "点击查看详情");
	  //IosPushtoSingle.sendNotificationMsg(cid2,"ios模板","hello android ");
	  
	  
	  
	  
	  
  }
}

