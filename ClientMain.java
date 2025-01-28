import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain
{
    public static void main(String[] args)
    {
        try {
            System.out.println("Hi");
            GameData gameData = new GameData();
            Scanner scan = new Scanner(System.in);
            String userName;
            ObjectInputStream is;
            ObjectOutputStream os;
            ConnectFourFrame frame;
            System.out.println("Hi3");
            Socket socket = new Socket("127.0.0.1", 8001);
            System.out.println("Hi4");
            is = new ObjectInputStream(socket.getInputStream());
            os = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Hi2");
            // Create the Frame based on which player the server says this client is
            while(true) {
                System.out.println("Enter username");

                userName = scan.nextLine();
                CommandFromServer cfs;
                os.writeObject(new CommandFromClient(CommandFromClient.CHECK_NAME, (String)userName));
                do{
                     cfs = (CommandFromServer)is.readObject();
                     System.out.println("Checking2");
                }while(cfs.getCommand() != CommandFromServer.INVALID_NAME);
                if(cfs.getCommand() == CommandFromServer.INVALID_NAME){
                    if(cfs.getData().equals("true")){
                        break;
                    }
                }
            }
            frame = new ConnectFourFrame(gameData,os,userName);
            ClientsListener cl = new ClientsListener(is,os, frame);
            Thread t = new Thread(cl);
            t.start();
            try {
                os.writeObject(new CommandFromClient(CommandFromClient.DMING, userName + "%%%" + " has connected."));
            } catch (Exception ex) {
                System.out.println("Failed");
                ex.printStackTrace();
            }

            // Starts a thread that listens for commands from the server
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
