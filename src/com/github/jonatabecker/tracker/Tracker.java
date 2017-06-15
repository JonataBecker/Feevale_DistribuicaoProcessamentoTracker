package com.github.jonatabecker.tracker;

import com.github.jonatabecker.share.Job;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author JonataBecker
 */
public class Tracker {

    static List<Integer> jobs = Collections.synchronizedList(new ArrayList<Integer>());
    static List<Integer> results = Collections.synchronizedList(new ArrayList<Integer>());
    static int nJobs;

    
    private void createJobs(int nJobs) {
        Tracker.nJobs = nJobs;
        for (int i = 0; i < nJobs; i++) {
            jobs.add(i + 1);
        }
    }
    
    public static void listResults(){
        if(Tracker.nJobs != results.size()){
            return;
        }
        for(Integer n:results){
            System.out.println("Value: " + n);
        }
    }
    
    private static void startRegistry(int port)
            throws RemoteException {
        try {
            Registry registry
                    = LocateRegistry.getRegistry(port);
            registry.list();
        } catch (RemoteException e) {
            LocateRegistry.createRegistry(port);
        }
    }

    public static void main(String[] args) {
        Tracker t = new Tracker();
        String url;
        t.createJobs(1000);
        try {
            startRegistry(8877);
            url = "rmi://localhost:" + 8877 + "/tracker";
            Job job = new PrimeJob();
            Naming.rebind(url, job);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Tracker Ready!");
    }

}
