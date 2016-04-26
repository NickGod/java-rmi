package data.client;


import data.common.*;
import rmi.*;
import java.net.*;

public class PingPongClient {
    /** Socket address used for the creation of stubs. */
    private InetSocketAddress           address;
    private static final int NUMBER = 4;
    private PingPongInterface stub;

    public PingPongClient(int portnumber){
        int pass = 0;
        stub = null;
        try {
            address = new InetSocketAddress("server", portnumber);
            stub = Stub.create(PingPongInterface.class, address);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }

        for(int i = 0; i < NUMBER; i++){
            try {
                String res = (String) stub.ping(i);
                System.out.println("Received: " + res);
                if (res.equals("Pong " + i)){
                    pass++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.print(NUMBER + " Tests Completed, " + (NUMBER - pass) + " Tests Failed");
    }

    public static void main (String[] args) {
        if (args.length < 1){
            System.err.println("Please give the port number");
            System.exit(-1);
        }
        String portString = args[0];
        System.out.println("Connecting server on port " + String.valueOf(portString));
        int portnumber = Integer.parseInt(portString);
        PingPongClient client = new PingPongClient(portnumber);
    }

}
