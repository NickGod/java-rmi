package rmi;
import java.net.*;
import java.io.*;
import java.lang.reflect.*;

public class SkeletonThread<T> extends Thread {
    Socket socket = null;
    T server = null;
    Class<T> intf;

    public SkeletonThread(Socket socket, T server, Class<T> intf) {
        this.socket = socket;
        this.server = server;
        this.intf = intf;
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

            Method method = this.server.getClass().getMethod(methodName, paramTypes);
            
            Object ret = method.invoke(this.server, params);

            ObjectOutputStream objOutput = new ObjectOutputStream(this.socket.getOutputStream());
            objOutput.writeObject(ret);
        }
        catch(NoSuchMethodException e) {

        }
        catch(IllegalAccessException e) {

        }
        catch(InvocationTargetException e) {

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
