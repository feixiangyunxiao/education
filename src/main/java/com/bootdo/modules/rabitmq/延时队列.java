package com.bootdo.modules.rabitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class 延时队列 {

    /**
     * 创建一个队列
     */
    public  static  void  createDLX(){
        //创建一个通道
        Channel channel = null;
        Connection connection = null;
        try {
            connection = RabbitMqConnectFactory.getConnection();
            channel = connection.createChannel();

            //创建一个交换器 direct模式下需要完全匹配 路由key
            channel.exchangeDeclare("test_exchange.dlx" , "direct" , true);
            //再创建一个交换器 fanout 模式 会把所有发过来的消息 转发到 绑定的路由中
            channel.exchangeDeclare( "test_exchange.normal" , "fanout" , true);
            channel.queueDeclare("test_queue.d1x",true, false, false, null);
            channel .queueBind( "test_queue.d1x" , "test_exchange.dlx" ,"routingkey");
            Map<String, Object> args = new HashMap<String, Object>( );
            channel.queueDeclare("test_queue.norma1",true, false, false, args);
            channel .queueBind( "test_queue.normal" , "test_exchange.normal" , "");
            channel.basicPublish( "test_exchange.normal" , "rk" ,
                    MessageProperties.PERSISTENT_TEXT_PLAIN , "dlx" .getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            RabbitMqConnectFactory.closeChange(channel);
            RabbitMqConnectFactory.closeConnection(connection);
        }
    }



    public static void main(String[] r){
        createDLX();
    }

}
