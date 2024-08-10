package src;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

import javax.imageio.*;

import java.io.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.ArrayList;


public class Sales_Dashboard extends JFrame {

    private static String manname;
    private JScrollPane scrollPane;
    private DefaultTableModel tm;
    private TableRowSorter<DefaultTableModel> sorter_tm;
    //private List<LocalDate> data;

    private JComboBox<String> day;
    private JComboBox<String> month;
    private JComboBox<String> year;

    private JComboBox<String> end_day;
    private JComboBox<String> end_month;
    private JComboBox<String> end_year;

    private static Integer now_day = LocalDate.now().getDayOfMonth();
    private static Integer now_month = LocalDate.now().getMonthValue();
    private static String now_month_str = String.valueOf(LocalDate.now().getMonth());
    private static String now_month_str_f = now_month_str.substring(0, 1).toUpperCase() + now_month_str.substring(1).toLowerCase();
    private static Integer now_year = LocalDate.now().getYear();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    new Sales_Dashboard(manname).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Sales_Dashboard(String n) {
        setTitle("Sales Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_SD = new JPanel();
        manager_SD.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_SD);
        manager_SD.setLayout(null);

        Manager man_data = new Manager();

        // Define the columns names
        String[] col_name = {"Booking ID", "Hall ID", "Num of Guests", "Start Date", "End Date", "Start Time", "End Time", "Book Status", "Booking Paid", "Deposit Paid", "Username"};

        // Define the data for the table
        // Object [][] represents whole table

        Object[][] row_Data = man_data.present_data("resources/bookings.txt");

        tm = new DefaultTableModel(row_Data, col_name);
        JTable view = new JTable(tm);

        scrollPane = new JScrollPane(view);
        scrollPane.setBounds(9, 125, 970, 400);
        manager_SD.add(scrollPane);

        // Start Day label
        JLabel day_lbl = new JLabel("Start Day : ");
        day_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        day_lbl.setBounds(195, 20, 45, 20);
        manager_SD.add(day_lbl);
        
        // Start Day combo box
        ArrayList<String> day_data = man_data.day_list(now_year, now_month_str_f, now_day);
        String[] days = day_data.toArray(new String [0]);
        day = new JComboBox<>(days);
        day.setBounds(245, 23, 50, 20);
        //day.setSelectedIndex(-1);
        //day.setSelectedIndex(0);
        
        manager_SD.add(day);
        
        // Start Month label
        JLabel month_lbl = new JLabel("Start Month : ");
        month_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        month_lbl.setBounds(325, 23, 60, 20);
        manager_SD.add(month_lbl);

        // Month combo box
        // String[] Month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        ArrayList<String> month_data = man_data.month_list(now_month);
        String[] months = month_data.toArray(new String[0]);
        month = new JComboBox<>(months);
        month.setBounds(395, 23, 100, 20);
        //month.setSelectedIndex(-1);
        // month.addActionListener((new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         //month.removeAllItems();
        //         ArrayList<String> daylist = man_data.day_list(Integer.parseInt(year.getSelectedItem().toString()), String.valueOf(month.getSelectedItem()), day.getSelectedIndex() + 1);

        //         String[] all_day = daylist.toArray(new String[0]);
        //         for ( int i= 0; i < all_day.length; i++) {
        //             day.addItem(all_day[i]);
        //         }
        //     }
        // }));
        manager_SD.add(month);

        // Start Year label
        JLabel year_lbl = new JLabel("Start Year : ");
        year_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        year_lbl.setBounds(530, 23, 60, 20);
        manager_SD.add(year_lbl);

        // Start Year combo box
        ArrayList<String> year_data = man_data.year_list(now_year);
        String[] years = year_data.toArray(new String[0]);
        year = new JComboBox<>(years);  
        year.setBounds(600, 23, 80, 20);
        //year.setSelectedIndex(-1);
        manager_SD.add(year);
        

       ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String txt_day = (String)day.getSelectedItem();
            String txt_month = (String)month.getSelectedItem();
            String txt_year = (String) year.getSelectedItem();


            Object[][] row_data = man_data.data_filter(txt_day, txt_month, txt_year);
            tm.setDataVector(row_data, col_name);
            JTable filter_data = new JTable(tm);
            
            scrollPane = new JScrollPane(filter_data);

            manager_SD.add(scrollPane);
            filter_data.revalidate();
            filter_data.repaint();


        }
       };

       day.addActionListener(actionListener);
       month.addActionListener(actionListener);
       year.addActionListener(actionListener);

       // End Day label
       JLabel end_day_lbl = new JLabel("End Day : ");
       end_day_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
       end_day_lbl.setBounds(195, 60, 45, 20);
       manager_SD.add(end_day_lbl);
       
       // Start Day combo box
       ArrayList<String> end_day_data = man_data.day_list(now_year, now_month_str_f, now_day);
       String[] end_days = end_day_data.toArray(new String [0]);
       end_day = new JComboBox<>(end_days);
       end_day.setBounds(245, 63, 50, 20);
       //day.setSelectedIndex(-1);
       //day.setSelectedIndex(0);
       
       manager_SD.add(end_day);
       
       // Start Month label
       JLabel end_month_lbl = new JLabel("Month : ");
       end_month_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
       end_month_lbl.setBounds(325, 63, 60, 20);
       manager_SD.add(end_month_lbl);

       // End Month combo box
       // String[] Month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
       ArrayList<String> end_month_data = man_data.month_list(now_month);
       String[] end_months = end_month_data.toArray(new String[0]);
       end_month = new JComboBox<>(end_months);
       end_month.setBounds(395, 63, 100, 20);
       //month.setSelectedIndex(-1);
       // month.addActionListener((new ActionListener() {
       //     @Override
       //     public void actionPerformed(ActionEvent e) {
       //         //month.removeAllItems();
       //         ArrayList<String> daylist = man_data.day_list(Integer.parseInt(year.getSelectedItem().toString()), String.valueOf(month.getSelectedItem()), day.getSelectedIndex() + 1);

       //         String[] all_day = daylist.toArray(new String[0]);
       //         for ( int i= 0; i < all_day.length; i++) {
       //             day.addItem(all_day[i]);
       //         }
       //     }
       // }));
       manager_SD.add(end_month);

       // End Year label
       JLabel end_year_lbl = new JLabel("Year : ");
       end_year_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
       end_year_lbl.setBounds(530, 63, 60, 20);
       manager_SD.add(end_year_lbl);

       // End Year combo box
       ArrayList<String> end_year_data = man_data.year_list(now_year);
       String[] end_years = end_year_data.toArray(new String[0]);
       end_year = new JComboBox<>(end_years);  
       end_year.setBounds(600, 63, 80, 20);
       //year.setSelectedIndex(-1);
       manager_SD.add(end_year);



       JButton refresh = new JButton("Refresh");
        refresh.setBounds(720, 43, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_SD.remove(scrollPane);
                Object[][] row_Data = man_data.present_data("resources/bookings.txt");

                DefaultTableModel tm = new DefaultTableModel();
                tm.setDataVector(row_Data, col_name);
                JTable table_tm = new JTable(tm);

                // TableRowSorter<DefaultTableModel> sorter_tm = new TableRowSorter<>(tm);
                // table_tm.setRowSorter(sorter_tm);

                JScrollPane scrollPane = new JScrollPane(table_tm);
                // scrollPane = man_data.present_data(col_name);
                
                scrollPane.setBounds(9, 125, 970, 400);
                manager_SD.add(scrollPane);
                
                table_tm.revalidate(); // important, recalculate the layout / resize the panel?
                table_tm.repaint(); // important, rearranging components
               
                
                
            }
        });
        manager_SD.add(refresh);

        JButton back_btn = new JButton();
        try {
            BufferedImage backImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            backImage = ImageIO.read(new File("D:/sem 1/Java/test/Manager/logout.png"));
            Image back_ima = backImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

            back_btn.setIcon(new ImageIcon(back_ima));
            back_btn.setBounds(920, 23, 25, 25);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Manager_Home_Page man_HP = new Manager_Home_Page(n);
                man_HP.setTitle("Manager Home Page");
                man_HP.setVisible(true);
            }
        });

        manager_SD.add(back_btn);
        
    }

}
