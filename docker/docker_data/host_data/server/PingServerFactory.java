package data.server;

import rmi.*;
import data.common.*;
import java.net.*;

public class PingServerFactory {
    public static PingPongServer makePingServer() {
        return new PingPongServer();
    }

    public static void main(String args[]) {
        if (args.length < 1){
            System.err.println("Please give the port number");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0]);

        @SuppressWarnings("unchecked")
        Skeleton<PingPongInterface> skeleton = new Skeleton(
                                            PingPongInterface.class,
                                            PingServerFactory.makePingServer(),
                                            new InetSocketAddress(port)
                                            );
        try {
            skeleton.start();
        }
        catch(RMIException e) {

        }
    }
}
