package DataStructures;

import java.util.Arrays;

public class DynamicArray<T> {
    T[] array;
    int size;
    public DynamicArray() {
        array = (T[]) new Object[10];
        size=0;
    }

    public void Insert(T data){
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

    public T find(int index) {
        return array[index];
    }

    public T[] toArray(){
        T[] arr =(T[]) new Object[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = array[i];
        }
        return arr;
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += array[i] + "-";
        }
        return s;
    }
}
