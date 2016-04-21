package rmi;

import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

public class SkeletonThread<T> extends Thread {
    ArrayList<ServerThread<T>> threads = new ArrayList<ServerThread<T>>();
    InetSocketAddress address;
    Class<T> intf;
    T server;
    ServerSocket socketServer;

    public SkeletonThread(ServerSocket socketServer, InetSocketAddress address,
                Class<T> intf, T server) {
        this.socketServer = socketServer;
        this.address = address;
        this.intf = intf;
        this.server = server;
    }

    public void run() {
        try {
            while(true) {
                System.out.println("-----Waiting for a connection...-----");
                Socket socket = this.socketServer.accept();
                (new ServerThread<T>(socket, this.server, this.intf)).start();
            }
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
