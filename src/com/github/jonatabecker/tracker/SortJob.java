package com.github.jonatabecker.tracker;

import com.github.jonatabecker.share.Job;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author JonataBecker
 */
public class SortJob implements Job<int[]>, Serializable {

    private final int[] arr;

    public SortJob(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int[] exec() {
        for (int i = 0; i < arr.length; i++) {
            for (int x = i; x < arr.length; x++) {
                if (arr[i] > arr[x]) {
                    int aux = arr[i];
                    arr[i] = arr[x];
                    arr[x] = aux;
                }
            }
        }
        return arr;
    }

    @Override
    public String toString() {
        return "SortJob{" + "arr=" + Arrays.toString(arr) + '}';
    }

}
