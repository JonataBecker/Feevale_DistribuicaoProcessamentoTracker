package com.github.jonatabecker.tracker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.github.jonatabecker.share.Fifo;
import com.github.jonatabecker.share.Job;

/**
 *
 * @author JonataBecker
 */
public class FifoJob extends UnicastRemoteObject implements Fifo<Job> {

    private final Tracker tracker;
    
    public FifoJob(Tracker tracker) throws RemoteException{
        super();
        this.tracker = tracker;
    }
    
    @Override
    public Job getJob() throws RemoteException, InterruptedException {
        return tracker.getJog();
    }

    @Override
    public void sendJobResult(Object result) throws RemoteException, InterruptedException {
        tracker.addResult(result);
    }
    
}
