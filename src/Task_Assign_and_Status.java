import javax.swing.*;
import javax.imageio.*;
import javax.management.remote.JMXServerErrorException;
import javax.swing.border.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

public class Task_Assign_and_Status extends JFrame {
    private static String manname;
    private static JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Task_Assign_and_Status(manname).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Task_Assign_and_Status(String n) {
        setTitle("Task Assign and Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        Manager man_task = new Manager();

        JPanel manager_TAS = new JPanel();
        manager_TAS.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_TAS);
        manager_TAS.setLayout(null);

        JLabel taskAssign_lbl = new JLabel("Task Assign to: ");
        taskAssign_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        taskAssign_lbl.setBounds(50,70, 120, 40);
        manager_TAS.add(taskAssign_lbl);

        JComboBox staff = new JComboBox<>();
        staff.setBounds(160, 83, 120, 20);
        // 
        manager_TAS.add(staff);

        String[] row_name = {"Task ID", "Issues ID", "Issues", "Description", "Username", "Halls ID", "Staff ID", "Handled Staff", "Issues Status"};

        scrollPane = man_task.view_issues(row_name, "issues.txt");
        scrollPane.setBounds(9, 350, 970, 100);
        manager_TAS.add(scrollPane);

    }
}
