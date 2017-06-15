package com.github.jonatabecker.tracker;

import com.github.jonatabecker.share.Job;
import java.io.Serializable;

/**
 *
 * @author JonataBecker
 */
public class PrimeJob implements Job<Integer>, Serializable {

    private final Integer number;

    public PrimeJob(Integer number) {
        this.number = number;
    }

    private boolean isPrime(int n) {
        int v = n;
        while (--v > 1) {
            if (n % v == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer exec() {
        if (isPrime(number)) {
            return number;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "PrimeJob{" + "number=" + number + '}';
    }

}
