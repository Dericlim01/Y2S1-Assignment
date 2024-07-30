import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Customer_Issues_Receive extends JFrame {
    private static String manname;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Customer_Issues_Receive(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Customer_Issues_Receive(String n){
        setTitle("Customer Issues Receive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_CIR = new JPanel();
        manager_CIR.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_CIR);
        manager_CIR.setLayout(new BorderLayout());

        Manager man_issues = new Manager();

        String[] col_name = {"Username", "Hall Type", "Issues", "Issues Description", "Date"};

        scrollPane = man_issues.view_issues(col_name);

        manager_CIR.add(scrollPane);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(100, 100, 100, 50);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_CIR.remove(scrollPane);
                scrollPane = man_issues.view_issues(col_name);
                manager_CIR.add(scrollPane, BorderLayout.CENTER);
                manager_CIR.revalidate();
                manager_CIR.repaint();
            }
        });
        panel2.add(refresh);

        manager_CIR.add(panel2, BorderLayout.NORTH);






        
    }
}
