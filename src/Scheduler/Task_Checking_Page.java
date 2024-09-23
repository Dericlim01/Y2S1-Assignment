package src.Scheduler;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class Task_Checking_Page extends JFrame {
    public static String name;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Task_Checking_Page(name).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Task_Checking_Page(String name) {
        setTitle("Task Checking Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);
        
        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        // Title Label
        JLabel titleLabel = new JLabel("Task Checking");
        titleLabel.setBounds(340, -80, 370, 300);
        titleLabel.setFont(new Font("Engravers MT",Font.PLAIN,25));
        panel.add(titleLabel);

        // Task ID label
        JLabel taskID_lbl = new JLabel("Task ID: ");
        taskID_lbl.setBounds(210, 130, 100, 30);
        taskID_lbl.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        panel.add(taskID_lbl);

        // Issues Title label
        JLabel issues_title_lbl = new JLabel("Issues:");
        issues_title_lbl.setBounds(225, 220, 100, 30);
        issues_title_lbl.setFont(new Font("Comic Sans Ms", Font.BOLD, 20));
        panel.add(issues_title_lbl);

        // Issues Title Content label
        JLabel issues_title_cnt_lbl = new JLabel();
        issues_title_cnt_lbl.setBounds(300, 220, 400, 30);
        issues_title_cnt_lbl.setFont(new Font("Comic Sans Ms", Font.PLAIN, 20));
        panel.add(issues_title_cnt_lbl);

        // Issue Description label
        JLabel issue_description_lbl = new JLabel("Issue Description:");
        issue_description_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        issue_description_lbl.setBounds(120,310,400,50);
        panel.add(issue_description_lbl);

        // Issue Description Content label
        JLabel issue_description_cnt_lbl = new JLabel();
        issue_description_cnt_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        issue_description_cnt_lbl.setBounds(305,310,400,50);
        panel.add(issue_description_cnt_lbl);

        // Task Description label
        JLabel task_description_lbl = new JLabel("Task Description:");
        task_description_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        task_description_lbl.setBounds(125,450,400,50);
        panel.add(task_description_lbl);

        // Task Description Content label
        JLabel task_description_cnt_lbl = new JLabel();
        task_description_cnt_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        task_description_cnt_lbl.setBounds(305,450,400,50);
        panel.add(task_description_cnt_lbl);

        // Hall ID label
        JLabel hallID_lbl = new JLabel("Hall ID: ");
        hallID_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        hallID_lbl.setBounds(550, 130,150,50);
        panel.add(hallID_lbl);

        // Hall ID Content label
        JLabel hallID_cnt_lbl = new JLabel();
        hallID_cnt_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        hallID_cnt_lbl.setBounds(650, 130,400,50);
        panel.add(hallID_cnt_lbl);

        // Status label
        JLabel status_lbl = new JLabel("Status: ");
        status_lbl.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        status_lbl.setBounds(220,590,300,50);
        panel.add(status_lbl);

        // Status Content label
        JLabel status_cnt_lbl = new JLabel();
        status_cnt_lbl.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
        status_cnt_lbl.setBounds(300,590,400,50);
        panel.add(status_cnt_lbl);

        // TaskID ComboBox
        ArrayList<String> tasksList = new Task_Checking().search_task_assigned(name) ;
        String[] task = tasksList.toArray(new String[0]);
        JComboBox<String> taskID_cmbbx = new JComboBox<String>(task);
        taskID_cmbbx.setBounds(320, 135, 125, 25);
        taskID_cmbbx.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        taskID_cmbbx.setSelectedIndex(-1);
        panel.add(taskID_cmbbx);
        taskID_cmbbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> taskData;
                if (taskID_cmbbx.getSelectedItem() != null){
                    taskData = new Task_Checking().search_task_data(taskID_cmbbx.getSelectedItem().toString());
                    issues_title_cnt_lbl.setText(taskData.get(2));
                    issue_description_cnt_lbl.setText(taskData.get(3));
                    task_description_cnt_lbl.setText(taskData.get(7));
                    hallID_cnt_lbl.setText(taskData.get(5));
                    status_cnt_lbl.setText(taskData.get(8));
                }
            }
        });

        // Done Button
        JButton done_btn = new JButton("Done Task");
        done_btn.setBounds(600,675,220,35);
        done_btn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        done_btn.setBackground(new Color(250,240,230));
        done_btn.setForeground(new Color(128,128,128));
        panel.add(done_btn);
        done_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taskID_cmbbx.getSelectedIndex() != -1) {
                    int choice = JOptionPane.showConfirmDialog(
                        null,
                        "\nComfirmation,Click 'OK' to set this task as done ", 
                        "Confirmation",
                        JOptionPane.OK_CANCEL_OPTION); 
                    // Check the user's choice and display a corresponding message 
                    if (choice == JOptionPane.OK_OPTION) { 
                        if (new Task_Checking().change_task_done(taskID_cmbbx.getSelectedItem().toString())) {
                            JOptionPane.showMessageDialog(
                                null,
                                "Change Successful",
                                "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                            issues_title_cnt_lbl.setText("");
                            issue_description_cnt_lbl.setText("");
                            task_description_cnt_lbl.setText("");
                            hallID_cnt_lbl.setText("");
                            status_cnt_lbl.setText("");
                            ArrayList<String> newTasksList = new Task_Checking().search_task_assigned(name);
                            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(newTasksList.toArray(new String[0]));
                            taskID_cmbbx.setModel(model);
                            taskID_cmbbx.setSelectedIndex(-1);
                        } else {
                            JOptionPane.showMessageDialog(
                                null,
                                "Change Failed",
                                "Error",
                                JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else { 
                    JOptionPane.showMessageDialog(
                        null,
                        "Please Select a Task",
                        "Error",
                        JOptionPane.INFORMATION_MESSAGE);
                }   
            }
        });

        // Add maintainance Button
        JButton add_maintainance_btn = new JButton("Add Maintainance");
        add_maintainance_btn.setBounds(200,675,220,35);
        add_maintainance_btn.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        add_maintainance_btn.setBackground(new Color(250,240,230));
        add_maintainance_btn.setForeground(new Color(128,128,128));
        panel.add(add_maintainance_btn);
        add_maintainance_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Schedule_Maintainance_Page(name).setVisible(true);
            }
        });

        // Home Page Label
        JLabel back_lbl = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\logout.png"));
            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 30, 35, 35);
        } catch (IOException e) {
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Scheduler_Main_Page(name).setVisible(true);
            }
        });
        panel.add(back_lbl);

        // Design 4 Pic
        JLabel des4 = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources\\Image\\design4.png"));
            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(des4);
    }   
}
