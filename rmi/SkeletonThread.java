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
            while(!this.socketServer.isClosed()) {
                //System.out.println("-----Waiting for a connection...-----");
                Socket socket = this.socketServer.accept();
                ServerThread<T> thread = (new ServerThread<T>(socket, this.server, this.intf));
                thread.start();
                threads.add(thread);
            }
        }
        catch(SocketException e) {
            try {
                for(ServerThread<T> t: this.threads) {
                    t.join();
                }
            }
            catch(InterruptedException ep) {

            }
        }
        catch(IOException e) {
        }
    }
}
