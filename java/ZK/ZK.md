# ZooKeeper 笔记
## ZK
> 具有文件系统特征的数据库

> 配置管理，名字服务，提供分布式同步以及集群管理

```
    解决问题：
        1.数据一致性  > 强一致性 弱一致性  最终一致性
        2.发布与订阅功能
        3.
    领导者选举模式（事务id，follow  maxid）投票箱      半数机制  计数
    
    何时进行选举：
        1.系统开始集群
        2.领导者挂掉
        3.follow挂掉，领导者发现跟随者不足半数，则服务器会停止服务，重新选举
        
    当系统已经选好领导者时，新来的follow只能跟随领导者，即使他的事务id或者maxid是
    最新的.
     
    写操作：
        1.预提交
        2.follower发出ack，过半即commit
        3.通知所有的follower进行commit
    读操作：
        1.不用加锁
    
    推荐：集群的节点的个数是奇数    
    num > n /2 
    
    ZAB协议：
        Z ：数据一致性
        A ：可用性
        B ：分区容错性
            
```
