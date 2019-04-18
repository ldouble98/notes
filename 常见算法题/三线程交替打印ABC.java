/**
 * @Author: small_double
 * @Date: 19-4-8 下午3:56
 */
public class 三线程交替打印ABC {


    public static class ThreadPrinter implements Runnable {
        private String name;
        private Object prev;
        private Object self;

        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {//多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                synchronized (prev) {    // 先获取 prev 锁
                    synchronized (self) {// 再获取 self 锁
                        System.out.print(name);
                        count--;

                        self.notifyAll();// 先释放 self，唤醒其他线程竞争self锁
                    }
                    try {
                        prev.wait();     // 再释放 prev，休眠等待唤醒
                        /**
                         * 注意的是notify()调用后，并不是马上就释放对象锁，而是在相应的synchronized(){}语句块执行结束，自动释放锁，
                         * JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
                         */
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A", c, a);
        ThreadPrinter pb = new ThreadPrinter("B", a, b);
        ThreadPrinter pc = new ThreadPrinter("C", b, c);
        new Thread(pa).start();
        Thread.sleep(10);
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }

}
