import javax.swing.*;

import java.awt.EventQueue;

public class Register extends JFrame{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    Register reg = new Register();
                    reg.setTitle("Register");
                    reg.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}