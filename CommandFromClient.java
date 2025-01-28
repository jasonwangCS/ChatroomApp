import java.io.Serializable;

public class CommandFromClient implements Serializable
{

    private int command;

    private String data ="";


    public static final int MOVE    =0;
    public static final int CHECK_NAME =1;
    public static final int REDAGREERESTART =2;
    public static final int BLACKAGREERESTART =3;

    public static final int DMING = 5;

    public static final int TURN_OFF = 4;

    public CommandFromClient(int command, String data) {
        this.command = command;
        this.data = data;
    }

    public int getCommand() {
        return command;
    }

    public String getData() {
        return data;
    }
}

