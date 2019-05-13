package com.bootdo.modules.rabitmq.customerimp;

import com.bootdo.modules.rabitmq.RabbitMqConnectFactory;
import com.bootdo.modules.rabitmq.interfase.Customer;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author 郝江波
 * @date 2019/4/30 10:43
   队列消费者
 */
public abstract class MeConsumer implements Customer {
    //创建一个新的连接
    Connection connection = null;
    //创建一个通道
    Channel channel = null;

   public  void  linesennerQueue(final String queueName){
       try {
           connection = RabbitMqConnectFactory.getConnection();
           channel = connection.createChannel();
           System.err.println(queueName+"等待消息...");
           final Connection finalConnection = connection;
           final Channel finalChannel = channel;
           new Thread(){
               public  void run(){
                   //自动回复队列应答 -- RabbitMQ中的消息确认机制
                   try {
                        channel.basicConsume(queueName,
                               false,
                               new DefaultConsumer(finalChannel) {
                                   @Override
                                   public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                                       MeConsumer.this.handleDelivery(finalChannel, consumerTag, envelope, properties, body);
                                   }
                               });
                        for(;;);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }finally {
                       closeChange();
                   }
               }
           }.start();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

    /**
     * 关闭连接
     */
   public void closeChange(){
       RabbitMqConnectFactory.closeChange(channel);
       RabbitMqConnectFactory.closeConnection(connection);
   }


    /**
     * 子类实现这个抽象方法 就可以
     *
     *  String message = new String(body, "UTF-8");
     *             System.err.println("接收到的消息"+message);
     *             //手动确认 ack
     *             channel.basicAck(envelope.getDeliveryTag(),true);
     *             //拒绝消息   参数1 消息索引  参数2是否一次性提交之前的消息  参数3是否重新入队
     *             //finalChannel.basicNack(envelope.getDeliveryTag(),false,false);
     *
     *
     * @param channel
     * @param consumerTag
     * @param envelope
     * @param properties
     * @param body
     */
    public abstract void handleDelivery(Channel channel, String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body);

}
