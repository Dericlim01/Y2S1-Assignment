package src;

import java.io.*;
import java.util.Date;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.text.SimpleDateFormat;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;


public class Staff_Management {
    private String line;
    private TableRowSorter<DefaultTableModel> sorter;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        public Staff_Management(String n){

        }

        //Check the unique staffname
        Create_file file = new Create_file();
        public Boolean check_staff (String name){
        if(file.staffs_file()){
            try (BufferedReader read_staff = new BufferedReader(new FileReader("resources/staffs.txt"))){
                while ((line = read_staff.readLine()) != null){
                    String[] data = line.split(",");
                    String staffname = data[0];
                    if(staffname.equals(name)){
                        return false;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
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
                FileWriter staffs = new FileWriter("resources/staffs.txt",true);
                staffs.write(stSplitData + "\n");
                staffs.close();
                return true;
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error occured.");
            e.printStackTrace();
        }
        return false;
    }

    //Search staff and view in table
    public JScrollPane view_staff(String[] staffCol){
        ArrayList<String[]> staffData = new ArrayList<>();
            try (BufferedReader read = new BufferedReader(new FileReader("resources/staffs.txt"))){
                while((line = read.readLine()) != null){
                    String[] data = line.split(",");
                    staffData.add(data);
                    }
                }
                catch (Exception e) {
                // TODO: handle exception
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


    public void filter_staff(String option, String value){
        //Two main option to filter
        //Gender option
        if(option.equals("Gender")){
            if(value.equals("male") || value.equals("female")){
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
        else if(option.equals("Role")){
            if(value.equals("scheduler") || value.equals("manager")){
                sorter.setRowFilter(RowFilter.regexFilter(value,6));
            }
            else{
                JOptionPane.showMessageDialog(null, "Failed to filter.","filter status",JOptionPane.PLAIN_MESSAGE);
            }
        }

    }

    public void load_staff (JComboBox<String> staffshow){
        //construct an empty combo box object
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        //set model to JComboBox
        staffshow.setModel(model);
        try (BufferedReader read = new BufferedReader(new FileReader("resources/staffs.txt"))){
            while((line = read.readLine()) != null){
                String[] data = line.split(",");
                String staffname = data[0];
                //adding element at the end of vector with increasing the size by one
                model.addElement(staffname);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}

