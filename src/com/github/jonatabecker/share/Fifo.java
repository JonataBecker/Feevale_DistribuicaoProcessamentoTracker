package com.github.jonatabecker.share;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JonataBecker
 * 
 * @param <T>
 */
public interface Fifo<T extends Job> extends Remote{

    public T getJob() throws RemoteException, InterruptedException;

    public void sendJobResult(Object result) throws RemoteException, InterruptedException;

}
