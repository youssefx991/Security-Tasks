package Security;

public class RSA {

    public int encrypt(int p, int q, int M, int e) {
        int n = p * q;
        return (int) mod_large(M, e, n);
    }

    public int decrypt(int p, int q, int C, int e) {
        int n = p * q;
        int phi_n = (p-1) * (q-1);
        int d = extended_euclidean(e, phi_n);

        return (int) mod_large(C, d, n);
    }


    public long mod_large(long base, long exp, long mod) {
    long result = 1;
    for (long i = 0; i < exp; i++) {
        result = (result * base) % mod;
    }
    return result;
}


    public int extended_euclidean(int b, int m){
        
        int Q=-1, MI=-1;
        int A1=1, A2=0, A3=m;
        int B1=0, B2=1, B3=b;
        int T1=-1, T2=-1, T3=-1;

        while (B3 != 0 && B3 != 1)
        {
            Q = A3 / B3;
            T1 = A1 - Q * B1;
            T2 = A2 - Q * B2;
            T3 = A3 - Q * B3;
            A1 = B1;
            A2 = B2;
            A3 = B3;
            B1 = T1;
            B2 = T2;
            B3 = T3;
        }

        if (B3 == 1)
        {
            MI = ((B2 % m) + m) % m;
        }
        return MI;
    }

}
