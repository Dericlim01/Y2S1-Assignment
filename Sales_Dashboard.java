import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

import javax.imageio.*;

import java.io.*;

import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.text.DateFormat;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
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
    private List<LocalDate> data;

    

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
        String[] col_name = {"ID", "Name", "Age", "Department", "Date"};

        // Define the data for the table
        // Object [][] represents whole table

       

        // DefaultTableModel table_Model = new DefaultTableModel(row_Data, col_name);
        //JTable view = new JTable();

        

        // Create the TableRowSorter and set it on the Table
        // TableRowSorter<DefaultTableModel> table_sorter = new TableRowSorter<>(table_Model);
        // view.setRowSorter(table_sorter); 

        scrollPane = man_data.present_data(col_name);
        scrollPane.setBounds(9, 70, 970, 400);
        manager_SD.add(scrollPane);
        // Set the table's preffered scrollable viewport size
        scrollPane.setPreferredSize(new Dimension(400,150));


        JLabel day_lbl = new JLabel("Day : ");
        day_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        day_lbl.setBounds(195, 20, 45, 20);
        manager_SD.add(day_lbl);
        
        ArrayList<String> day_data = man_data.day_list(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());
        String[] days = day_data.toArray(new String [0]);
        JComboBox<String> day = new JComboBox<>(days);
        day.setBounds(245, 23, 50, 20);
        day.setSelectedIndex(LocalDateTime.now().getDayOfMonth() - 1);
        // for(int i = 1; i <= 31; i++){
        //     day.addItem(String.format("%2d", i));
        // }
        
        manager_SD.add(day);
        
        
        JLabel month_lbl = new JLabel("Month : ");
        month_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        month_lbl.setBounds(325, 23, 60, 20);
        manager_SD.add(month_lbl);

        // String[] Month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        ArrayList<String> month_data = man_data.month_list();
        String[] months = month_data.toArray(new String[0]);
        JComboBox<String> month = new JComboBox<>(months);
        month.setBounds(395, 23, 100, 20);
        month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                day.removeAllItems();
                ArrayList<String> day_List = man_data.day_list(Integer.parseInt(day.getSelectedItem().toString()), month.getSelectedIndex() + 1);
                String[] allDay = day_List.toArray(new String[0]);
                for (int i = 0; i < allDay.length; i++) {
                    day.addItem(allDay[i]);
                }
            }
        });
        manager_SD.add(month);

        JLabel year_lbl = new JLabel("Year : ");
        year_lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        year_lbl.setBounds(530, 23, 60, 20);
        manager_SD.add(year_lbl);

        ArrayList<String> year_data = man_data.year_list();
        String[] years = year_data.toArray(new String[0]);
        JComboBox<String> year = new JComboBox<>(years);
        year.setBounds(600, 23, 80, 20);
        // for (int i = 2000; i <= 2100; i++) {
        //     year.addItem(String.valueOf(i));
        // }
        // year.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String txt_year = year.getSelectedItem().toString();
        //         man_data.data_filter(txt_year);
        //     }
        // });
        manager_SD.add(year);


        //month.setBounds(450, 200, 100, 50);
        
        // day.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {

        //         String text = day.getSelectedItem().toString();
        //         man_data.data_filter(table_Model, table_sorter,text);
        //         // day.removeAllItems();
        //         // man_data.date_based_on_month(day, month, year);
                
        //     }
        // });

        // JTextField filterTextField = new JTextField(10);
        // filterTextField.setBounds(450, 200, 100, 50);
        // filter_panel.add(filterTextField);

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(720, 23, 80, 20);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_SD.remove(scrollPane);
                scrollPane = man_data.present_data(col_name);
                scrollPane.setBounds(9, 70, 970, 400);
                
                manager_SD.revalidate(); // important, recalculate the layout / resize the panel?
                manager_SD.repaint(); // important, rearranging components
                manager_SD.add(scrollPane);
                
                
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

       ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String txt_day = day.getSelectedItem().toString();
            String txt_month = month.getSelectedItem().toString();
            String txt_year = year.getSelectedItem().toString();


    
            man_data.data_filter(txt_day, txt_month, txt_year);

        }
       };

       day.addActionListener(actionListener);
       month.addActionListener(actionListener);
       year.addActionListener(actionListener);

       
    // day.addActionListener(new ActionListener() {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         ArrayList<String[]> data = new ArrayList<>();
    
    //         JOptionPane.showMessageDialog(null, day.getSelectedItem());
    
    //         String txt_day = day.getSelectedItem().toString();
    //         String txt_month = month.getSelectedItem().toString();
    //         String txt_year = year.getSelectedItem().toString();
    
    //         man_data.data_filter(txt_day, txt_month, txt_year);
    
    //         try {
    //             FileReader fr = new FileReader("Sales.txt");
    //             BufferedReader br = new BufferedReader(fr);
    //             String read;
    //             while ((read = br.readLine()) != null) {
    //                 String[] fields = read.split(",");
    //                 String dateData = fields[4];
    //                 String[] dateParts = dateData.split("-");
    //                 String dayPart = dateParts[0];
    
    //                 if (dayPart.equals(txt_day)) {
    //                     System.out.println(day.getSelectedItem().toString().trim());
    //                 }
    //             }
    //             br.close();
    //             fr.close();
    //         } catch (IOException io) {
    //             System.out.println(io.getMessage());
    //         }
    //     }
    // });
        // month.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String text = day.getSelectedItem().toString();
        //         man_data.data_filter(table_Model, table_sorter,text);
        //         //, text2, text3


        //     }
        // });

        // year.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         String text = day.getSelectedItem().toString();
        //         man_data.data_filter(table_Model, table_sorter,text);


        //     }
        // });

        /* 
        public void itemStateChanged(ItemEvent e)
        {
            // if the state combobox is changed
            if (e.getSource() == c1) {
     
                l1.setText(c1.getSelectedItem() + " selected");
            }
        }*/
        
        // day.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {

        //         String line_data;
        //         ArrayList<String[]> data = new ArrayList<>();
                
        //         try(BufferedReader read_file = new BufferedReader(new FileReader("Sales.txt"))){
        //             while((line_data = read_file.readLine()) != null){
        //                 String[] row = line_data.split(",");x
        //                 data.add(row);

        //                 for(int i = 0; i < row.length; i++){
        //                     String date_data = row[i][4];

        //                 }

                        

        //             }
        //         }catch(IOException ie){
        //             ie.printStackTrace();
        //         }

        //         JOptionPane.showMessageDialog(null,day.getSelectedItem());
                
        //         String txt_day = day.getSelectedItem().toString();
        //         String txt_month = month.getSelectedItem().toString();
        //         String txt_year = year.getSelectedItem().toString();

                
        //         man_data.data_filter(txt_day, txt_month, txt_year);
        //         try{
        //             FileReader fr = new FileReader("Sales.txt");
        //             BufferedReader br = new BufferedReader(fr);
        //             String read;
        //             while((read = br.readLine())!=null){
        //                String dateData = read.split(",")[4]; 
        //                DateFormat df = new SimpleDateFormat(day);
        //                if(dateData=="01"){
        //                 System.out.println(day.getSelectedItem().toString().trim());
        //                }
        //             }
        //         } catch(IOException io){
        //             System.out.println(io.getMessage());
        //         }
        //     }
        // });
        
    }

    // private void refresh(){
    //     Sales_Dashboard();
    // }
}
