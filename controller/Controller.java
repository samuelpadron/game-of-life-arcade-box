package controller;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import model.Direction;
import model.World;

public class Controller extends JFrame implements KeyListener, ActionListener, Runnable {

    JTextArea displayArea;
    JTextField typingArea;
    World world = World.getInstance();
    static final String newline = System.getProperty("line.separator");

    public Controller(String name) {
        super(name);
    }

    private static void createAndShowGUI() {
        Controller frame = new Controller("Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane();
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane() {
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        getContentPane().add(typingArea, BorderLayout.PAGE_START);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayArea.setText("");
        typingArea.setText("");
        typingArea.requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // displayInfo(e, "KEY PRESSED: ");
        System.out.println(world);
        if (e.getKeyCode() == 37) { // <-
            world.move(Direction.LEFT);
        } else if ((e.getKeyCode() == 38)) {// ^
            world.move(Direction.UP);
        } else if ((e.getKeyCode() == 39)) {// ->
            world.move(Direction.RIGHT);
        } else if ((e.getKeyCode() == 40)) {// v
            world.move(Direction.DOWN);
        } else if ((e.getKeyCode() == 68)) {
            world.incrementPattern();
        } else if ((e.getKeyCode() == 65)) {
            world.decrementPattern();
        } else if ((e.getKeyCode() == 83)) {
            world.placePattern();
        } else if ((e.getKeyCode() == 49)) {
            world.next();
            System.out.println(world);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // displayInfo(e, "KEY RELEASED: ");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    // This is not needed in the end product
        @Override
    public void run() {
        createAndShowGUI();
    }
}
