package data.common;

import rmi.RMIException;

public interface PingPongInterface{
    public Object ping(int idNumber) throws RMIException;
}
