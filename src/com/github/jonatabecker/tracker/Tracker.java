package com.github.jonatabecker.tracker;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.github.jonatabecker.share.Fifo;
import com.github.jonatabecker.share.Job;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author JonataBecker
 */
public class Tracker {

    private final LinkedBlockingQueue<Job> jobs;
    private final LinkedBlockingQueue<Object> results;

    public Tracker() {
        this.jobs = new LinkedBlockingQueue();
        this.results = new LinkedBlockingQueue();
    }

    public Job getJog() throws InterruptedException {
        return jobs.poll();
    }
    
    public void addJob(Job job) {
        jobs.add(job);
    }

    public void addResult(Object object) throws InterruptedException {
        results.put(object);
        if (object instanceof int[]) {
            System.out.println("result: " + Arrays.toString((int[]) object));
        } else {
            System.out.println("result: " + object);
        }
    }

    public void startRegistry(int port) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            registry.list();
        } catch (RemoteException e) {
            LocateRegistry.createRegistry(port);
        }
    }

    private static void addPrimeJobs(Tracker tracker, int n) {
        for (int i = 1; i < n; i++) {
            tracker.addJob(new PrimeJob(i));
        }
    }
    
    private static void addSortJobs(Tracker tracker, int n, int size) {
        for (int i = 0; i < n; i++) {
            int[] arr = new int[size];
            for (int x = 0; x < size; x++) {
                arr[x] = (int) (Math.random() * 10);
            }
            tracker.addJob(new SortJob(arr));
        }
    }
    
    public static void main(String[] args) {
        try {
            Tracker tracker = new Tracker();
            tracker.startRegistry(8877);
            addPrimeJobs(tracker, 10);
            addSortJobs(tracker, 10, 10000);
            String url = "rmi://localhost:" + 8877 + "/tracker";
            Fifo job = new FifoJob(tracker);
            Naming.rebind(url, job);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Tracker Ready!");
    }

}
