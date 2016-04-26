package data.server;

import rmi.*;
import data.common.*;

public class PingPongServer implements PingPongInterface {
    public PingPongServer() {
    }

    public Object ping(int idNumber) throws RMIException {
        System.out.println("Get ID Number from Client: " + String.valueOf(idNumber));
        return "Pong " + String.valueOf(idNumber);
    }
}
