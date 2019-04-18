/**
 * @Author: small_double
 * @Date: 19-4-8 下午4:22
 */
public class 斐波那契数列 {

    public static int Fibonacci(int n) {
        if(n>39) return 0;
        int sum = 0;
        if(n == 1) return 1;
        else if(n == 0) return 0;
        else if(n == 2) return 1;
        else sum += Fibonacci(n-1)+Fibonacci(n-2);
        return sum;
    }

    /**
     * 非递归实现 ，使用循环，时间复杂度O（n），空间复杂度o（1）
     * @param n
     * @return
     */
    public int Fibonacci2(int n) {
        int preNum=1;
        int prePreNum=0;
        int result=0;
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        for(int i=2;i<=n;i++){
            result=preNum+prePreNum;
            prePreNum=preNum;
            preNum=result;
        }
        return result;

    }
    public static void main(String[] args) {
        System.out.println(Fibonacci(6));
    }
}
