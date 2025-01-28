import java.io.Serializable;

public class CommandFromServer implements Serializable
{
    // The command being sent to the client
    private int command;
    // Text data for the command
    private String data ="";

    // Command list
    public static final int CONNECTED=0;
    public static final int INVALID_NAME=1;
    public static final int ADD_NAMES=2;
    public static final int DMED=3;
    public static final int MOVE=4;
    public static final int B_WINS =5;
    public static final int R_WINS=6;
    public static final int TIE=7;
    public static final int RESTART_GAME = 8;
    public static final int CHANGE_RED_TO_AGREE_RESTART = 9;
    public static final int CHANGE_BLACK_TO_AGREE_RESTART = 10;

    public static final int TURN_OFF = 11;


    public CommandFromServer(int command, String data) {
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
