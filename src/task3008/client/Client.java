package task3008.client;

import task3008.Connection;
import task3008.ConsoleHelper;
import task3008.Message;
import task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    volatile private boolean clientConnected = false;
    ConsoleHelper consoleHelper = new ConsoleHelper();

    public class SocketThread extends Thread{
        protected void notifyConnectionStatusChanged(boolean clientConnected){
                Client.this.clientConnected = clientConnected;
                synchronized (Client.this){
                    Client.this.notify();
            }
        }

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage("Пользователь " + userName + " подключился к чату");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage("Пользователь " + userName + " покинул чат");
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                MessageType messageType = message.getType();
                if (messageType != null) {

                    switch (messageType) {

                        // 	Если тип полученного сообщения NAME_REQUEST (сервер запросил имя)
                        case NAME_REQUEST: {

                            // запросить ввод имени пользователя с помощью метода getUserName()
                            // создать новое сообщение с типом USER_NAME и введенным именем, отправить сообщение серверу.
                            String userName = getUserName();
                            connection.send(new Message(MessageType.USER_NAME, userName));
                            break;
                        }

                        // Если тип полученного сообщения NAME_ACCEPTED (сервер принял имя)
                        case NAME_ACCEPTED: {

                            // значит сервер принял имя клиента, нужно об этом сообщить главному потоку, он этого очень ждет.
                            // Сделай это с помощью метода notifyConnectionStatusChanged(), передав в него true. После этого выйди из метода.
                            notifyConnectionStatusChanged(true);
                            return;
                        }

                        default: {
                            throw new IOException("Unexpected MessageType");
                        }
                    }
                } else {
                    throw new IOException("Unexpected MessageType");
                }

            }
        }


        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() != null) {

                    switch (message.getType()) {
                        case TEXT:
                            processIncomingMessage(message.getData());
                            break;
                        case USER_ADDED:
                            informAboutAddingNewUser(message.getData());
                            break;
                        case USER_REMOVED:
                            informAboutDeletingNewUser(message.getData());
                            break;
                        default:
                            throw new IOException("Unexpected MessageType");
                    }
                } else {
                    throw new IOException("Unexpected MessageType");
                }

            }
        }



        @Override
        public void run() {
            String adressServer = getServerAddress();
            int port = getServerPort();
            Socket socket = null;

            try {
                socket = new Socket(adressServer, port);
                Connection connection = new Connection(socket);
                Client.this.connection = connection;
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {
                e.printStackTrace( );
                notifyConnectionStatusChanged(false);
            }catch (ClassNotFoundException e) {
                e.printStackTrace( );
                notifyConnectionStatusChanged(false);
            }
        }
    }

    protected String getServerAddress(){
        ConsoleHelper.writeMessage("Введите адрес сервера:");
        return ConsoleHelper.readString();
    }

    protected int getServerPort(){
        ConsoleHelper.writeMessage("Введите порт сервера:");
        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        ConsoleHelper.writeMessage("Введите имя пользователя:");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }

    protected void sendTextMessage(String text){
            try {
                Message message = new Message(MessageType.TEXT, text);
                connection.send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Клиент отключен. Сообщение не было отправлено.");
                clientConnected = false;
            }
    }

    public  void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this){
                this.wait();
                if(clientConnected){
                    ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’");
                    while (clientConnected) {
                        String text = ConsoleHelper.readString();
                        if (text.equalsIgnoreCase("exit")) {
                            break;
                        }else if(shouldSendTextFromConsole()){
                            sendTextMessage(text);
                        }
                    }
                } else {
                    ConsoleHelper.writeMessage("Произошла ошибка соединения");
                }
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("произошла ошибка, программа будет закрыта");
            System.exit(1);

        }
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }




}
