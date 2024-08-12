package src.Admin;

import src.Create_file;

import java.io.*;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.TableRowSorter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;

public class Staff_Management {
    private String line;
    private TableRowSorter<DefaultTableModel> sorter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        public Staff_Management(String name) {

        }

        //Check the unique staffname
        Create_file file = new Create_file();
        public Boolean check_staff (String name) {
        if(file.staffs_file()){
            try (BufferedReader read_staff = new BufferedReader(new FileReader("resources/Database/staffs.txt"))){
                while ((line = read_staff.readLine()) != null){
                    String[] data = line.split(",");
                    String staffname = data[0];
                    if(staffname.equals(name)){
                        return false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }    

    }
    return true;
    }

    //Staff Management
    public Boolean add_staff(String staffname, String password, String phone, String email, Date D_O_B, String gender, String role){
        try {      
                String dobFormat = dateFormat.format(D_O_B);
                String[] staffsData = {
                    staffname,
                    password,
                    phone,
                    email,
                    dobFormat,
                    gender,
                    role
                };
                String stSplitData = String.join(",", staffsData);
                FileWriter staffs = new FileWriter("resources/Database/staffs.txt",true);
                staffs.write(stSplitData + "\n");
                staffs.close();
                return true;
        }catch (Exception e) {
            System.out.println("Error occured.");
            e.printStackTrace();
        }
        return false;
    }

    //Search staff and view in table
    public JScrollPane view_staff(String[] staffCol){
        ArrayList<String[]> staffData = new ArrayList<>();
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"))){
                while((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    staffData.add(data);
                    }
                }
                catch (Exception e) {
                e.printStackTrace();
            }
            Object[][] staffRow= staffData.toArray(new Object[0][]);

            DefaultTableModel staff_table = new DefaultTableModel(staffRow,staffCol);
            JTable table = new JTable(staff_table);
            sorter = new TableRowSorter<>(staff_table);
            table.setRowSorter(sorter);           
            JScrollPane scrollPane = new JScrollPane(table);
            return scrollPane;
    }


    public void filter_staff(String option, String value) {
        //Two main option to filter
        //Gender option
        if(option.equals("Gender")){
            if(value.equals("male") || value.equals("female")) {
                //row sorter, filter by row
                //regexFilter - searching data in reguar expressions 
                //^/$ anchor of the string beginning and ending (prevent searching male in String female)         
                sorter.setRowFilter(RowFilter.regexFilter("^" + value + "$",5));
            }
            else{
                JOptionPane.showMessageDialog(null,"Failed to filter.","filter status",JOptionPane.PLAIN_MESSAGE);
            }
      
        }
        //Role option
        else if(option.equals("Role")) {
            if(value.equals("scheduler") || value.equals("manager")) {
                sorter.setRowFilter(RowFilter.regexFilter(value,6));
            }
            else{
                JOptionPane.showMessageDialog(null, "Failed to filter.","filter status",JOptionPane.PLAIN_MESSAGE);
            }
        }

    }

    public void load_staff (JComboBox<String> staffshow) {
        //construct an empty combo box object
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        //set model to JComboBox
        staffshow.setModel(model);
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"))){
            while((line = read.readLine()) != null){
                String[] data = line.split(",");
                String staffname = data[0];
                //adding element at the end of vector with increasing the size by one
                model.addElement(staffname);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean edit_staff(String staffname, String password, String phone, String email, Date D_O_B, String gender, String role){
        ArrayList<String> staffnewData = new ArrayList<>();
        boolean edit = false;

        String dobFormat = dateFormat.format(D_O_B);

        // Read all lines and modify the target line
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/Database/staffs.txt"))) {
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                //update if successful to search the staffname and exist in txt file
                if (data[0].equals(staffname)) {
                    data[1] = password;
                    data[2] = phone;
                    data[3] = email;
                    data[4] = dobFormat;
                    data[5] = gender;
                    data[6] = role;

                    edit = true;
                }

                //adding all file datas into ArrayList
                //spliting data with comma
                staffnewData.add(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        //Ensure all lines are written back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/staffs.txt"))) {
            //staff String as a single line in the txt file while executing for loop
            for (String staff : staffnewData) {
                writer.write(staff);
                // Ensure each record is on a new line
                writer.newLine();  
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return edit;
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
    
}

