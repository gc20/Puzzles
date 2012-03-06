import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class ScottTrick implements Runnable {
    private void solve() throws IOException {
        int nt = nextInt();
        for (int it = 0; it < nt; ++it) {
            long p = nextLong();
            long l = nextLong();
            if (l >= p) l = p;
            long[] s1 = readSeq((int) p);
            long[] s2 = readSeq((int) p);
        /*Random random = new Random(4923943159634165431L);
        while (true) {
            long p = random.nextInt(2500) + 2;
            while (!BigInteger.valueOf(p).isProbablePrime(100))
                ++p;
            long l = random.nextInt((int) p) + 1;
            long[] s1 = new long[(int) p];
            long[] s2 = new long[(int) p];
            for (int i = 0; i < p; ++i) {
                s1[i] = random.nextInt(100000000);
                s2[i] = random.nextInt(100000000);
            }*/
            long res = doit(s1, s2, l, p);
            /*long res2 = solveSlow(s1, s2, l, p);
            if (res != res2)
                throw new RuntimeException();
            System.out.print(".");
        }     */
            writer.println(res);
        }
    }

    private long doit(long[] s1, long[] s2, long l, long p) {
        long res = 0;
        for (int i = 0; i < p; ++i) {
            res += s1[0] * s2[i];
            if (i > 0) res += s1[i] * s2[0];
        }
        long tmp = p - 1;
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 2; i <= tmp; ++i) {
            if (tmp % i == 0) {
                while (tmp % i == 0) tmp /= i;
                primes.add(i);
            }
        }
        long generator = 1;
        while (!isGenerator(generator, p, primes)) {
            ++generator;
            if (generator >= p) throw new RuntimeException();
        }
        long[] discrete = new long[(int) p];
        long[] pows = new long[(int) (p - 1)];
        Arrays.fill(discrete, -1);
        long pw = 1;
        for (int i = 0; i < p - 1; ++i) {
            pows[i] = pw;
            if (discrete[(int) pw] != -1) throw new RuntimeException();
            discrete[(int) pw] = i;
            pw = (pw * generator) % p;
        }
        long[] poly1 = new long[(int) p - 1];
        for (int i = 1; i < p; ++i)
            poly1[(int) discrete[i]] += s1[i];
        long[] poly2 = new long[(int) p - 1];
        for (int i = 1; i < p; ++i)
            poly2[(int) discrete[i]] += s2[i];
        long[] poly = quickPolyMul(poly1, poly2);
        for (int i = 0; i < poly.length; ++i) {
            long what = pows[(int) (i % (p - 1))];
            if (what < l)
                res += poly[i];
        }
        return res;
    }

    private long[] quickPolyMul(long[] poly1, long[] poly2) {
        if (poly1.length < 10) {
            long[] res = new long[poly1.length + poly2.length];
            for (int i1 = 0; i1 < poly1.length; ++i1)
                for (int i2 = 0; i2 < poly2.length; ++i2)
                    res[i1 + i2] += poly1[i1] * poly2[i2];
            return res;
        }
        int half = (poly1.length + 1) / 2;
        long[] a1 = new long[half];
        long[] b1 = new long[half];
        System.arraycopy(poly1, 0, b1, 0, half);
        System.arraycopy(poly1, half, a1, 0, poly1.length - half);
        long[] a2 = new long[half];
        long[] b2 = new long[half];
        System.arraycopy(poly2, 0, b2, 0, half);
        System.arraycopy(poly2, half, a2, 0, poly2.length - half);
        long[] c1 = add(a1, b1);
        long[] c2 = add(a2, b2);
        long[] a1a2 = quickPolyMul(a1, a2);
        long[] b1b2 = quickPolyMul(b1, b2);
        long[] c1c2 = quickPolyMul(c1, c2);
        long[] res = new long[a1a2.length + 2 * half];
        for (int i = 0; i < a1a2.length; ++i) {
            res[i + 2 * half] += a1a2[i];
            res[i + half] -= a1a2[i];
        }
        for (int i = 0; i < b1b2.length; ++i) {
            res[i] += b1b2[i];
            res[i + half] -= b1b2[i];
        }
        for (int i = 0; i < c1c2.length; ++i) {
            res[i + half] += c1c2[i];
        }
        return res;
    }

    private long[] add(long[] a2, long[] b2) {
        long[] res = new long[a2.length];
        for (int i = 0; i < a2.length; ++i)
            res[i] = a2[i] + b2[i];
        return res;
    }

    private boolean isGenerator(long generator, long p, List<Integer> primes) {
        if (pow(generator, p - 1, p) != 1) throw new RuntimeException();
        for (int pr : primes) {
            long cur = pow(generator, (p - 1) / pr, p);
            if (cur == 1) return false;
        }
        return true;
    }

    private long pow(long a, long k, long modulo) {
        if (k == 0) return 1 % modulo;
        if (k % 2 == 0) return pow((a * a) % modulo, k / 2, modulo);
        return (pow(a, k - 1, modulo) * a % modulo);
    }

    private long solveSlow(long[] s1, long[] s2, long l, long p) {
        long[] ps1 = new long[s1.length + 1];
        long[] ps2 = new long[s2.length + 1];
        for (int i = 0; i < s1.length; ++i)
            ps1[i + 1] = ps1[i] + s1[i];
        for (int i = 0; i < s2.length; ++i)
            ps2[i + 1] = ps2[i] + s2[i];
        long res = 0;
        for (int extra = 0; extra < p; ++extra) {
            res += get(p, ps1, ps2, extra * p + l - 1) - get(p, ps1, ps2, extra * p - 1);
        }
        return res;
    }

    long totalSteps = 0;

    private long get(long p, long[] s1, long[] s2, long limit) {
        if (limit < 0)
            return 0;
        long res = s1[1] * s2[(int) p] + s1[(int) p] * s2[1] - s1[1] * s2[1];
        totalSteps = 0;
        for (long x1 = 1; x1 < p;) {
            long y1 = Math.min(limit / x1, p - 1);
            long x2;
            if (y1 == 0) {
                x2 = p - 1;
            } else {
                x2 = Math.min(p - 1, limit / y1);
            }
            res += (s1[(int) (x2 + 1)] - s1[(int) x1]) * (s2[(int) y1 + 1] - s2[1]);
            x1 = x2 + 1;
            ++totalSteps;
        }
        //System.err.println(totalSteps);
        return res;
    }

    private long[] readSeq(int p) throws IOException {
        long[] cnt = new long[p];
        int n = nextInt();
        long[] res = new long[n];
        res[0] = ((nextLong() % p) + p) % p;
        res[1] = ((nextLong() % p) + p) % p;
        long a3 = ((nextLong() % p) + p) % p;
        long a4 = ((nextLong() % p) + p) % p;
        long a5 = ((nextLong() % p) + p) % p;
        for (int i = 2; i < n; ++i) {
            res[i] = (res[i - 2] * a3 + res[i - 1] * a4 + a5) % p;
        }
        for (long x : res) {
            ++cnt[(int) x];
        }
        return cnt;
    }

    public static void main(String[] args) {
        new ScottTrick().run();
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
