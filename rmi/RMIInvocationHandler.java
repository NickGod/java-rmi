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
        try {
            Socket socket = new Socket(this.skeletonAddress.getAddress(),
                                       this.skeletonAddress.getPort());
            ObjectOutputStream objOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objInput = new ObjectInputStream(socket.getInputStream());

            System.out.println("\n\n")
            objOutput.writeObject(method.getName());
            objOutput.writeObject(method.getParameterTypes());
            objOutput.writeObject(args);

            return objInput.readObject();
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
