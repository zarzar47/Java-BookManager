package DataStructures;
import Books.Book;

public class DynamicArray<T> {
    T[] array;
    int size;
    // Constructor
    public DynamicArray() {
        array=(T[]) new Object[10];
        size=0;
    }

    // Methods
    public void insert(T data){
        if (size>=array.length)
            resize();
        array[size++] = data;
    }

    public void insertAt(T data, int i){
        if (i>= array.length) {
            resize(i);
        }
        array[i] = data;
        size++;
    }

    private void resize() {
        resize(array.length);
    }

    private void resize(int index) {
        int diff = index - array.length;
        int newsize = 0;
        newsize=size+diff+5;
        T[] newArray = (T[]) new Object[newsize];
        for (int i = 0; i < size; i++) {
            newArray[i]=array[i];
        }
        array = newArray;
    }

    public static void ascSortByName(Book[] arr, int l, int u, boolean ascending)
    {
        if (arr.length == 1) return;

        if (l < u) {
            int m = l + (u - l)/2;

            ascSortByName(arr, l, m, ascending);
            ascSortByName(arr, m + 1, u, ascending);

            ascStringSortHelper(arr, l, m, u, ascending);
        }
    }
    public static void ascSortByPrice(Book[] arr, int l, int u, boolean ascending)
    {
        if (arr.length == 1) return;

        if (l < u) {
            int m = l + (u - l)/2;

            ascSortByPrice(arr, l, m, ascending);
            ascSortByPrice(arr, m + 1, u, ascending);

            ascPriceSortHelper(arr, l, m, u, ascending);
        }
    }
    public static void ascSortByPopularity(Book[] arr, int l, int u, boolean ascending)
    {
        if (arr.length == 1) return;

        if (l < u) {
            int m = l + (u - l)/2;

            ascSortByPopularity(arr, l, m, ascending);
            ascSortByPopularity(arr, m + 1, u, ascending);

            ascPopularitySortHelper(arr, l, m, u, ascending);
        }
    }

    public static void ascStringSortHelper(Book[] arr, int l, int m, int u, boolean ascending)
    {
        int n1 = m - l + 1;
        int n2 = u - m;
        Book[] L = new Book[n1];
        Book[] R = new Book[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        int i = 0, j = 0;

        int k = l;

        if (ascending)
        {
            while (i < n1 && j < n2)
            {
                if (L[i].getName().compareToIgnoreCase((R[j].getName())) <= 0)
                    arr[k++] = L[i++];
                else
                    arr[k++] = R[j++];
            }
        }
        else
        {
            while (i < n1 && j < n2)
            {
                if (L[i].getName().compareTo(R[j].getName()) > 0)
                    arr[k++] = L[i++];
                else
                    arr[k++] = R[j++];
            }
        }


        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            // arr[k++] = R[j++];
            arr[k++] = R[j++];
    }
    public static void ascPriceSortHelper(Book[] arr, int l, int m, int u, boolean ascending)
    {
        int n1 = m - l + 1;
        int n2 = u - m;
        Book[] L = new Book[n1];
        Book[] R = new Book[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;

        if (ascending)
        {
            while (i < n1 && j < n2)
            {
                if (L[i].getPrice() <= R[j].getPrice())
                    arr[k++] = L[i++];
                else
                    arr[k++] = R[j++];
            }
        }
        else
        {
            while (i < n1 && j < n2)
            {
                if (L[i].getPrice() > R[j].getPrice())
                    arr[k++] = L[i++];
                else
                    arr[k++] = R[j++];
            }
        }

        while (i < n1)
             arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }
    public static void ascPopularitySortHelper(Book[] arr, int l, int m, int u, boolean ascending)
    {
        int n1 = m - l + 1;
        int n2 = u - m;
        Book[] L = new Book[n1];
        Book[] R = new Book[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        int i = 0, j = 0;
        int k = l;

        if (ascending)
        {
            while (i < n1 && j < n2)
            {
                if (L[i].getPopularity() <= R[j].getPopularity())
                    arr[k++] = L[i++];
                else
                    arr[k++] = R[j++];
            }
        }
        else
        {
            while (i < n1 && j < n2)
            {
                if (L[i].getPopularity() > R[j].getPopularity())
                    arr[k++] = L[i++];
                else
                    arr[k++] = R[j++];
            }
        }

        while (i < n1)
            arr[k++] = L[i++];

        while (j < n2)
            arr[k++] = R[j++];
    }




    public T find(int index) {
        return array[index];
    }

    public int getSize()
    {
        return size;
    }


    public T[] toArray(){
        T[] arr = (T[]) new Object[size];
        for (int i = 0; i < arr.length; i++)
            arr[i] = this.array[i];
        return arr;
    }

    public Book[] toArr()
    {
        Book[] arr = new Book[size];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (Book) this.array[i];
        return arr;
    }

    public void toDyArr(T[] arr)
    {
        this.clearAll();
        for (T t : arr) this.insert(t);
    }

    public void clearAll(){
        System.out.println("bullshit");
        size = 0;
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
