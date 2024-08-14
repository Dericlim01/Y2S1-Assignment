package src.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class User_Management {
    private String line;
    private TableRowSorter<DefaultTableModel> sorter;

    public JScrollPane view_users(String[] userCol){
    ArrayList<String[]> usersData = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))){
            while((line = read.readLine()) != null) {
                String[] data = line.split(",");
                usersData.add(data);
                }
            }
            catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] userRow = usersData.toArray(new Object[0][]);

        DefaultTableModel users_table = new DefaultTableModel(userRow, userCol);
        JTable table = new JTable(users_table);
        sorter = new TableRowSorter<>(users_table);
        table.setRowSorter(sorter);           
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    public void filter_users(String value) {
        if(value.equals("admin") || value.equals("scheduler") || value.equals("manager") || value.equals("customer")) {
            //row sorter, filter by row
            //regexFilter - searching data in reguar expressions 
            sorter.setRowFilter(RowFilter.regexFilter("^" + value + "$",2));
        }
        else{
            JOptionPane.showMessageDialog(null,"Failed to filter.","filter status",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void load_user (JComboBox<String> usershow) {
        //construct an empty combo box object
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        //set model to JComboBox
        usershow.setModel(model);
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))){
            while((line = read.readLine()) != null){
                String[] data = line.split(",");
                String username = data[0];
                String role = data[2];
                
                //excluded the super admin
                if(!role.equals("superadmin")){
                    //adding element at the end of vector with increasing the size by one
                    model.addElement(username);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
