package rmi;
import java.net.*;
import java.io.*;
import java.lang.reflect.*;

public class ServerThread<T> extends Thread {
    Socket socket = null;
    T server = null;
    Class<T> intf;

    public ServerThread(Socket socket, T server, Class<T> intf) {
        this.socket = socket;
        this.server = server;
        this.intf = intf;
    }
    public void run() {
        ObjectOutputStream objOutput = null;
        ObjectInputStream objInput = null;
        try {
            objOutput = new ObjectOutputStream(this.socket.getOutputStream());
            objOutput.flush();
            objInput = new ObjectInputStream(this.socket.getInputStream());
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            close(objInput);
            close(objOutput);
            return;
        }

        try {
            @SuppressWarnings("unchecked")
            String methodName = (String) objInput.readObject();
            @SuppressWarnings("unchecked")
            Class<T>[] paramTypes = (Class<T>[]) objInput.readObject();
            @SuppressWarnings("unchecked")
            Object[] params = (Object[]) objInput.readObject();

            Method method = this.server.getClass().getMethod(methodName, paramTypes);

            Object ret = method.invoke(this.server, params);

            objOutput.writeObject(ret);
            objOutput.flush();
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            try{
                objOutput.writeObject(e);
                objOutput.flush();
            }
            catch (Exception ee){
                System.err.println(e.getMessage());
            }
        }
        finally {
            close(objInput);
            close(objOutput);
        }
    }

    public static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException e) {
            //log the exception
        }
    }
}
