package server;

import rmi.*;
import common.*;

public class PingServerFactory implements PingPongInterface {
    public static PingPongServer makePingPongServer() {
        return new PingPongServer();
    }

    public static void main(String args[]) {
        if (args.length < 1){
            System.err.println("Please give the port number");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0]);

        Skeleton<TestInterface> skeleton = Skeleton(
                                            PingPongInterface.class,
                                            new PingPongServer(),
                                            new InetSocketAddress(port)
                                            );
        skeleton.start();
    }

    public Object ping(int idNumber) throws RMIException, FileNotFoundException {
        return "Pong " + String.valueOf(idNumber);
    }
}
