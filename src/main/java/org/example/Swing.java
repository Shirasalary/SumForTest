package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Swing extends JFrame{

    // **לשים לב!! יש לשנות את גודל הקבועים בהתאם

    /* איך להגדיר חלון JFrame
    *  this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("title");
        * Panel panelName = new Panel(Constants.X_WINDOW, Constants.Y_WINDOW, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.add(panelName);
        this.setVisible(true); */

     /* איך להגדיר חלון JPanel
    *  this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setVisible(true); */


    // מציירת לי את החלון להכניס גם את השורה שבהערה
    public void paintComponent(Graphics graphics){

        //super.paintComponent(graphics);
    }

    public static final Font myFont = new Font("Gisha", Font.BOLD,20);


    public static final int CHECK_BOX_WIDTH = 250, CHECK_BOX_HEIGHT = 50;

    public static JCheckBox newCheckBox(String text, int x, int y){
        JCheckBox jCheckBox = new JCheckBox(text);
        jCheckBox.setBounds(x,y,CHECK_BOX_WIDTH,CHECK_BOX_HEIGHT);
        jCheckBox.setFont(myFont);
        jCheckBox.setVisible(true);
        return jCheckBox;
    }

    public static final int LABEL_WIDTH = 175, LABEL_HEIGHT = 35;
    public static JLabel newLabel (String text, int x, int y)
    {
        JLabel label = new JLabel(text);
        label.setBounds(x,y,LABEL_WIDTH,LABEL_HEIGHT);
        label.setFont(myFont);
        label.setVisible(true);
        return label;
    }

    public static final int BUTTON_WIDTH = 150, BUTTON_HEIGHT = 30;
    public static JButton newButton (String text, int x, int y)
    {
        JButton button = new JButton(text);
        button.setBounds(x,y,BUTTON_WIDTH,BUTTON_HEIGHT);
        button.setFocusable(false);
        button.setFont(myFont);
        return button;
    }

    public static List<String> convertArrayToList(String[] array){
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(array));
        return result;
    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static final int TEXT_AREA_WIDTH = 200, TEXT_AREA_HEIGHT = 300;
    public static JTextArea newTextArea (String text, int x, int y){

        JTextArea textArea = new JTextArea(text);
        textArea.setBounds(x, y,TEXT_AREA_WIDTH,TEXT_AREA_HEIGHT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
}
