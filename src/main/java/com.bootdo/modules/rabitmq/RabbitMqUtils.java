package com.bootdo.modules.rabitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * haojiangbo
 */
public class RabbitMqUtils {



    /**
     * 发送消息
     * @param roteName
     * @param message
     */
    public static void sendMessage(String roteName , byte[] message){
        sendMessage(RabbitMqConnectFactory.exchangeName,roteName,message);
    }


    /**
     * 发送消息
     * @param roteName
     * @param message
     */
    public static void sendMessage(String exchangeName, String roteName , byte[] message){
        //创建一个通道
        Channel channel = null;
        Connection connection = null;
        try {
            connection = RabbitMqConnectFactory.getConnection();
            channel = connection.createChannel();
            //发送消息到队列中
            channel.basicPublish(exchangeName, roteName ,  MessageProperties.PERSISTENT_TEXT_PLAIN, message);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            RabbitMqConnectFactory.closeChange(channel);
            RabbitMqConnectFactory.closeConnection(connection);
        }
    }


    /**
     * 发送消息
     * @param roteName
     * @param message
     */
    public static void sendMessage(String exchangeName, String roteName, byte[] message, ConfirmListener confirmListener){
        //创建一个通道
        Channel channel = null;
        Connection connection = null;
        try {
            connection = RabbitMqConnectFactory.getConnection();
            channel = connection.createChannel();
            // 开启发送方确认模式
            channel.confirmSelect();
            /**
             * mandatory：true：如果exchange根据自身类型和消息routeKey无法找到一个符合条件的queue，
             * 那么会调用basic.return方法将消息返还给生产者。false：出现上述情形broker会直接将消息扔掉
             * immediate：true：如果exchange在将消息route到queue(s)时发现对应的queue上没有消费者，
             * 那么这条消息不会放入队列中。当与消息routeKey关联的所有queue(一个或多个)都没有消费者时，
             * 该消息会通过basic.return方法返还给生产者。
             * BasicProperties ：需要注意的是BasicProperties.deliveryMode，0:不持久化 1：持久化
             * 这里指的是消息的持久化，配合channel(durable=true),queue(durable)可以实现，即使服务器宕机，
             * 消息仍然保留
             * 简单来说：mandatory标志告诉服务器至少将该消息route到一个队列中，
             * 否则将消息返还给生产者；
             * immediate标志告诉服务器如果该消息关联的queue上有消费者，
             * 则马上将消息投递给它，如果所有queue都没有消费者，
             * 直接把消息返还给生产者，不用将消息入队列等待消费者了。
             */
            channel.basicPublish(exchangeName, roteName,true,  MessageProperties.PERSISTENT_TEXT_PLAIN, message);
            //异步监听确认和未确认的消息
            channel.addConfirmListener(confirmListener);
            /*new ConfirmListener() {
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("未确认消息，标识：" + deliveryTag);
                }
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println(String.format("已确认消息，标识：%d，多个消息：%b", deliveryTag, multiple));
                }
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            RabbitMqConnectFactory.closeChange(channel);
            RabbitMqConnectFactory.closeConnection(connection);
        }
    }


  public  static void main(String[] athgs){
      //RabbitMqConnectFactory.createQueue("haojiangbo","*.haojiangbo.*");
      /*   RabbitMqUtils.sendMessage("aa*.mqtest.zzz", "123".getBytes());*/
      RabbitMqUtils.sendMessage("amq.topic","aa.haojiangbo.zzz", "666".getBytes());


  }


}
