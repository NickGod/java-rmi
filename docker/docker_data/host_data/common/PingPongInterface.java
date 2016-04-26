package common;

import rmi.RMIException;
import java.io.FileNotFoundException;

public interface PingPongInterface{
    public Object ping(int idNumber) throws RMIException;
}
