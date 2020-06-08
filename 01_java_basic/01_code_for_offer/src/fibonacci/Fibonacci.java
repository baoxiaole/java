package fibonacci;

/**
 * 斐波拉契数列
 */
public class Fibonacci {

    //递归的解法
    public static int fibonacci(int n){
        if(n < 0){
            throw new IllegalArgumentException("输入有误");
        }
        if(n <= 1){
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    //循环的解法
    public static int fibonacciByLoop(int n){
        if(n < 0){
            throw new IllegalArgumentException("输入有误");
        }
        if(n <= 1){
            return n;
        }
        int fibOne = 0;
        int fibTow = 1;
        int fibN = 0;
        for(int i=2;i<=n;i++){
            fibN = fibOne + fibTow;
            fibOne = fibTow;
            fibTow = fibN;
        }
        return fibN;
    }

    public static void main(String []args){
//        System.out.println(febonacci(-1));
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));
        System.out.println(fibonacci(5));
        System.out.println(fibonacciByLoop(5));
    }
}
