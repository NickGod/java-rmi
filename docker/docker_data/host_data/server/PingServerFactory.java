package data.server;

import rmi.*;
import data.common.*;
import java.net.*;

public class PingServerFactory implements FactoryInterface {
    InetSocketAddress address;
    int numServer = 0;

    public PingServerFactory(InetSocketAddress address) {
        this.address = address;
    }

    public PingPongInterface makePingServer() throws RMIException {
        this.numServer ++;
        Skeleton<PingPongInterface> skeleton = null;
        try {
            // System.out.println("Server address: " + InetAddress.getLocalHost().toString());
            skeleton = new Skeleton<PingPongInterface>(
                                    PingPongInterface.class,
                                    new PingPongServer(),
                                    new InetSocketAddress(InetAddress.getLocalHost(), this.address.getPort() + numServer)
                                    );
        }
        catch(UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        try {
            skeleton.start();
        }
        catch(RMIException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        try {
            return Stub.create(PingPongInterface.class, skeleton);
        }
        catch(Exception e) {
            return null;
        }
    }

    public static void main(String args[]) {
        if (args.length < 1){
            System.err.println("Please give the port number");
            System.exit(-1);
        }
        int port = Integer.parseInt(args[0]);
        InetSocketAddress factoryAddr = new InetSocketAddress(port);
        PingServerFactory factory = new PingServerFactory(factoryAddr);

        // System.out.println("Factory address: " + factoryAddr.getAddress().toString());

        @SuppressWarnings("unchecked")
        Skeleton<FactoryInterface> skeleton = new Skeleton<FactoryInterface> (
                                                    FactoryInterface.class,
                                                    factory,
                                                    new InetSocketAddress(port)
                                                    );
        try {
            skeleton.start();
        }
        catch(RMIException e) {

        }
    }
}
