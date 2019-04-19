# CAS 笔记
 > CompareAndSet or CompareAndSwap 比较在交换
 利用CAS实现乐观锁，乐观锁是一种思想。CAS是这种思想的一种实现方式
# 操作数
CAS上有三个操作数：
 - V： 需要读写的内存位置
 - A： 旧的预期变量值
 - B： 拟写入的新的值
> 操作过程 在进行CompareAndSwap之前，将V与A比较，如果两者相等，则将
此位置的值更新为B，如果不相等，处理器不做任何操作；无论那种情况，都会放回V中
存放的值

我认为位置`V 应该包含值 A；如果包含该值，则将 B 放到这个位置；
否则，不要更改该位置，只告诉我这个位置现在的值即可`。

JNI java native interface 完成CPU指令的操作 native关键字本地方法

CAS通过调用JNI的代码实现的。

而compareAndSwapInt就是借助C来调用CPU底层指令实现的。


# CAS 缺点

1. ABA 问题

    比如说一个线程one从内存位置V中取出A，这时候另一个线程two也从内存中取出A，
    并且two进行了一些操作变成了B，然后two又将V位置的数据变成A，这时候线程one进行CAS操作发现内存中仍然是A，然后one操作成功。尽管线程one的CAS操作成功，但可能存在潜藏的问题
    > 解决方法：　从Java1.5开始JDK的atomic包里提供了一个类
    AtomicStampedReference来解决ABA问题
    
    首先检查`当前引用`是否等于`预期引用`，并且`当前标志`是否等于`预期标志`，
    如果`全部相等`，则以`原子方式`将该引用和该标志的值设置为给定的更新值

2. 循环时间长开销大
    
    自旋CAS（不成功，就一直循环执行，直到成功）
    
3.  只能保证一个共享变量的原子操作
    Java1.5开始JDK提供了AtomicReference类来保证引用对象之间的原子性，
    你可以把多个变量放在一个对象里来进行CAS操作。
    
# 与Synchronized比较
synchronized在jdk1.6之后，已经改进优化。
synchronized的底层实现主要依靠`Lock-Free`的队列，基本思路是`自旋后阻塞`，竞争切换后继续竞争锁，
稍微牺牲了公平性，但获得了高吞吐量。
在`线程冲突较少`的情况下，可以获得和CAS类似的性能；

`线程冲突严重`的情况下，性能远高于CAS。
    

参考博客：[https://www.cnblogs.com/qjjazry/p/6581568.html]


