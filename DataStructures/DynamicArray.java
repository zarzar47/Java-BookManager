package DataStructures;
import Books.Book;

public class DynamicArray<T> {
    T[] array;
    int size;

    // Constructor
    public DynamicArray() {
        array = (T[]) new Object[10];
        size=0;
    }

    // Methods
    public void insert(T data){
        if (size>=array.length)
            resize();
        array[size++] = data;
    }
    public void insertAt(T data, int i) {
        if (i>=array.length)
            resize(i);
        array[i] = data;
        size++;
    }

    private void resize() {
        resize(array.length);
    }

    private void resize(int index) {
        int diff = index - array.length;
        size+=diff;
        T[] newArray = (T[]) new Object[size+diff+5];
        for (int i = 0; i < size; i++) {
            newArray[i]=array[i];
        }
        array = newArray;
    }

    public void ascSortByName(DynamicArray<Book> arr, int l, int u)
    {
        if (l < u) {
            int m = l + (u - l)/2;

            ascSortByName(arr, l, m);
            ascSortByName(arr, m + 1, u);

            ascStringSortHelper(arr, l, m, u);
        }
    }
    public void ascSortByPrice(DynamicArray<Book> arr, int l, int u)
    {
        if (l < u) {
            int m = l + (u - l)/2;

            ascSortByPrice(arr, l, m);
            ascSortByPrice(arr, m + 1, u);

            ascPriceSortHelper(arr, l, m, u);
        }
    }
    public void ascSortByPopularity(DynamicArray<Book> arr, int l, int u)
    {
        if (l < u) {
            int m = l + (u - l)/2;

            ascSortByPrice(arr, l, m);
            ascSortByPrice(arr, m + 1, u);

            ascPopularitySortHelper(arr, l, m, u);
        }
    }

    public void ascStringSortHelper(DynamicArray<Book> arr, int l, int m, int u)
    {
        int n1 = m - l + 1;
        int n2 = u - m;
        DynamicArray<Book> L = new DynamicArray<>();
        DynamicArray<Book> R = new DynamicArray<>();

        for (int i = 0; i < n1; ++i)
            // L[i] = arr.find(l + i);
            L.insertAt(arr.find(l + i), i);
        for (int j = 0; j < n2; ++j)
            // R[j] = arr[m + 1 + j];
            R.insertAt(arr.find(m + 1 + j), j);


        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
//            if (L[i] <= R[j])
//                arr[k++] = L[i++];
//            else
//                arr[k++] = R[j++];
            if (L.find(i).getName().compareTo(R.find(j).getName()) <= 0)
                arr.insertAt(L.find(i++), k++);
            else
                arr.insertAt(R.find(j++), k++);
        }

        while (i < n1)
            // arr[k++] = L[i++];
            arr.insertAt(L.find(i++), k++);

        while (j < n2)
            // arr[k++] = R[j++];
            arr.insertAt(R.find(j++), k++);
    }
    public void ascPriceSortHelper(DynamicArray<Book> arr, int l, int m, int u)
    {
        int n1 = m - l + 1;
        int n2 = u - m;
//        DynamicArray<Book> L = new DynamicArray<>();
//        DynamicArray<Book> R = new DynamicArray<>();
        Book[] L = new Book[n1];
        Book[] R = new Book[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr.find(l + i);
            // L.insertAt(arr.find(l + i), i);
        for (int j = 0; j < n2; ++j)
            R[j] = arr.find(m + 1 + j);
            // R.insertAt(arr.find(m + 1 + j), j);


        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i].getPrice() <= R[j].getPrice())
                arr.insertAt(L[i++], k++);
            else
                arr.insertAt(R[j++], k++);
//            if (L.find(i).getPrice() <= R.find(j).getPrice())
//                arr.insertAt(L.find(i++), k++);
//            else
//                arr.insertAt(R.find(j++), k++);
        }

        while (i < n1)
            // arr[k++] = L[i++];
            arr.insertAt(L[i++], k++);

        while (j < n2)
            // arr[k++] = R[j++];
            arr.insertAt(R[j++], k++);
    }
    public void ascPopularitySortHelper(DynamicArray<Book> arr, int l, int m, int u)
    {
        int n1 = m - l + 1;
        int n2 = u - m;
//        DynamicArray<Book> L = new DynamicArray<>();
//        DynamicArray<Book> R = new DynamicArray<>();
        Book[] L = new Book[n1];
        Book[] R = new Book[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr.find(l + i);
        // L.insertAt(arr.find(l + i), i);
        for (int j = 0; j < n2; ++j)
            R[j] = arr.find(m + 1 + j);
        // R.insertAt(arr.find(m + 1 + j), j);


        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i].getPopularity() <= R[j].getPopularity())
                arr.insertAt(L[i++], k++);
            else
                arr.insertAt(R[j++], k++);
//            if (L.find(i).getPrice() <= R.find(j).getPrice())
//                arr.insertAt(L.find(i++), k++);
//            else
//                arr.insertAt(R.find(j++), k++);
        }

        while (i < n1)
            // arr[k++] = L[i++];
            arr.insertAt(L[i++], k++);

        while (j < n2)
            // arr[k++] = R[j++];
            arr.insertAt(R[j++], k++);
    }



    public T find(int index) {
        return array[index];
    }

    public int getSize()
    {
        return size;
    }


    public T[] toArray(){
        T[] arr =(T[]) new Object[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[i];
        }
        return arr;
    }

    public void clearAll(){
        for (int i = 0; i < size; i++) {
            array[i]=null;
        }
    }

    // toString
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += array[i] + "\n";
        }
        return s;
    }
}
