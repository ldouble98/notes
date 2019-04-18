```java
package kuaishou;

import java.util.Scanner;

/**
 * @Author: small_double
 * @Date: 19-4-13 下午3:39
 * 求n！的末尾的非零位
 */
public class Test2 {
    public static int fun(int n) {
        if (n < 0) {
            return -1;//传入的数据不合法
        }
        if (n == 0) {
            return 1;
        } else if (n == 1) {//递归结束的条件
            return 1;
        } else {
            return n * fun(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        System.out.println(fun(a));
        String res = Integer.toString(fun(a));
        for (int i = res.length()-1; i >0; i--) {
            if('0'!=res.charAt(i)){
                System.out.println(Integer.parseInt(String.valueOf(res.charAt(i))));
            }
        }

    }
}


```