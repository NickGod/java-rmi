package rmi;
import java.net.*;
import java.io.*;

public class SkeletonThread<T> extends Thread {
    Socket socket = null;
    T server = null;

    public SkeletonThread(Socket socket, T server) {
        this.socket = socket;
        this.server = server;
    }
    public void run() {
        try {
            ObjectInputStream objInput = new ObjectInputStream(this.socket.getInputStream());

            @SuppressWarnings("unchecked")
            String methodName = (String) objInput.readObject();
            @SuppressWarnings("unchecked")
            Class<T>[] paramTypes = (Class<T>[]) objInput.readObject();
            @SuppressWarnings("unchecked")
            Object[] params = (Object[]) objInput.readObject();
        }
        catch(ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
