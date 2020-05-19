import java.util.HashMap;
import java.util.Map;

public class Dp {

    Map<Integer, Long> cache = new HashMap<>(); // memoization
    int invokes = 0;

    public long fib(int n) {
        System.out.println("Calculating fib for " + n);
        invokes++;
        if (n == 1 || n == 2) {
            return 1;
        } else {
            long res1 = fib(n-1);
            long res2 = fib(n-2);
            return res1 + res2;
        }
    }

    public long fibRecursiveWithMemoization(int n) {
        invokes++;
        System.out.println("Calculating fib for " + n);
        if (n == 1 || n == 2) {
            return 1;
        } else {
            if (cache.containsKey(n)) {
                return cache.get(n);
            } else {
                long res1 = fibRecursiveWithMemoization(n-1);
                long res2 = fibRecursiveWithMemoization(n-2);
                cache.put(n-1, res1);
                cache.put(n-2, res2);
                return res1 + res2;
            }
        }
    }


    public long fibDynamicProgramming(int n) {
        long[] arr = new long[n+1];
        //arr[0] = 1;
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }

        return arr[n];
    }

    public static void main(String[] args) {
        Dp dp = new Dp();
        long startMillis = System.currentTimeMillis();
        long ret = dp.fibRecursiveWithMemoization(30);
        long elaspedMillis = System.currentTimeMillis() - startMillis;

        System.out.println(ret);
        System.out.println(dp.invokes);
        System.out.println("Elaspsed millis " + elaspedMillis);
    }
}
