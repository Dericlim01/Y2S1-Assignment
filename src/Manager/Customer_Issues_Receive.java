package src.Manager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.imageio.*;

//import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Customer_Issues_Receive extends JFrame {
    private static String manname;
    private JScrollPane scrollPane;
    private DefaultTableModel table_Model;

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
        manager_CIR.setLayout(null);

        Manager man_issues = new Manager();

        

        String[] col_name = {"Username", "Hall Type", "Issues", "Issues Description", "Date"};
        Object[][] row_data = man_issues.present_data("resources/Database/issues.txt");
        table_Model = new DefaultTableModel();
        table_Model.setDataVector(row_data, col_name);
        JTable view = new JTable(table_Model);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 100, 970, 400);

        manager_CIR.add(scrollPane);
        //scrollPane.setViewportView(table_Model);
        //scrollPane.setPreferredSize(new Dimension(200, 150));

        // Hall Type filter Label
        JLabel hall = new JLabel("Hall Type:");
        hall.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        hall.setBounds(50, 25, 80, 20);
        manager_CIR.add(hall);

        // Hall Type combo box
        ArrayList<String> hall_Type = man_issues.hall_type();
        String[] hall_data = hall_Type.toArray(new String[0]);
        JComboBox<String> hall_cb = new JComboBox<String>(hall_data);
        hall_cb.setBounds(125, 27, 120, 20);
        hall_cb.setSelectedIndex(-1);
        hall_cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hallType = String.valueOf(hall_cb.getSelectedItem());
                Object[][] hall_Data = man_issues.hallData(hallType);
                table_Model.setDataVector(hall_Data, col_name);
                view.revalidate();
                view.repaint();
            }
        });
        
        manager_CIR.add(hall_cb);

        JButton assign = new JButton("Assign Staff");
        assign.setBounds(800, 600, 110, 20);
        assign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if(selectedRow >= 0){
                    dispose();
                    new Task_Assign(selectedRow, n).setVisible(true);

                }else{
                    JOptionPane.showMessageDialog(null,"Please select the row");
                }


            }
        });
        manager_CIR.add(assign);

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(720, 23, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_CIR.remove(scrollPane);
                Object[][] row_data = man_issues.present_data("resources/Database/issues.txt");
                table_Model = new DefaultTableModel();
                table_Model.setDataVector(row_data, col_name);
                JTable view = new JTable(table_Model);

                scrollPane = new JScrollPane(view);
                scrollPane.setBounds(9, 70, 970, 400);

                manager_CIR.add(scrollPane);
                view.revalidate();
                view.repaint();
            }
        });
        manager_CIR.add(refresh);

        JLabel back_lbl = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/logout.png"));

            Image image = get_image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);

            back_lbl.setIcon(new ImageIcon(image));
            back_lbl.setBounds(920, 15, 35, 35);

        } catch(IOException e){
            e.printStackTrace();
        }
        back_lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back_lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Manager_Home_Page(n).setVisible(true);         
            }

        });
        manager_CIR.add(back_lbl);







        
    }
}
