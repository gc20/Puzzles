package hackercup.round1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class C {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(C.class.getSimpleName() + ".in"));
        PrintWriter out = new PrintWriter(new File(C.class.getSimpleName() + ".out"));
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String s = new C().solve(in);
            out.println(s);
            System.out.println(s);
        }
        out.close();
    }

    private String solve(Scanner in) {
        long n = in.nextLong();
        long k = in.nextLong();
        return "" + (calc(n, k) + 1);
    }

    private long calc(long n, long k) {
        //System.out.println(n);
        if (n == 1) return 0;
        if ((k + 1) * 2 <= n) {
            long p = n / (k + 1);
            long z = (k + 1) * p;
            long res = calc(n - p, k) + z;
            if (res >= n) {
                res -= n;
                res += res / k;
            }
            return res;
        } else {
            long z = k % n;
            long res = z + 1 + calc(n - 1, k);
            if (res >= n) res -= n;
            return res;
        }
    }
}