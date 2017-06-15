package com.github.jonatabecker.tracker;

import com.github.jonatabecker.share.Job;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author JonataBecker
 */
public class PrimeJob extends UnicastRemoteObject implements Job{

    public PrimeJob() throws RemoteException{
        super();
    }
    
    @Override
    public Integer getJob() throws RemoteException {
        return (Tracker.jobs.size() > 0)?Tracker.jobs.remove(0):null;
    }

    @Override
    public void sendJobResult(Integer result) throws RemoteException {
        Tracker.results.add(result);
        Tracker.listResults();
    }
    
}
