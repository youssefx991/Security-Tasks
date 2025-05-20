package Security;


import java.util.Arrays;
import java.util.List;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {
        int ya = (int) mod_large(alpha, xa, q);
        int yb = (int) mod_large(alpha, xb, q);
        int ka = (int) mod_large(yb, xa, q);
        int kb = (int) mod_large(ya, xb, q);
        
        return Arrays.asList(ka, kb);
    }

    public long mod_large(long base, long exp, long mod) {
    long result = 1;
    for (long i = 0; i < exp; i++) {
        result = (result * base) % mod;
    }
    return result;
}

}
