#  
```
HashMap 存放键值對，put操作，先对key值进行hash，将hashcode对数组长度取模，
java中使用移位计算，效率更高，所以数组长度一般为2^n，不是n次方时容易导致数组部分位置不能访问，
浪费空间，而且冲突发生概率更大，hashmap处理冲突的方法是链表法，就是有不同的key值可能会有
相同的hash值，然后利用头插法插入链表，在put时会进行一个是否扩容的判断，线程不安全就是
因为这个扩容复制旧table时造成的，扩容时会有一个rehash的操作，多线程可能会死锁

get操作就是先hash，然后取出利用key.equals()比较

HashSet 不能重复元素，其构造方法就是初始化了一个hashmap，因为hashmap不能
存放相同的key值，所以hashset没有重复元素

遍历方式：
    keyset，entryset，
    jdk1.8 外层遍历 table，内层遍历链表或红黑树
    map.forEach((key,value)->{
        System.out.println("key=" + key + " value=" + value);
    });
currenthashmap 线程安全 jdk1.7由Segment与hashEntry数组组成 
由重入锁ReentrantLock实现 put操作volatile修饰value，但不能保证原子性

get操作： hash获取Segment，在hash定位到特定元素上

继承Thread类，

实现Runable接口，

是否可继承 是否可实现其他资源共享

线程池： ThreadPoolExecutor

线程间通信：wait（），notify（）

锁： synchronized：
        同步普通方法，锁的是当前对象。
        同步静态方法，锁的是当前 Class 对象。
        同步块，锁的是 () 中的对象。
        
        
spring 
    ioc 控制反转 
    创建对象由以前的程序员自己new 构造方法来调用，变成了交由Spring创建对象
    
    
    日志 权限 事务 
    @ProxytargetClass 指定代理类
    @EnableAspectAutoProxy
    AOP 面向切面编程  AspectJ
        
        织入
            增强： advices 
                增强类型：前置，后置，环绕（@Around） @Aspect增强器
            切点： JointPoint 一个target会有多个切点，
            切面： pointcut @pointcut（“execution（*包.*(..方法)）”） 
        
        ProceedingJoinPoint joinPoint;
        jointPoint.proceed();
        
        
        
        
        
        
        
        
        
        
        静态代理：
        动态代理：必须是接口的
        
        其实代理的一般模式就是静态代理的实现模式：首先创建一个接口(JDK代理都是面向接口的）
        ，然后创建具体实现类来实现这个接口，在创建一个代理类同样实现这个接口，
        不同之处在于，
        具体实现类的方法中需要将接口中定义的方法的业务逻辑功能实现，
        而代理类中的方法只要调用具体类中的对应方法即可，
        这样我们在需要使用接口中的某个方法的功能时直接调用代理类的方法即可，
        将具体的实现类隐藏在底层
        https://www.cnblogs.com/V1haoge/p/5860749.html
        
        cglib：cglib是对一个小而快的字节码处理框架 ASM 的封装。
            他的特点是继承于被代理类，这就要求被代理类不能被 final 修饰。
    
    Proxy.newProxyInstance() 
    参数：classLoad  数组 Class[] interfaces InvocationHandler 
    自定义 InvocationHandler 重写invoke方法
    
    字节吗数组：ProxyGenerate.generateProxyClass（“$Proxy”，new class[]{}）
    
    generateClassFile()
    
    
```
