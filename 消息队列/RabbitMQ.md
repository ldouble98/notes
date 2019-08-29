# RabbitMQ
> 最常用的消息中间件
```
 生产者 交换机  队列 消费者
 
 与 ActiveMQ 拿到消息就直接放在队列等待消费者拿走不同， 
 Rabbit 拿到消息之后，会先交给 交换机 （Exchange）, 
 然后交换机再根据预先设定的不同绑定( Bindings )策略，来确定要发给哪个队列。
 如图所示，比起 ActiveMQ 多了 Exchange 和 Bindings。
 
```
 
 
