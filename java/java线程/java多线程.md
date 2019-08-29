# java 多线程
## **synchronized 关键字原理** 
本质对一个`对象监视器`进行获取

    同步普通方法，锁的是当前对象
    同步静态方法，锁的是当前class对象
    同步快，锁的是（）中的对象 
	
## **volatile关键字**
1 保证了一个变量对这个变量进行操作时的可见性
  
2 禁止指令重排序

3 示例
   * 3.1 **双重检查锁的单例模式**
``` java
    public class Singleton {
            private static volatile Singleton singleton;
    
            private Singleton() {
            }
    
            public static Singleton getInstance() {
                if (singleton == null) {
                    synchronized (Singleton.class) {
                        if (singleton == null) {
                            singleton = new Singleton();
                        }
                    }
                }
                return singleton;
            }
    
        } 
```
   * 3.2 **


## 示例java 多线程 向同一个文件写入数字
```java
	import java.io.*;
	public class Test {
	public static  int i = 1;
	public static volatile boolean flag = false;
	public static void main(String[] args) {

		File f = new File("/home/jyz/lee/java/test.txt");
        // FileWriter fr2 = new FileWriter(f,true);
		
        Thread thread1 = new Thread("thread1"){
        	public void run(){
        		while(i<=50){
        		synchronized(f){
        			if(!flag){
        				flag = true;
        		try (FileWriter fr = new FileWriter(f,true)) {
		            // 以字符流的形式把数据写入到文件中
        			for (int j = 0;j<5 ;j++ ) {
		            String data=this.getName()+" -- "+(i++);

		            System.out.println(data);
		            char[] cs = data.toCharArray();
		            fr.write(cs);
		            fr.write("\n");
	  				}
	  				fr.close();
	  				f.wait();
	       		} catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
	        	}catch (Exception e) {
	        		e.printStackTrace();
	        	}
	        }
	        }
        	}
	        }
        };
        thread1.start();
        Thread thread2 = new Thread("thread2"){
        	public void run(){
        		while(i<=50){
        			if(flag){
        				flag = false;
        		synchronized(f){
        		try (FileWriter fr = new FileWriter(f,true)) {
		            // 以字符流的形式把数据写入到文件中
        			for (int j = 0;j<5 ;j++ ) {
		            String data=this.getName()+" -- "+(i++);
		            System.out.println(data);
		            char[] cs = data.toCharArray();
		            fr.write(cs);
		            fr.write("\n");
	  				}
	  				fr.close();
	  				f.notify();
	       		} catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
	        	}catch (Exception e) {
	        		e.printStackTrace();
	        	}
	        }}
	    }
	        }
        };
        thread2.start();
	}

```
# 《java多线程核心技术》阅读笔记

1 this.interrupted() :测试当前线程是否已经是中断状态,
执行后具有将状态标志置清除为 false 的功能。

2 this.isInterrupted() :测试线程 Thread 对象是否已经
是中断状态,但不清除状态标志

    

