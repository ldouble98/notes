# spring框架笔记
- Core technologies: dependency injection, events, resources, i18n, validation, data binding, type conversion, SpEL, AOP.

- Testing: mock objects, TestContext framework, Spring MVC Test, WebTestClient.

- Data Access: transactions, DAO support, JDBC, ORM, Marshalling XML.

- Spring MVC and Spring WebFlux web frameworks.

- Integration: remoting, JMS, JCA, JMX, email, tasks, scheduling, cache.

- Languages: Kotlin, Groovy, dynamic languages.
# 注入方法
## 注解方法
    @Autowired：自动装配
        - 默认yibyType的方式注入
        - 通过将@Autowired注解放在构造器上来完成构造器注入，默认构造器参数通过类型自动装配
        - 通过将@Autowired注解放在构造器上来完成字段注入
        - 通过将@Autowired注解放在方法上来完成方法参数注入
    @Resource
        - java java的注解，默认使用byName的方式，
## set方法注入

## 构造器注入
    
    - 使用带参的构造方法 constructor-arg 
    在XML文件中同样不用<property>的形式，而是使用<constructor-arg>标签
    ref属性同样指向其它<bean>标签的name属性
    -     
## 静态工厂注入
    
## 实例工厂注入