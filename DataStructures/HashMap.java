package DataStructures;

class keyValuePair<K, V>{

}
public class HashMap {
    int[] table;

    int numOfCollisions = 0;
    int numOfOccupiedCells = 0;

   HashMap(int s) {
        // table size should be a prime number and 1/3 extra.
        int size = s + (s / 3);
        int newSize = getPrime(size);

        table = new int[newSize]; // if value is 0 for integer the cell will consider empty.
    }

    private int getPrime(int n) {
        while (true) {
            if (isPrime(n)) return n;
            n++;
        }
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public int hash(int key) {
        //compute hash value by taking mod on key value and return remainder
        return key % table.length;
    }
}
