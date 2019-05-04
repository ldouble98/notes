# cglib 源码解析


DefaultListableBeanFactory



web容器 父容器 子容器

singletonObjects





resolveBeforeInstantiation


BeanWrapper    

AbstractAutoProxyCreator


BeanPostProcessor



https://www.bilibili.com/video/av45500833

@Configuration 注解笔记   cglib

bean - BeanDefinition - 


lite  full  


refresh()
方法： invokeBeanDefinitionRegistryPostProcessor（
    currentRegistryProcessors，registr   y
）
invokeBeanFactoryPostProcessors

BeanFactoryPostProcessor 后置处理

isFullConfigurationClass
isLiteConfigurationClass


判断是否被代理过
     EnhancedConfiguration.class.isAssignableFrom(configClass)
 