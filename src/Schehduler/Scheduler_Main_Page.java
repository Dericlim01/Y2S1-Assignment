import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Scheduler_Main_Page extends JFrame {
    public static String name;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                new Scheduler_Main_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public Scheduler_Main_Page(String n){
        setTitle("Scheduler Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);
        
        // Button
        JButton viewProfileBtn = new JButton("View Profile");
        viewProfileBtn.setBounds(500, 200, 220, 45);
        // viewProfileBtn.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
                
        //     }
        //});
        panel.add(viewProfileBtn);

        JButton hallMngBtn = new JButton("Hall Management");
        hallMngBtn.setBounds(500, 300, 220, 45);
        panel.add(hallMngBtn);

        JButton hallSchBtn = new JButton("Hall Scheduling");
        hallSchBtn.setBounds(500, 400, 220, 45);
        panel.add(hallSchBtn);

        JButton viewTaskButton = new JButton("View Task");
        viewTaskButton.setBounds(500, 500, 220, 45);
        panel.add(viewTaskButton);


        //Label
        JLabel titleLabel = new JLabel("Scheduler Main Page");
        titleLabel.setBounds(465, -20, 350, 300);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD|Font.ITALIC, 30));
        panel.add(titleLabel);
    }
}