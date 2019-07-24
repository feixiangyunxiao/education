package com.bootdo.getuiutil;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class IosPushtoSingle {
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	 //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "MD2fFyXbja5yPUtM5Pood";
    private static String appKey = "zouc0VNQUp8zc574RnLr98";
    private static String masterSecret = "7anFUWQ1YcAU4VZMgii7nA";
    private static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    
    //static String CID = "491cddfbe09fba7c22bf973fb56ff7d0";//
   //static String CID2 = "f21d74c5522e20dbf72a9833f082f28e";//
    static String CID2 = "491cddfbe09fba7c22bf973fb56ff7d0";//
   
    public static void main(String[] args) throws InterruptedException {
    	
    	System.out.println(sendNotificationMsg(CID2, "这是标题啊","您有新订单了!哈哈哈1"));
    	//System.out.println(sendNotificationMsg(CID, "这是标题啊","您有新订单了!哈哈哈2"));
    	//System.out.println(sendTransmissionMsg(CID, "您有新订单了!哈哈哈"));

	}
    
    /**
     * 发送 透传消息模版
     * @return
     */
    public static String sendTransmissionMsg(String appcid,String content){
    	IGtPush push = new IGtPush(host, appKey, masterSecret);
        
    	TransmissionTemplate template = new TransmissionTemplate();
    	template.setAppId(appId);
    	template.setAppkey(appKey);
    	// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
    	template.setTransmissionType(1);
    	template.setTransmissionContent(content);
    	// 设置定时展示时间
    	// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
    	
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(appcid);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
           return ret.getResponse().toString();
        } else {
           return "服务器响应异常";
        }
    }
    
    

    
   public static String sendNotificationMsg(String appcid,String title,String content){
	   IGtPush push = new IGtPush(host, appKey, masterSecret);
	   TransmissionTemplate template = getTemplate(title,content);
       SingleMessage message = new SingleMessage();
       message.setOffline(true);
       // 离线有效时间，单位为毫秒，可选
       message.setOfflineExpireTime(24 * 3600 * 1000);
       message.setData(template);
       // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
       message.setPushNetWorkType(0);
       Target target = new Target();
       target.setAppId(appId);
       target.setClientId(appcid);
       //target.setAlias(Alias);
       IPushResult ret = null;
       try {
           ret = push.pushMessageToSingle(message, target);
       } catch (RequestException e) {
           e.printStackTrace();
           ret = push.pushMessageToSingle(message, target, e.getRequestId());
       }
       if (ret != null) {
           return ret.getResponse().toString();
       } else {
           return "服务器响应异常";
       }
   }
  
    
   public static TransmissionTemplate getTemplate(String title,String content) {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    template.setTransmissionContent(content);
	    template.setTransmissionType(2);
	    APNPayload payload = new APNPayload();
	    //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
	    payload.setAutoBadge("+1");
	    payload.setContentAvailable(1);
	    payload.setSound("default");
	    payload.setCategory("$由客户端定义");
	    //简单模式APNPayload.SimpleMsg
	    //payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));
	    //字典模式使用APNPayload.DictionaryAlertMsg
	    payload.setAlertMsg(getDictionaryAlertMsg(title,content));
	    template.setAPNInfo(payload);
	    return template;
	}

	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String title, String content){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(content);
	    //alertMsg.setActionLocKey("ActionLockey");
	    //alertMsg.setLocKey("LocKey");
	    //alertMsg.addLocArg("loc-args");
	    //alertMsg.setLaunchImage("launch-image");
	    // iOS8.2以上版本支持    
	    alertMsg.setTitle(title);
	    //alertMsg.setTitleLocKey("TitleLocKey");
	    //alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}
}
