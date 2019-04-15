# springboot 笔记
# spring-mvc 官方文档

> The DispatcherServlet, as any Servlet, needs to be declared and mapped according to the
Servlet specification by using Java configuration 
or in web.xml. In turn, the DispatcherServlet uses Spring
configuration to discover the delegate components it needs
for request mapping, view resolution, exception handling, 
and more.

> The following example of the Java configuration registers 
and initializes the DispatcherServlet, 
which is auto-detected by the Servlet container 
(see Servlet Config):

# 模拟springboot的源码实现
> 关键实现代码  
```java
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletCxt) {

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
    }
}
```
# spring mvc 正常初始化过程

1. web.xml 
    
    - ContextLoaderListener 声明一个DispatcherServlet
    - spring init  applicationContext.xml
    - DispatcherServlet  ap 
    
2. applicationContext.xml
    - 扫包 @Service @Component 
    
3. spring-mvc.xml
    - 扫包 @Controller
    - 视图解析
    - json解析器

# springboot如何做到零配置
springboot要做到零配置就要将上面1-3替换掉

- 替换ContextLoaderListener初始化 spring init

         AnnotationConfigWebApplicationContext ac = 
         new AnnotationConfigWebApplicationContext();


- 替换 DispatcherServlet
    
     
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
            
        addServlet 相当于
             * 1,传统的web.xml
             *  <servlet>
             *      <servlet-class>sss</servlet-class>
             *  </servlet>
             * 2 @WebServlet()
- 扫包
    
    ```java
    @Configuration
    @ComponentScan("com.example.springboot.*")
    public class AppWebConfig implements WebMvcConfigurer {
    }
    ```
    声明一个类，将这个类注册到AnnotationConfigWebApplicationContext实现
    扫包的功能
         
         ac.register(AppConfig.class);
         ac.refresh();
- spring-mvc 扫包功能
    因为已经实现了@Controller的扫包功能，只需将ac配置到声明的DDispatcherServlet
    中就可以实现
    
        DispatcherServlet servlet = new DispatcherServlet(ac);

# springboot如何内嵌容器的
tomcat 是有java语言编写的，所以可以直接在java程序中声明一个Tomcat
            
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9898);
        // tomcat.addContext("/","");
        tomcat.addWebapp("/", "/home/lxs/study/tomcat/");
        tomcat.start();
        tomcat.getServer().await();

但是tomcat 为啥回去执行`MyWebApplicationInitializer`类下的`onStartup`方法呢？

这里涉及到servlet 3.0 的规范：
    
        javax.servlet.ServletContainerInitializer
    web容器加载了一个项目的时候，如果这个项目当中提供了一个接口ServletContainerInitializer，并且配置了这个接口
    那么就会调用这个接口的tomcat.startup(),
    这个方法会传入两个参数，其中一个是Set<Class<？> webAppInitializerClasses,webAppInitializerClasses表示实现了
    当前类上面注解@HandlesTypes的接口的所有实现类的集合
    循环set，调用startup()方法。
```java
@HandlesTypes(TestSpring.class)
public class TestServlet implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        for (Class<?> c : set) {
            System.out.println(c.getName());
        }
    }
}

```    

>出现这个错误是因为tomcat.addwebapp()默认认为你为加载jsp，org.apache.jasper.servlet.JspServlet 
将其改为 tomcat.addContext() 将会出现 MyWebApplicationInitializer类下onstartup()方法
不会自动执行

>改为手动执行
```java
public class SpringApplication {
    public static void run() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9898);
        // tomcat.addContext("/","");
        tomcat.addWebapp("/","");


        // spring init
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppWebConfig.class);
        ac.refresh();
        DispatcherServlet servlet = new DispatcherServlet(ac);

        // 解除耦合
        // tomcat init spring boot servletCxt ?
        Wrapper app = tomcat.addServlet("/", "app", servlet);
        app.addMapping(".do");
        app.setLoadOnStartup(1);
        
        // ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        // registration.setLoadOnStartup(1);
        // registration.addMapping("/");

        tomcat.start();
        tomcat.getServer().await();
    }
}
```
# 配置json解析器以及视图解析器
## fastjson
 传统的配置方式:  bean 配置在message-converters里面
```xml
<mvc:annotation-driven>
    <mvc:message-converters register-defaults="true">
        <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
            <property name="supportedMediaTypes" value="text/html;charset=UTF-8"/>
            <property name="features">
                <array>
                    <value>WriteMapNullValue</value>
                    <value>WriteNullStringAsEmpty</value>
                </array>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>

```
    
```java
@Configuration
@ComponentScan("com.example.springboot.*")
@EnableWebMvc // 部分功能调用WebMvcConfigurer接口下的所有方法
public class AppWebConfig implements WebMvcConfigurer {
    
    // @Bean 相当于传统的在xml上<bean></bean>
    // 这样写 bean并没有配置到message-converters里面
    // 所以要重写WebMvcConfigurer的extendMessageConverters方法
    
    @Bean
    public UserService service(){
        return new UserServiceImpl();
    }
    // list 专门存放解析 字符串 json jsp等的消息转换器
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter =
                new FastJsonHttpMessageConverter();
        converters.add(fastJsonHttpMessageConverter);

    }
    
}
```
- > 在AppWebConfig加上注解@EnableWebMvc并实现WebMvcConfigurer
- > @EnableWebMvc会使得spring在初始化时会调用



  


