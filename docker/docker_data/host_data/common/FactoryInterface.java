package data.common;

import rmi.RMIException;


public interface FactoryInterface {
    public PingPongInterface makePingServer() throws RMIException;
}
