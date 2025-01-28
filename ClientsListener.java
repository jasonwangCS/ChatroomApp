import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientsListener implements Runnable
{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private ConnectFourFrame frame = null;

    public ClientsListener(ObjectInputStream is,
                           ObjectOutputStream os, ConnectFourFrame frame) {
        this.is = is;
        this.os = os;
        this.frame = frame;

    }

    @Override
    public void run() {
        try
        {
            while(true)
            {
                CommandFromServer cfs = (CommandFromServer)is.readObject();
                if(cfs.getCommand() == cfs.TURN_OFF){
                    System.out.println("closed");
                }
                // processes the received command
                if(cfs.getCommand() == CommandFromServer.DMED){
                    String cook = cfs.getData();
                    String[] cook2 = cook.split("%%%");
                    for(int i = 0; i < cook2.length; i++){
                        System.out.println(cook2[i]);
                    }

                    // add functionality for showing message on frame (frame.showmessage or something)
                    System.out.println("updateMessages");
                    frame.messageUpdate(cook2);

                }

                else if(cfs.getCommand() == CommandFromServer.ADD_NAMES){
                    String[] cook2 = cfs.getData().split("%%%");
                    // add functionality here to show the names on the screen
                    frame.addNames(cook2);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}