package com.bootdo.modules.rabitmq.interfase;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;

/**
 * 公共接口  子类必须实现这个方法
* @author 郝江波
* @date 2019/5/8 15:12
*/
public interface Customer {
public   void handleDelivery(Channel channel, String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body);
}
