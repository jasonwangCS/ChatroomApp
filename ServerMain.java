import java.io.*;
import java.net.*;

public class ServerMain {
    public static void main(String[] args) {
        try{

            ServerSocket serverSocket = new ServerSocket(8001);
            System.out.println("Server started. Waiting for players...");


            Socket bCon;
            ObjectOutputStream bos;
            ObjectInputStream bis;
            while(true){
                bCon = serverSocket.accept();
                bos = new ObjectOutputStream(bCon.getOutputStream());
                bis = new ObjectInputStream(bCon.getInputStream());
                bos.writeObject(new CommandFromServer(CommandFromServer.CONNECTED, null));
                System.out.println("Guy has connected");

                ServersListener sl = new ServersListener(bis,bos,'B');
                Thread t = new Thread(sl);
                t.start();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
