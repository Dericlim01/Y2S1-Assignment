package src.Admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
    private String role;
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
                role = data[2];
                
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

    public void admin_load_user (JComboBox<String> usershow) {
        //construct an empty combo box object
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        //set model to JComboBox
        usershow.setModel(model);
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))){
            while((line = read.readLine()) != null){
                String[] data = line.split(",");
                String username = data[0];
                role = data[2];
                
                //excluded the super admin and admin
                if(!role.equals("superadmin") && !role.equals("admin")){
                    //adding element at the end of vector with increasing the size by one
                    model.addElement(username);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean delete_users(String username){
        ArrayList<String> usersData = new ArrayList<>();
        boolean delete = false;
        try(BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            while((line = read.readLine()) != null){
                String[] data = line.split(",");
                String getuser = data[0];
                role = data[2];
                if(getuser.equals(username)){
                    if(role.equals("scheduler") || role.equals("manager")){
                        delete = true;
                        delete_staff(getuser);
                        continue;
                    }
                    else if(role.equals("customer")){
                        delete = true;
                        delete_customer(getuser);
                        continue;
                    }
                    else if (role.equals("admin")){
                        delete = true;
                        continue;
                    }
                }
                usersData.add(line);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if(delete){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/users.txt"))) {
                for(String user: usersData){
                    writer.write(user);
                    writer.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return delete;
    }

    public Boolean delete_staff(String staffname) {
        ArrayList<String> staffData = new ArrayList<>();
        boolean delete = false;
        try(BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"))) {
            while((line = read.readLine())!= null){
                String[] data = line.split(",");
                if (data[0].equals(staffname)){
                    delete = true;
                    //when succeffully searching and matching staff name, continue to delete
                    continue;

                }
                staffData.add(line);
        }          
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if(delete){
            //Ensure all line are written back to the file
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/staffs.txt"))) {
                for(String staff: staffData){
                    writer.write(staff);
                    writer.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return delete;
    }

    public Boolean delete_customer(String cusname) {
        ArrayList<String> cusData = new ArrayList<>();
        boolean delete = false;
        try(BufferedReader read = new BufferedReader(new FileReader("resources/Database/customers.txt"))) {
            while((line = read.readLine())!= null){
                String[] data = line.split(",");
                if (data[0].equals(cusname)){
                    delete = true;
                    //when succeffully searching and matching staff name, continue to delete
                    continue;

                }
                cusData.add(line);
        }          
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if(delete){
            //Ensure all line are written back to the file
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/customers.txt"))) {
                for(String customer: cusData){
                    writer.write(customer);
                    writer.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return delete;
    }


}
