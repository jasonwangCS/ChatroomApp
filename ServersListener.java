import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ServersListener implements Runnable
{
    private ObjectInputStream is;
    private ObjectOutputStream os;

    // Stores the which player this listener is for
    private char player;

    // static data that is shared between both listeners
    private static char turn = 'B';
    private static GameData gameData = new GameData();
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();


    public ServersListener(ObjectInputStream is, ObjectOutputStream os, char player) {
        this.is = is;
        this.os = os;
        this.player = player;
        outs.add(os);
    }

    @Override
    public void run() {
        try
        {
            while(true)
            {
                CommandFromClient cfc = (CommandFromClient) is.readObject();
                if(cfc.getCommand() == CommandFromClient.TURN_OFF){
                    for (int i = 0; i < names.size(); i++) {
                        if (cfc.getData().equals(names.get(i))) {
                            names.remove(i);
                            break;
                        }
                    }
                    StringBuilder peenpeen = new StringBuilder();
                    for (String name : names) {
                        peenpeen.append(name).append("%%%");
                    }
                    sendCommand(new CommandFromServer(CommandFromServer.ADD_NAMES, peenpeen.toString()));
                }
                if(cfc.getCommand() == CommandFromClient.CHECK_NAME)
                {
                    System.out.println("Checking");
                    String valid = "true";
                    for (String name : names) {
                        if (cfc.getData().equals(name)) {
                            valid = "false";
                            break;
                        }
                    }
                    names.add(cfc.getData());
                    sendCommand(new CommandFromServer(CommandFromServer.INVALID_NAME, (String)valid));
                    StringBuilder peenpeen = new StringBuilder();
                    for (String name : names) {
                        peenpeen.append(name).append("%%%");
                    }
                    sendCommand(new CommandFromServer(CommandFromServer.ADD_NAMES, peenpeen.toString()));
                }
                if(cfc.getCommand() == CommandFromClient.DMING)
                {
                    sendCommand(new CommandFromServer(CommandFromServer.DMED, cfc.getData()));
                }

                if(cfc.getCommand() == CommandFromClient.DMING){
                    System.out.println("we made it this far");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void sendCommand(CommandFromServer cfs)
    {
        // Sends command to both players
        for (ObjectOutputStream o : outs) {
            try {
                o.writeObject(cfs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
