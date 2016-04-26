package server;

import rmi.*;
import common.*;

public class PingPongServer implements PingPongInterface {
    public PingPongServer() {
    }

    public Object ping(int idNumber) throws RMIException {
        return "Pong " + String.valueOf(idNumber);
    }
}
