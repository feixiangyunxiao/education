package com.bootdo.modules.rabitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 连接工厂
  * @author 郝江波
  * @date 2019/5/8 15:14
  */
public class RabbitMqConnectFactory {

   //交换机名称
   public  static String exchangeName = "amq.topic";
   public  static final String MQPATH = "localhost";
   public  static String virtualHost = "songba";
   private static ConnectionFactory factory = null;

   public static void initFactory() throws IOException, TimeoutException {
       initFactory(MQPATH,"admin","admin",5672,"/");
   }

   /**
    * 单例模式  创建mq连接工厂
    * @param host
    * @param username
    * @param password
    * @param port
    * @throws IOException
    * @throws TimeoutException
    */
   public static void initFactory(String host, String username, String password, int port, String virtualHost) throws IOException, TimeoutException {
       if(null == factory){
           synchronized (RabbitMqConnectFactory.class){
               if(null == factory){
                   factory = new ConnectionFactory();
                   //设置RabbitMQ相关信息
                   factory.setHost(host);
                   factory.setUsername(username);
                   factory.setPassword(password);
                   factory.setPort(port);
                   factory.setVirtualHost(virtualHost);
               }
           }
       }
   }



   /**
    * 得到一个连接
    * @return
    */
   public static Connection getConnection(){

       //创建一个新的连接
       Connection connection = null;
       try {
           initFactory();
           return factory.newConnection();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (TimeoutException e) {
           e.printStackTrace();
       }
       return connection;
   }

   /**
    * 回收一个管道
    * @param channel
    */
   public static void  closeChange(Channel channel ){
       try {
           if(null != channel){
               System.out.println("管道不为空" + channel);
               channel.close();

           }
       } catch (IOException e) {
           e.printStackTrace();
       } catch (TimeoutException e) {
           e.printStackTrace();
       }
   }


   /**
    * 创建一个队列
    * @param queueName
    * @param routingKey
    */
   public  static  void  createQueue(String queueName, String routingKey, boolean durable){
       createQueue(exchangeName,queueName,routingKey,durable);
   }


   /**
    * 创建一个队列
    * @param queueName
    * @param routingKey
    */
   public  static  void  createQueue(String queueName, String routingKey){
       createQueue(exchangeName,queueName,routingKey,true);
   }


   /**
    * 延时队列的例子
    */
   static void createYanshiQueue(){
       //创建一个通道
       Channel channel = null;
       Connection connection = null;
       try {
           connection = RabbitMqConnectFactory.getConnection();
           channel = connection.createChannel();
           //durable 会持久化

           //创建交换机
           channel.exchangeDeclare("com.haojiangbo.s", "fanout",true);
           //创建队列
           channel.queueDeclare("com.haojiangbo.squeue",true, false, false, null);
           //绑定队列
           channel.queueBind("com.haojiangbo.squeue","com.haojiangbo.s","666ssss");


           Map args = new HashMap();
           //设置生存时间 5秒
           args.put("x-message-ttl" , 5000);
           //超时后移送到哪个交换器
           args.put("x-dead-letter-exchange", "com.haojiangbo.s");
           //超时移送到指定交换器的同时 制定key
           args.put("x-dead-letter-routing-key","666ssss");

           //创建交换机
           channel.exchangeDeclare("com.haojiangbo.m", "topic",true);
           //创建队列
           channel.queueDeclare("com.haojiangbo.mqueue",true, false, false, args);
           //绑定队列
           channel.queueBind("com.haojiangbo.mqueue","com.haojiangbo.m","*.mmm.*");


       } catch (IOException e) {
           e.printStackTrace();
       }finally {
           RabbitMqConnectFactory.closeChange(channel);
           RabbitMqConnectFactory.closeConnection(connection);
       }

   }


   /**
    * 创建一个队列
    * @param queueName
    * @param routingKey
    */
   public  static  void  createQueue(String exchangeName, String queueName, String routingKey, boolean durable){
       //创建一个通道
       Channel channel = null;
       Connection connection = null;
       try {
           connection = RabbitMqConnectFactory.getConnection();
           channel = connection.createChannel();
           //durable 会持久化
           //创建交换机
           channel.exchangeDeclare(exchangeName,  "topic",durable);
           //创建队列
           channel.queueDeclare(queueName,durable, false, false, null);
           //绑定队列
           channel.queueBind(queueName,exchangeName,routingKey);
       } catch (IOException e) {
           e.printStackTrace();
       }finally {
           RabbitMqConnectFactory.closeChange(channel);
           RabbitMqConnectFactory.closeConnection(connection);
       }
   }



   /**
    * 释放连接
    * @param connection
    */
   public static void  closeConnection(Connection connection){
       try {
           if(null != connection) {
               connection.close();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public static void main(String[] aa){
       //createYanshiQueue();
       createQueue("feichiqies","feichijiu", "*.feichijiu.*", true);
   }
}
