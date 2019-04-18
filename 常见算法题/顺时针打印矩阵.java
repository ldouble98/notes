import java.util.ArrayList;

/**
 * @Author: small_double
 * @Date: 19-4-9 下午6:33
 */
public class 顺时针打印矩阵 {
    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList();

        return  result;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        ArrayList<Integer> result = new ArrayList();
        result = printMatrix(matrix);
        // printMatrix({{1},{2}})
        for (int i :result
             ) {
            System.out.println(i);
        }
    }
}
