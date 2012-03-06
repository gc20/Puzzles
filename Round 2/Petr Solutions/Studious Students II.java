import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class StudiousStudentII implements Runnable {
    static final long MODULO = 1000000007;

    private void solve() throws IOException {
        int nt = nextInt();
        long[][] comb = new long[100][100];
        comb[0][0] = 1;
        for (int i = 1; i < 100; ++i) {
            comb[i][0] = 1;
            for (int j = 1; j < 100; ++j) comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MODULO;
        }
        for (int it = 0; it < nt; ++it) {
            String s = nextToken();
            long[][][][][][] ways = new long[s.length()][s.length()][s.length()][2][2][2];
            // first pos, last pos, number of ops, has 'a', has 'b', is length exactly 1.
            for (int len = 1; len <= s.length(); ++len)
                for (int firstPos = 0; firstPos + len <= s.length(); ++firstPos) {
                    int lastPos = firstPos + len - 1;
                    if (len == 1) {
                        long[] arr = ways[firstPos][lastPos][0][(s.substring(firstPos, lastPos + 1).contains("a") ? 1 : 0)][(s.substring(firstPos, lastPos + 1).contains("b") ? 1 : 0)];
                        ++arr[firstPos == lastPos ? 1 : 0];
                         if (arr[firstPos == lastPos ? 1 : 0] >= MODULO)
                             arr[firstPos == lastPos ? 1 : 0] -= MODULO;
                    }
                    if (len > 1) {
                        for (int oldNum = 0; oldNum < s.length(); ++oldNum)
                            for (int oldHasA = 0; oldHasA < 2; ++oldHasA)
                                for (int oldHasB = 0; oldHasB < 2; ++oldHasB)
                                    for (int oldIsSingle = 0; oldIsSingle < 2; ++oldIsSingle)
                                        if (ways[firstPos + 1][lastPos][oldNum][oldHasA][oldHasB][oldIsSingle] != 0) {
                                            long[] arr = ways[firstPos][lastPos][oldNum][oldHasA | (s.charAt(firstPos) == 'a' ? 1 : 0)][oldHasB | (s.charAt(firstPos) == 'b' ? 1 : 0)];
                                            arr[0] += ways[firstPos + 1][lastPos][oldNum][oldHasA][oldHasB][oldIsSingle];
                                            if (arr[0] >= MODULO)
                                                arr[0] -= MODULO;
                                        }
                        for (int middlePos = firstPos; middlePos <= lastPos; ++middlePos) {
                            for (int oldNum = 0; oldNum < s.length(); ++oldNum)
                                for (int oldHasA = 0; oldHasA < 2; ++oldHasA)
                                    for (int oldHasB = 0; oldHasB < 2; ++oldHasB) {
                                        long cnt = ways[firstPos][middlePos][oldNum][oldHasA][oldHasB][0];
                                        if (cnt == 0) continue;
                                        for (int transformTo = 0; transformTo < 2; ++transformTo) {
                                            if (oldHasA == 0 && transformTo == 0) continue;
                                            if (oldHasB == 0 && transformTo == 1) continue;
                                            if (middlePos < lastPos) {
                                                for (int nextNum = 0; nextNum < s.length(); ++nextNum)
                                                    for (int nextHasA = 0; nextHasA < 2; ++nextHasA)
                                                        for (int nextHasB = 0; nextHasB < 2; ++nextHasB)
                                                            for (int nextIsSingle = 0; nextIsSingle < 2; ++nextIsSingle) {
                                                                long cnt2 = ways[middlePos + 1][lastPos][nextNum][nextHasA][nextHasB][nextIsSingle];
                                                                if (cnt2 == 0)
                                                                    continue;
                                                                long[] arr = ways[firstPos][lastPos][oldNum + nextNum + 1][nextHasA | (transformTo == 0 ? 1 : 0)][nextHasB | (transformTo == 1 ? 1 : 0)];
                                                                arr[0] = (arr[0] + cnt * cnt2 % MODULO * comb[oldNum + nextNum + 1][nextNum]) % MODULO;
                                                            }
                                            } else {
                                                long[] arr = ways[firstPos][lastPos][oldNum + 1][transformTo == 0 ? 1 : 0][transformTo == 1 ? 1 : 0];
                                                arr[1] += cnt;
                                                if (arr[1] >= MODULO)
                                                    arr[1] -= MODULO;
                                            }
                                        }
                                    }
                        }
                    }
                }
            {
                long res = 0;
                for (int oldNum = 0; oldNum < s.length(); ++oldNum)
                    for (int oldHasA = 0; oldHasA < 2; ++oldHasA)
                        for (int oldHasB = 0; oldHasB < 2; ++oldHasB)
                            for (int oldIsSingle = 0; oldIsSingle < 2; ++oldIsSingle)
                                res = (res + (ways[0][s.length() - 1][oldNum][oldHasA][oldHasB][oldIsSingle])) % MODULO;
                writer.println(res);
            }
        }
    }

    public static void main(String[] args) {
        new StudiousStudentII().run();
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
