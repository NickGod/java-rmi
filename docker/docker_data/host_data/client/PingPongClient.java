package client;

import rmi.*;
import java.net.*;

public class PingPongClient{
    /** Socket address used for the creation of stubs. */
    private InetSocketAddress           address;
    /** Dummy skeleton used during the construction of stubs. */
    private Skeleton<TestInterface>     skeleton;
    /** Server socket used by the listening thread for the connection check. */
    private ServerSocket                socket;

    public PingPongClient(int portnumber){
        address = new InetSocketAddress(portnumber);

    }

    public static void main (String[] args) {
        if (args.length < 1){
            System.err.println("Please give the port number");
            System.exit(-1);
        }
        String portString = args[0];
        System.out.println(portString);
        int portnumber = Integer.parseInt(portString);
        PingPongClient client = new PingPongClient(portnumber);
    }

}