package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//מחלקה של מקלדות
public class Movement implements KeyListener {

    public Movement(){

        // כאשר אני בונה את המחלקה של האובייקט ששם משתמשים במקלדה צריך לבקש את הפוקוס לשם
        // className.requestFocus();

        //צריך לרשום את זה במחלקה className
        //this.addKeyListener(new Movement());
    }


    public void keyTyped(KeyEvent e) {

    }


    // מה קורה שהוא לוחץ על כפתור במקלדת
    public void keyPressed(KeyEvent e) {

        // מה יקרה שהוא ילחץ על רווח
        if (e.getKeyCode() == KeyEvent.VK_SPACE ){

        }
    }


    //מה שיקרה אחרי שהוא שחרר את הכפתור
    public void keyReleased(KeyEvent e) {

    }
}
