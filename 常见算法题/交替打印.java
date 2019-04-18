/**
 * @Author: small_double
 * @Date: 19-4-8 下午3:10
 */

import java.io.*;

public class 交替打印 {
    public static int i = 1;
    public static volatile boolean flag = false;

    public static void main(String[] args) {

        File f = new File("test.txt");
        // FileWriter fr2 = new FileWriter(f,true);

        Thread thread1 = new Thread("thread1") {
            public void run() {
                while (i <= 50) {
                    // synchronized (f) {
                        if (!flag) {

                            try (FileWriter fr = new FileWriter(f, true)) {
                                // 以字符流的形式把数据写入到文件中
                                for (int j = 0; j < 5; j++) {
                                    String data = this.getName() + " -- " + (i++);

                                    System.out.println(data);
                                    char[] cs = data.toCharArray();
                                    fr.write(cs);
                                    fr.write("\n");
                                }
                                fr.close();
                                // f.wait();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            flag = true;
                        }
                    }
                }
            // }
        };
        thread1.start();
        Thread thread2 = new Thread("thread2") {
            public void run() {
                while (i <= 50) {
                    // synchronized (f) {
                        if (flag) {
                            try (FileWriter fr = new FileWriter(f, true)) {
                                // 以字符流的形式把数据写入到文件中
                                for (int j = 0; j < 5; j++) {
                                    String data = this.getName() + " -- " + (i++);
                                    System.out.println(data);
                                    char[] cs = data.toCharArray();
                                    fr.write(cs);
                                    fr.write("\n");
                                }
                                fr.close();
                                // f.notify();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            flag = false;

                        }
                    }
                }
            // }
        };
        thread2.start();
    }
}
