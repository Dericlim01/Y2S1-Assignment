import java.awt.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;


public class Staff_Management extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    Staff_Management sm = new Staff_Management();
                    sm.setTitle("Staff Management");
                    sm.setVisible(true);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Staff_Management(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140,100,1000,800);
        setResizable(false);

        contentPane = new JPanel();
    }
}
