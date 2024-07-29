import javax.swing.*;
import javax.imageio.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sales_Dashboard extends JFrame {

    private JScrollPane scrollPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try{
                    new Sales_Dashboard().setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public Sales_Dashboard(){
        setTitle("Sales Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 100, 1000, 800);
        setResizable(false);

        JPanel manager_SD = new JPanel();
        manager_SD.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(manager_SD);
        manager_SD.setLayout(new BorderLayout());

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
        manager_SD.add(scrollPane, BorderLayout.CENTER);
        // Set the table's preffered scrollable viewport size
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Filter panel setup ( create another panel for filter)
        JPanel filter_panel = new JPanel();
        filter_panel.setLayout(new FlowLayout());
        //filter_panel.setBounds(450, 100, 100, 50);

        filter_panel.add(new JLabel("Day"));
        JComboBox day = new JComboBox<>();
        for(int i = 1; i <= 31; i++){
            day.addItem(String.valueOf(i));
        }
        filter_panel.add(day);
        
        

        filter_panel.add(new JLabel("Month"));
        String[] Month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JComboBox month = new JComboBox<>(Month);
        filter_panel.add(month);

        
        filter_panel.add(new JLabel("Year"));
        JComboBox year = new JComboBox<>();
        for (int i = 2000; i <= 2100; i++){
            year.addItem(String.valueOf(i));
        }
        filter_panel.add(year);


        month.setBounds(450, 200, 100, 50);
        
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
        refresh.setBounds(100, 100, 100, 50);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager_SD.remove(scrollPane);
                scrollPane = man_data.present_data(col_name);
                manager_SD.add(scrollPane, BorderLayout.CENTER);
                manager_SD.revalidate(); // important, recalculate the layout / resize the panel?
                manager_SD.repaint(); // important, rearranging components
                
                
            }
        });
        filter_panel.add(refresh);


        manager_SD.add(filter_panel, BorderLayout.NORTH);

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
        //         String text = day.getSelectedItem().toString();
        //         // String text2 = month.getSelectedItem().toString();
        //         // String text3 = year.getSelectedItem().toString();
        //         man_data.data_filter(table_Model, table_sorter, text);
        //         //, text2, text3

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

        

        

        
    }

    // private void refresh(){
    //     Sales_Dashboard();
    // }
}
