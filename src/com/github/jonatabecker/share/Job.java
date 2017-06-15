package com.github.jonatabecker.share;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author JonataBecker
 */
public interface Job extends Remote{

    public Integer getJob() throws RemoteException;

    public void sendJobResult(Integer result) throws RemoteException;

}
