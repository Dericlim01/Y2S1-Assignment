package src.Manager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.imageio.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import java.util.List;
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
        manager_CIR.setBackground(new Color(248,248,248));
        setContentPane(manager_CIR);
        manager_CIR.setLayout(null);

        Manager man_issues = new Manager();

        String[] col_name = {"Issue ID", "Issue", "Description","Hall ID","Hall Type","Username"};
        Object[][] row_data = man_issues.present_data("resources/Database/issues.txt");
        table_Model = new DefaultTableModel();
        table_Model.setDataVector(row_data, col_name);
        JTable view = new JTable(table_Model);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 105, 970, 550);

        manager_CIR.add(scrollPane);
        //scrollPane.setViewportView(table_Model);
        //scrollPane.setPreferredSize(new Dimension(200, 150));

        // Logo Label
        JLabel logo_lbl = new JLabel("Symphony Hall");
        logo_lbl.setFont(new Font("French Script MT", Font.BOLD, 25));
        logo_lbl.setForeground(new Color(169, 169, 169));
        logo_lbl.setBounds(60, 20, 160, 30);
        manager_CIR.add(logo_lbl);

        // Logo Picture
        JLabel logo = new JLabel();
        try {
            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            get_image = ImageIO.read(new File("resources/Image/hall (1).png"));
            Image image = get_image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

            logo.setIcon(new ImageIcon(image));
            logo.setBounds(0, 0, 65, 65);
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager_CIR.add(logo);


        // Page Name Label
        JLabel cus = new JLabel("Customer Issues Receive");
        cus.setFont(new Font("Engravers MT", Font.PLAIN, 20));
        cus.setBounds(300, 20, 400, 20);
        manager_CIR.add(cus);

        // Hall Type filter Label
        JLabel hall = new JLabel("Hall Type:");
        hall.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
        hall.setBounds(50, 70, 80, 20);
        manager_CIR.add(hall);

        // Hall Type combo box
        ArrayList<String> hall_Type = man_issues.hall_type();
        String[] hall_data = hall_Type.toArray(new String[0]);
        JComboBox<String> hall_cb = new JComboBox<String>(hall_data);
        hall_cb.setBackground(new Color(250,240,230));
        hall_cb.setForeground(new Color(128,128,128));
        hall_cb.setBounds(125, 72, 120, 20);
        hall_cb.setSelectedIndex(-1);
        hall_cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hallType = String.valueOf(hall_cb.getSelectedItem());
                Object[][] hall_Data = man_issues.hallData(hallType);
                table_Model.setDataVector(hall_Data, col_name);
                table_Model.fireTableDataChanged();
                view.revalidate();
                view.repaint();
            }
        });
        
        manager_CIR.add(hall_cb);

        // Assign Staff
        List<String> issues_Row = new ArrayList<>();
        JButton assign = new JButton("Assign Staff");
        assign.setBackground(new Color(250,240,230));
        assign.setForeground(new Color(128,128,128));
        assign.setBounds(800, 675, 110, 20);
        assign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                
                if(selectedRow >= 0){
                    int col_count = view.getColumnCount();
                    if(col_count >= 5 ){

                        for(int i = 0; i < col_count; i++){
                            issues_Row.add(view.getValueAt(selectedRow, i).toString());
                        }

                        new Task_Assign(n, issues_Row).setVisible(true);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Invalid row data");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"Please select the row");
                }


            }
        });
        manager_CIR.add(assign);

        // Refresh Button
        JButton refresh = new JButton("Refresh");
        refresh.setBackground(new Color(250,240,230));
        refresh.setForeground(new Color(128,128,128));
        refresh.setBounds(720, 68, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // manager_CIR.remove(scrollPane);
                // Object[][] row_data = man_issues.present_data("resources/Database/issues.txt");
                // table_Model = new DefaultTableModel();
                table_Model.setDataVector(row_data, col_name);

                // JTable view = new JTable(table_Model);

                // scrollPane = new JScrollPane(view);
                //scrollPane.setBounds(9, 105, 970, 550);

                //manager_CIR.add(scrollPane);
                // table_Model.setDataVector(row_data, hall_data);
                table_Model.fireTableDataChanged();
                view.revalidate();
                view.repaint();
            }
        });
        manager_CIR.add(refresh);
        
        // Reply Customer
        JButton reply = new JButton("Respond");
        reply.setBackground(new Color(250,240,230));
        reply.setForeground(new Color(128,128,128));
        reply.setBounds(50, 675, 100, 20);
        reply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getSelectedRow();
                if(selectedRow >= 0){
                    int col_count = view.getColumnCount();
                    if(col_count >= 5 ){
                        String Issues_ID = view.getValueAt(selectedRow, 0).toString();
                        String Issue = view.getValueAt(selectedRow, 1).toString();
                        String description = view.getValueAt(selectedRow, 2).toString();
                        String hallType = view.getValueAt(selectedRow, 3).toString();
                        String userName = view.getValueAt(selectedRow, 4).toString();

                        issues_Row.add(Issues_ID);
                        issues_Row.add(Issue);
                        issues_Row.add(description);
                        issues_Row.add(hallType);
                        issues_Row.add(userName);
                        
                        new Reply_Customer(n, issues_Row).setVisible(true);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Invalid row data");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"Please select the row");
                }
            }
        });;
        manager_CIR.add(reply);

        // Back to Home Page Label
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


        //Design 4 Background Pic
        JLabel des4 = new JLabel();
        try{

            BufferedImage get_image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

            get_image = ImageIO.read(new File("resources/Image/design4.png"));

            Image image = get_image.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);

            des4.setIcon(new ImageIcon(image));
            des4.setBounds(0, 0, 1000, 800);

        } catch(IOException e){
            e.printStackTrace();
        }
        manager_CIR.add(des4);







        
    }
}
