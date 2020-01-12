package task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread{
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            String userName;
            Message request = new Message(MessageType.NAME_REQUEST);
            Message answer;
            do
            {
                connection.send(request);
                answer = connection.receive();
                userName = answer.getData();

            }while((answer.getType() != MessageType.USER_NAME)||userName.isEmpty()||connectionMap.containsKey(userName));
            connectionMap.put(userName, connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED, "Ваше имя принято!"));
            return userName;
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            Message inMessage;
            Message outMessage;
             while (true){
                 inMessage = connection.receive();
                 if(inMessage.getType() == MessageType.TEXT){
                     String stringMessage = userName + ": " + inMessage.getData();
                     outMessage = new Message(MessageType.TEXT, stringMessage);
                     sendBroadcastMessage(outMessage);
                 }
                 else{
                     ConsoleHelper.writeMessage("Error!");
                 }
             }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException{
            for (String name: connectionMap.keySet()) {
                if(name.equals(userName)){}
                else {
                Message userAdded = new Message(MessageType.USER_ADDED, name);
                connection.send(userAdded);}
            }
        }

        public void run(){
            ConsoleHelper.writeMessage("Установлено соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
            String userName = null;
            try (Connection connection = new Connection(socket)){
                userName = serverHandshake(connection);
                notifyUsers(connection, userName);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }
            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Соединение закрыто");
        }
    }



    public static void sendBroadcastMessage(Message message){
        for (String key : connectionMap.keySet()) {
            try {
                connectionMap.get(key).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(String.format("Can't send the message to %s", key));
            }
        }
    }


    public static void main(String[] args) throws IOException {
        int port = new ConsoleHelper().readInt();
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен");
        try{
            while (true){
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch(Exception e) {
            e.printStackTrace();
            serverSocket.close();
        }
    }
}
