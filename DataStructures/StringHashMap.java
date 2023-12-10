package DataStructures;
class StringValuePair<V extends Comparable<V>>{
    String key;
    V value;

    public StringValuePair(String key, V value){
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}

public class StringHashMap<V extends Comparable<V>> {
    StringValuePair[] table;
    int occupied;

    public StringHashMap() {
        table = new StringValuePair[13];
        occupied = 0;
    }

    public StringHashMap(int s) {
        int size = s + (s / 3);
        int newSize = getPrime(size);
        table = new StringValuePair[newSize];
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

    public int size(){
        return occupied;
    }

    public int hash(String key) {
        //compute hash value by taking mod on key value and return remainder
        long k = 0;
        for (int i = 0; i < key.length(); i++) {
            k += key.charAt(0)*(Math.pow(3, i));
        }
        return (int)(k%table.length);
    }

    public int rehash(int key, int i){
        return (key+i) % table.length;
    }

    public void resize(){
        int newSize = getPrime(table.length + 10);
        StringValuePair[] newArr = new StringValuePair[newSize];
        for (int j = 0; j < table.length; j++) {
            if (table[j] == null) continue;
            String key = table[j].getKey();
            int index = 0;
            for (int i = 0; i < key.length(); i++) {
                index += key.charAt(0)*(Math.pow(3, i));
            }
            index = index%newSize;
            if (newArr[index] == null){
                newArr[index] = new StringValuePair(key, table[j].getValue());
            }
            else {
                for (int i = 1; i < 11; i++) {
                    if (newArr[rehash(index, i)] == null){
                        newArr[rehash(index, i)] = new StringValuePair(key, table[j].getValue());
                        break;
                    }
                }
            }
        }
        table = newArr;
    }

    public void put(String key, V value){
        if ((occupied+1)/(double)(table.length) > 0.7) {
            System.out.println("Resizing");
            resize();
        }

        int index = hash(key);
        if (table[index] == null){
            table[index] = new StringValuePair(key, value);
            occupied++;
        }
        //No duplicate keys allowed
        else if(table[index].key.equals(key)){
            System.out.println("No duplicate keys allowed");
        }
        else {
            for (int i = 1; i < 11; i++) {
                if (table[rehash(index, i)] == null){
                    table[rehash(index, i)] = new StringValuePair(key, value);
                    occupied++;
                    return;
                }
                if(table[rehash(index, i)].key.equals(key)){
                    System.out.println("No duplicate keys allowed.");
                    return;
                }
            }
            resize();
        }
        toString();
    }

    public V get(String key){
        int index = hash(key);
        if (table[index].getKey().equals(key)) return (V) table[index].getValue();
        for (int i = 1; i <= 10; i++) {
            if (table[rehash(index, i)].getKey().equals(key))
                return (V) table[(index + i)% table.length].getValue();
        }
        return null;
    }

    public boolean containsKey(String key){
        int index = hash(key);
        if (table[index] == null) return false;
        if (table[index].getKey().equals(key)) return true;
        for (int i = 1; i <= 10; i++) {
            if (table[rehash(index, i)] == null) continue;
            if (table[rehash(index, i)].getKey().equals(key)) return true;
        }
        return false;
    }

    public LinkedList<String> keySet(){
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null)  list.insert(table[i].getKey());
        }
        return list;
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
