package com.bootdo.modules.rabitmq.customerimp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
  * @author 郝江波

     示例demo

     注意  这个 类的  方法是一个组塞的

     需要一个线程来维护

     您可以使用线程组  但while(true)方法并不会释放 线程

  * @date 2019/4/30 10:49
  */
public class MyConsumerImp extends MeConsumer  {

    @Override
    public void linesennerQueue(String queueName) {
        super.linesennerQueue(queueName);
    }

    public void handleDelivery(Channel channel, String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
        try {
            String message = new String(body, "UTF-8");
            System.err.println("接收到的消息"+message);
            //手动确认 ack
            channel.basicAck(envelope.getDeliveryTag(),true);
            //拒绝消息   参数1 消息索引  参数2是否一次性提交之前的消息  参数3是否重新入队
            //finalChannel.basicNack(envelope.getDeliveryTag(),false,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] a){
       MyConsumerImp imp = new MyConsumerImp();
       imp.linesennerQueue("com.haojiangbo.squeue");
     }

}
