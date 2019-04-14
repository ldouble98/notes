# java 代理
    
    静态代理我们知根知底，我们知道要对哪个接口、哪个实现类来创建代理类，
    所以我们在编译前就直接实现与实现类相同的接口，
    直接在实现的方法中调用实现类中的相应（同名）方法即可；
    
    动态代理不同，我们不知道它什么时候创建，也不知道要创建针对哪个接口、
    实现类的代理类（因为它是在运行时因需实时创建的）。
    
    虽然二者创建时机不同，创建方式也不相同，但是原理是相同的
    不同之处仅仅是：静态代理可以直接编码创建，
    而动态代理是利用反射机制来抽象出代理类的创建过程。

> **_静态代理_**

假设有一个接口C，A实现C接口的功能，B也去实现C接口，在B的实现
方法中直接调用A的实现

代码如下：

接口C
```java
public interface UserService {
    public void login();
}
``` 
A实现C接口的功能
```java
@Service("service")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    @Override
    public void login() {

        System.out.println("false");
    }
}
```
B直接调用A的实现
```java
@Service
public class UserServiceProxyImpl implements UserService {

    private UserService service;

    public UserServiceProxyImpl() {
        service = new UserServiceImpl();
    }

    @Override
    public void login() {
        System.out.println("do something before login");
        service.login();
        System.out.println("do something after login");
    }
}
```
使用如下：
```java
public class StaticProxyTest {
    public static void main(String[] args) {
        UserService service = new UserServiceProxyImpl();
        service.login();
    }
}
```
> _**jdk动态代理**_

1 编写一个接口
```java
public interface UserService {
    public void login();
}

```
2 自定义一个InvocationHandler 重写Invoke方法 ,
    这里通过反射的方式获取类的方法（method.invoke()）
```java
public class ServiceInvocationHandler implements InvocationHandler {
    UserService userService;

    public ServiceInvocationHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("jdk do something before ");
        method.invoke(userService,objects);
        System.out.println("jdk do something after");
        return null;
    }
}
```
3 调用Proxy的静态方法 newProxyInstance 方法， 
  参数分别为类记载器，类接口数组new Class[]，InvocationHandler
```
  UserService service = (UserService) Proxy.newProxyInstance(
                  ProxyTest.class.getClassLoader(),new Class[]{UserService.class}, new ServiceInvocationHandler(new UserServiceImpl()));
```
4 用新生成的代理实例调用某个方法实现功能
```
service.login();
```
   
   + > 在 2中可以初始化原始的userService，然后在
   3中初始化代理实例的时候传入被代理类
   + > java动态代理，ProxyGenerator.generateProxyClass,
   生成一个二进制数组byte[]的代理类，
    。。。通过generateClassFile（），
   + > 通过反编译的结果发现生成的代理类继承了Proxy，所以java动态代理只能实现接口
   
5 反编译过程
```java
public class ProxyTest2 {
    public static void main(String[] args) throws Exception {
        byte[] bytes = ProxyGenerator.generateProxy();
        FileOutputStream fileOutputStream = new FileOutputStream("$Proxy1.class");
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();

    }
}
```
6 反编译生成类的代码
```java

```

> _**cglib代理**_
    
    cglib是对一个小而快的字节码处理框架 ASM 的封装。
    他的特点是继承于被代理类，这就要求被代理类不能被 final 修饰