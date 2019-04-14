# ioc 控制反转
ioc -> 将原本由程序员 new class 创建类的步骤交给spring处理，而且是注入
好属性的类。

DI 依赖注入 Dependency Inject. 简单地说就是拿到的对象的属性，
已经被注入好相关值了，直接使用即可

    Class pClass1=Class.forName(className);
    Class pClass2=Hero.class;
    Class pClass3=new Hero().getClass();
    
