package src.Manager;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Image;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;


public class View_Customer_Issues extends JFrame{
    private static String manname;
    private JScrollPane scrollPane;
    private DefaultTableModel tm;

    private JTable view;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    new View_Customer_Issues(manname).setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public View_Customer_Issues(String n){
        setTitle("View Customer Issues");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_VCI = new JPanel();
        manager_VCI.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_VCI);
        manager_VCI.setLayout(null);

        Manager man_issues = new Manager();

        String[] col_name = {"Username", "Hall Type", "Issues", "Issues Description", "Date"};
        Object[][] row_Data = man_issues.present_data("resources/Database/issues.txt");
        tm = new DefaultTableModel(row_Data, col_name);
        view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 125, 970, 400);
        manager_VCI.add(scrollPane);

        // Hall Type filter Label
        JLabel hall = new JLabel("Hall Type:");
        hall.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        hall.setBounds(50, 25, 80, 20);
        manager_VCI.add(hall);

        // Hall Type combo box
        ArrayList<String> hall_Type = man_issues.hall_type();
        String[] hall_data = hall_Type.toArray(new String[0]);
        JComboBox<String> hall_cb = new JComboBox<String>(hall_data);
        hall_cb.setBounds(125, 27, 120, 20);
        hall_cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hallType = String.valueOf(hall_cb.getSelectedItem());
                Object[][] hall_Data = man_issues.hallData(hallType);
                tm.setDataVector(hall_Data, col_name);
                view.revalidate();
                view.repaint();
            }
        });
        hall_cb.setSelectedIndex(-1);
        manager_VCI.add(hall_cb);


        JButton refresh = new JButton("Refresh");
        refresh.setBounds(720, 23, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_VCI.remove(scrollPane);
                Object[][] row_data = man_issues.present_data("resources/Database/issues.txt");
                tm = new DefaultTableModel();
                tm.setDataVector(row_data, col_name);
                JTable view = new JTable(tm);

                scrollPane = new JScrollPane(view);
                scrollPane.setBounds(9, 125, 970, 400);

                manager_VCI.add(scrollPane);
                view.revalidate();
                view.repaint();
            }
        });
        manager_VCI.add(refresh);

        JButton back_btn = new JButton();
        try{
            BufferedImage backImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            backImage = ImageIO.read(new File("resources/Image/logout.png"));
            Image back_ima = backImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            back_btn.setIcon(new ImageIcon(back_ima));
            back_btn.setBounds(920, 23, 25, 25);

        }catch(IOException e){
            e.printStackTrace();
        }

        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Manager_Home_Page man_HP = new Manager_Home_Page(n);
                man_HP.setVisible(true);
            }
        });

        manager_VCI.add(back_btn);
    }
}
