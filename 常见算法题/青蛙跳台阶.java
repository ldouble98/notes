/**
 * @Author: small_double
 * @Date: 19-4-8 下午4:35
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * 利用斐波那契数列求得
 */
public class 青蛙跳台阶 {
    public static int JumpFloor(int target) {
        int count = 0;
        if(target<2)
            return target;
        int f1=1;
        int f2=0;
        int f=0;
        for(int i=1;i<=target;++i)
        {
            f=f1+f2;
            f2=f1;
            f1=f;
        }
        return f;
    }
    public static void main(String[] args) {
        System.out.println(JumpFloor(10));
    }
}
