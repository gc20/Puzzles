import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BonusAssignments implements Runnable {
    static final long MODULO = 1000000007;

    private void solve() throws IOException {
        int nt = nextInt();
        int[] divisor = new int[1000001];
        for (int i = 2; i * i < divisor.length; ++i) {
            if (divisor[i] == 0) {
                divisor[i] = i;
                for (int j = i * i; j < divisor.length; j += i)
                    if (divisor[j] == 0)
                        divisor[j] = i;
            }
        }
        for (int i = 2; i < divisor.length; ++i)
            if (divisor[i] == 0) divisor[i] = i;
        int[] moebius = new int[1000001];
        moebius[1] = 1;
        for (int i = 2; i < divisor.length; ++i) {
            int tmp = i;
            int by = divisor[i];
            int pw = 0;
            while (tmp % by == 0) {
                ++pw;
                tmp /= by;
            }
            if (pw == 0) throw new RuntimeException();
            if (pw == 1) {
                moebius[i] = -moebius[tmp];
            } else if (pw > 1) {
                moebius[i] = 0;
            }
        }

        for (int it = 0; it < nt; ++it) {
            int n = nextInt();
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            int d = nextInt();
            long res = 0;
            for (int k = 1; k <= a || k <= b || k <= c || k <= d; ++k) {
                int koef = moebius[k];
                if (koef == 0) continue;
                res = (res + koef * doit(n, (a + k - 1) / k, b / k, (c + k - 1) / k, d / k) + MODULO) % MODULO;
            }
            writer.println(res);
        }
    }

    private long doit(int n, int a, int b, int c, int d) {
        if (b >= d) {
            b = d;
        }
        if (c <= a) {
            c = a;
        }
        if (a > b)
            return 0;
        if (c > d)
            return 0;
        return (doit2(n, a, d, c, d) - doit2(n, b + 1, d, c, d) + MODULO) % MODULO;
    }

    private long doit2(int n, int a, int b, int c, int d) {
        if (b != d) throw new RuntimeException();
        if (b >= d) {
            b = d;
        }
        if (c <= a) {
            c = a;
        }
        if (a > b)
            return 0;
        if (c > d)
            return 0;
        return (doit3(n, a, d, a, d) - doit3(n, a, c - 1, a, c - 1) + MODULO) % MODULO;
    }

    private long doit3(int n, int a, int b, int c, int d) {
        if (a != c || b != d) throw new RuntimeException();
        if (b >= d) {
            b = d;
        }
        if (c <= a) {
            c = a;
        }
        if (a > b)
            return 0;
        if (c > d)
            return 0;
        long z = (b - a + 1);
        return pow(z, n);
    }

    private long pow(long z, int n) {
        if (n == 0)
            return 1;
        if (n % 2 == 0) {
            return pow((z * z) % MODULO, n / 2);
        }
        return pow(z, n - 1) * z % MODULO;
    }

    public static void main(String[] args) {
        new BonusAssignments().run();
    }

    BufferedReader reader;
    StringTokenizer tokenizer;
    PrintWriter writer;

    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
            writer = new PrintWriter(System.out);
            solve();
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }
}
