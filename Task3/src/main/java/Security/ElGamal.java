package Security;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ElGamal {
    public List<Long> encrypt(int q, int alpha, int y, int k, int m) {
        long K =  mod_large(y, k, q);
        long c1 = mod_large(alpha, k, q);
        long c2 = (K * m) % q;

        return Arrays.asList(c1, c2);
    }

    public int decrypt(int c1, int c2, int x, int q) {
        int K = (int) mod_large(c1, x, q);
        int K_inv = extended_euclidean(K, q);
        int M = (c2 * K_inv) % q;

        return M;
    }

    public long mod_large(long base, long exp, long mod) {
    long result = 1;
    for (long i = 0; i < exp; i++) {
        result = (result * base) % mod;
    }
    return result;
}


    public int extended_euclidean(int b, int m)
    {
        int MI = -1;
        int Q = -1;
        int A1=1, A2=0, A3=m;
        int B1=0, B2=1, B3=b;
        int T1=-1, T2=-1, T3=-1;

        while (B3 != 0 && B3 != 1)
        {
            Q = A3/B3;
            T1 = A1 - Q*B1;
            T2 = A2 - Q*B2;
            T3 = A3 - Q*B3;
            A1= B1;
            A2= B2;
            A3= B3;
            B1 = T1;
            B2 = T2;
            B3 = T3;
        }

        if (B3 == 1)
            MI = ((B2 % m) + m) % m;

        return MI;
    }

}
