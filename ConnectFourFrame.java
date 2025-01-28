import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Array;
import java.util.*;

public class ConnectFourFrame extends JFrame implements ActionListener, WindowListener, MouseListener {

    private String text = "";
    JFrame frame = new JFrame("Chatroom");
    JTextArea textArea;
    JTextArea messageBox;
    JScrollPane messagesDisplay;
    JList<String> messagelist;

    JButton send, exit;

    JScrollPane userDisplay;
    JList<String> userlist;
    private String player;

    private GameData gameData = null;

    private boolean gameOver = false;

    public boolean redAlreadyRightClicked = false;
    public boolean blackAlreadyRightClicked = false;

    Font myFont = new Font("Ink Free", Font.BOLD, 18);

    ArrayList<String> finalMessages = new ArrayList<>();
    ObjectOutputStream os;

    public ConnectFourFrame(GameData gameData, ObjectOutputStream os, String player) {
        super("Chatroom");
        this.gameData = gameData;
        this.os = os;
        this.player = player;

        frame.setLayout(null);
        frame.addMouseListener(this);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(this);


        //textArea = new JTextArea(String.valueOf(player));
        //textArea.setBounds(20,50, 600,600);
        //textArea.setFont(myFont);
        //textArea.setBorder();
        //textArea.setVisible(true);

        //add(textArea);
        String [] tmp = {};
        messagelist = new JList<>(tmp);
//        contactList.setViewportView(list);
        messagesDisplay = new JScrollPane(messagelist);

        messagesDisplay.setBounds(20, 35, 500, 500);
        //contactList.createVerticalScrollBar();


        //contactList.setViewportBorder(BorderFactory.createLineBorder(Color.black));
        messagesDisplay.setVisible(true);
        messagesDisplay.createVerticalScrollBar();
        //0 contactList.


        String [] wee = {"bob", "peepee"};
        userlist = new JList<>(wee);
//        contactList.setViewportView(list);
        userDisplay = new JScrollPane(userlist);

        userDisplay.setBounds(540, 35, 150, 500);
        //contactList.createVerticalScrollBar();


        //contactList.setViewportBorder(BorderFactory.createLineBorder(Color.black));
        userDisplay.setVisible(true);
        userDisplay.createVerticalScrollBar();
        //0 contactList.

        messageBox = new JTextArea("test message");
        messageBox.setFont(myFont);
        messageBox.setEditable(true);
        messageBox.setBounds(20, 550, 500, 110);
        messageBox.setVisible(true);

        send = new JButton("Send");
        send.setFont(myFont);
        send.addActionListener(this);
        send.setBounds(540, 550, 150, 50);

        exit = new JButton("Exit");
        exit.setFont(myFont);
        exit.addActionListener(this);
        exit.setBounds(540, 610, 150, 50);

        frame.add(userDisplay);
        frame.add(messagesDisplay);
        frame.add(messageBox);
        frame.add(send);
        frame.add(exit);
        frame.setSize(750, 750);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    public void addNames(String[] users)
    {
        userlist = new JList<>(users);
        userDisplay.setViewportView(userlist);

    }
    public void messageUpdate(String[] messages)
    {
        finalMessages.add(messages[0] + ": " + messages[1]);
        String [] PENIS = finalMessages.toArray(new String[0]);
        messagelist = new JList<>(PENIS);
        messagesDisplay.setViewportView(messagelist);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void windowOpened(WindowEvent e) { }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            System.out.println("CLSOINGEG");

            try {
                os.writeObject(new CommandFromClient(CommandFromClient.DMING, player + "%%%" + " has disconnected"));
            } catch (Exception ex) {
                System.out.println("Failed");
                ex.printStackTrace();
            }
            os.writeObject(new CommandFromClient(CommandFromClient.TURN_OFF, player));

        } catch (Exception ex) {
            System.out.println("Failed");
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {



    }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == send)
        {
            String message = messageBox.getText();
            messageBox.setText("");

            //add message into messagesList on all clients through server listener?
            try {
                os.writeObject(new CommandFromClient(CommandFromClient.DMING, player + "%%%" + message));
            } catch (Exception ex) {
                System.out.println("Failed");
                ex.printStackTrace();
            }
        }
        if(e.getSource() == exit)
        {
            //remove user from user list, and display user has disconnected message
//            try {
//                System.out.println("CLSOINGEG");
//                try {
//                    os.writeObject(new CommandFromClient(CommandFromClient.DMING, player + "%%%" + " has disconnected"));
//                } catch (Exception ex) {
//                    System.out.println("Failed");
//                    ex.printStackTrace();
//                }
//                os.writeObject(new CommandFromClient(CommandFromClient.TURN_OFF, player));
//            } catch (Exception ex) {
//                System.out.println("Failed");
//                ex.printStackTrace();
//            }

            //close window
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        }
    }
}