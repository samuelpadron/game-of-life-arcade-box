package controller;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class Controller extends JFrame implements KeyListener, ActionListener, Runnable {

    JTextArea displayArea;
    JTextField typingArea;
    static final String newline = System.getProperty("line.separator");

    public Controller(String name) {
        super(name);
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        Controller frame = new Controller("Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the content pane.
        frame.addComponentsToPane();

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {

        // JButton button = new JButton("Clear");
        // button.addActionListener(this);

        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        // JScrollPane scrollPane = new JScrollPane(displayArea);
        // scrollPane.setPreferredSize(new Dimension(375, 125));

        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        // getContentPane().add(scrollPane, BorderLayout.CENTER);
        // getContentPane().add(button, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // Clear the text components.
        displayArea.setText("");
        typingArea.setText("");

        // Return the focus to the typing area.
        typingArea.requestFocusInWindow();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        displayInfo(e, "KEY PRESSED: ");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // displayInfo(e, "KEY RELEASED: ");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    
    private void displayInfo(KeyEvent e, String keyStatus){
         
        //You should only rely on the key char if the event
        //is a key typed event.
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            keyString = "key character = '" + c + "'";
        } else {
            int keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")";
        }
         
        int modifiersEx = e.getModifiersEx();
        String modString = "extended modifiers = " + modifiersEx;
        String tmpString = KeyEvent.getModifiersExText(modifiersEx);
        if (tmpString.length() > 0) {
            modString += " (" + tmpString + ")";
        } else {
            modString += " (no extended modifiers)";
        }
         
        String actionString = "action key? ";
        if (e.isActionKey()) {
            actionString += "YES";
        } else {
            actionString += "NO";
        }
         
        String locationString = "key location: ";
        int location = e.getKeyLocation();
        if (location == KeyEvent.KEY_LOCATION_STANDARD) {
            locationString += "standard";
        } else if (location == KeyEvent.KEY_LOCATION_LEFT) {
            locationString += "left";
        } else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
            locationString += "right";
        } else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
            locationString += "numpad";
        } else { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
            locationString += "unknown";
        }
         System.out.println(keyStatus + newline
         + "    " + keyString + newline
         + "    " + modString + newline
         + "    " + actionString + newline
         + "    " + locationString + newline);

        displayArea.append(keyStatus + newline
                + "    " + keyString + newline
                + "    " + modString + newline
                + "    " + actionString + newline
                + "    " + locationString + newline);
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }

    @Override
    public void run() {
        createAndShowGUI();
    }
}