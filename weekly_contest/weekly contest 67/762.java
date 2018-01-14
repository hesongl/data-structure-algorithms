class Solution {
    public int countPrimeSetBits(int L, int R) {
        int res = 0;
        for (int i = L; i <= R; i++) {
            int temp = numOfBits(i);
            if (isPrime(temp))
                res++;
        }
        return res;
    }
    private int numOfBits(int n) {
        int count = 0;
        while (n > 0) {
            if ((n&1)==1)
                count++;
            n >>= 1;
        }
        return count;
    }
    private boolean isPrime(int n) {
        // !!
        if (n == 1)
            return false;
        int tmp = (int)Math.sqrt(n);
        for (int i = 2; i <= tmp; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}