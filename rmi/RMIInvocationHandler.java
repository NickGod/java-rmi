package rmi;
import java.lang.reflect.*;
import java.net.*;
import java.io.*;

public class RMIInvocationHandler implements InvocationHandler {
    InetSocketAddress skeletonAddress;
    public RMIInvocationHandler(InetSocketAddress address) {
        this.skeletonAddress = address;
    }

    // TODO: there should be more constructors

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Object result;
        Socket socket;
        ObjectOutputStream objOutput;
        ObjectInputStream objInput;
        try {
            socket = new Socket(this.skeletonAddress.getAddress(),
                                       this.skeletonAddress.getPort());
            objOutput = new ObjectOutputStream(socket.getOutputStream());
            objOutput.writeObject(method.getName());
            objOutput.writeObject(method.getParameterTypes());
            objOutput.writeObject(args);
            objOutput.flush();

            objInput = new ObjectInputStream(socket.getInputStream());
            result = objInput.readObject();
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        finally {
            objInput.close();
            objOutput.close();
            socket.close();
        }
    }
}
