package DataStructures;

class intValuePair<V extends Comparable<V>>{
    int key;
    V value;

    public intValuePair(int key, V value){
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}

public class intHashMap<V extends Comparable<V>>{
    intValuePair[] table;
    int occupied;

    public intHashMap() {
        table = new intValuePair[13];
        occupied = 0;
    }

    public intHashMap(int s) {
        int size = s + (s / 3);
        int newSize = getPrime(size);
        table = new intValuePair[newSize];
        occupied = 0;
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

    public int rehash(int key, int i){
        return (key+i) % table.length;
    }

    public void resize(){
        int newSize = getPrime(table.length + 10);
        intValuePair[] newArr = new intValuePair[newSize];
        for (int j = 0; j < table.length; j++) {
            if (table[j] == null) continue;
            int key = table[j].getKey();
            int index = key % newSize;
            if (newArr[index] == null){
                newArr[index] = new intValuePair(key, table[j].getValue());
                occupied++;
            }
            else {
                for (int i = 1; i < 11; i++) {
                    if (newArr[rehash(key, i)] == null){
                        newArr[rehash(key, i)] = new intValuePair(key, table[j].getValue());
                        occupied++;
                        return;
                    }
                }
            }
        }
        table = newArr;
    }

    public void put(int key, V value){
        if ((occupied+1)/(double)(table.length) > 0.7) {
            System.out.println("Resizing");
            resize();
        }

        int index = hash(key);
        if (table[index] == null){
            table[index] = new intValuePair(key, value);
            occupied++;
        }
        //No duplicate keys allowed
        else if(table[index].key == key){
            System.out.println("No duplicate keys allowed");
        }
        else {
            for (int i = 1; i < 11; i++) {
                if (table[rehash(key, i)] == null){
                    table[rehash(key, i)] = new intValuePair(key, value);
                    occupied++;
                    return;
                }
                if(table[rehash(key, i)].key == key){
                    System.out.println("No duplicate keys allowed.");
                    return;
                }
            }
            resize();
        }
    }

    public V get(int key){
        int index = hash(key);
        if (table[index].getKey() == key) return (V) table[index].getValue();
        for (int i = 1; i <= 10; i++) {
            if (table[rehash(index, i)].getKey() == key) return (V) table[(index + i)% table.length].getValue();
        }
        return null;
    }

    public int size(){
        return occupied;
    }

    public LinkedList<V> values(){
        LinkedList<V> list = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) list.insert((V)table[i].getValue());
        }
        return list;
    }

    public boolean containsKey(int key){
        int index = hash(key);
        if (table[index].getKey() == key) return true;
        for (int i = 1; i <= 10; i++) {
            if (table[rehash(index, i)].getKey() == key) return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null)
                sb.append("[" + i + "] " + table[i].key + " : " + table[i].value + "\n");
            else sb.append("[" + i + "] null \n");
        }
        return sb.toString();
    }
}