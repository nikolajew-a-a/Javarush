package task3008;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class Connection implements Closeable {
    final private Socket socket;
    final private ObjectOutputStream out;
    final private ObjectInputStream in;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void send(Message message) throws IOException {
        synchronized (out) {
            this.out.writeObject(message);
        }
    }

    public Message receive() throws IOException, ClassNotFoundException {
        Message message;
        synchronized (in) {
            message = (Message) this.in.readObject();
        }
        return message;
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }


    public void close()  {
        try{ out.close();
            in.close();
            socket.close();}
        catch (IOException e){
            e.getStackTrace();
        }
    }


}
