package src.Admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import src.Create_file;

public class Admin_Management {
    private String line;
    private TableRowSorter<DefaultTableModel> sorter;

    public Admin_Management (String name){

    }

    //Check the unique adminname
        Create_file file = new Create_file();
        public Boolean check_staff (String name) {
        if(file.staffs_file()){
            try (BufferedReader read_staff = new BufferedReader(new FileReader("resources/Database/users.txt"))){
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

    public String[] search_user(String name) {
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        String[] data;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            while ((line = read.readLine()) != null) {
                data = line.split(",");
                users.add(data);
            }
            // Search in arraylist
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i)[0].equals(name)) {
                    return users.get(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users.get(0);
    }

    public Boolean update_user(String n, String p) {
        // Create a user list
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        String[] data;
        Boolean user_update = false;
        Boolean users_update = false;
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            while ((line = read.readLine()) != null) {
                // Store data into data and add to users array
                data = line.split(",");
                users.add(data);
                System.out.println(users);
            }
            // Loop through the users
            for (int i = 0; i < users.size(); i++) {
                // If username found
                if (users.get(i)[0].equals(n)) {
                    users.get(i)[1] = p;
                    user_update = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (user_update) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/users.txt"))) {
                for (String[] userdata : users) {
                    writer.println(userdata[0] + "," + userdata[1] + "," + userdata[2]);
                }
                users_update = true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("User not found");
            return false;
        }

        if (users_update) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("resources/Database/users.txt"))) {
                for (String[] userdata : users) {
                    writer.println(userdata[0] + "," + userdata[1] + "," + userdata[2]);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("User not found");
            return false;
        }
    }

    public JScrollPane view_admin(String[] adCol){
    ArrayList<String[]> adData = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))){
            while((line = read.readLine()) != null) {
                String[] data = line.split(",");
                if(data[2].equals("admin")){
                    adData.add(data);
                }
                }
            }
            catch (Exception e) {
            e.printStackTrace();
        }
        Object[][] adRow= adData.toArray(new Object[0][]);

        DefaultTableModel admin_table = new DefaultTableModel(adRow,adCol);
        JTable table = new JTable(admin_table);
        sorter = new TableRowSorter<>(admin_table);
        table.setRowSorter(sorter);           
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    public void load_admin (JComboBox<String> adshow) {
        //construct an empty combo box object
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        //set model to JComboBox
        adshow.setModel(model);
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))){
            while((line = read.readLine()) != null){
                String[] data = line.split(",");
                if(data[2].equals("admin")){
                    String adname = data[0];
                    //adding element at the end of vector with increasing the size by one
                    model.addElement(adname);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean edit_admin(String adname, String password, String role){
        ArrayList<String> usersData = new ArrayList<>();
        boolean edit = false;
        // Read all lines and modify the target line
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                //update if successful to search the staffname and exist in txt file
                if (data[0].equals(adname)) {
                    data[1] = password;
                    data[2] = role;

                    edit = true;
                }

                //adding all file datas into ArrayList
                //spliting data with comma
                usersData.add(String.join(",", data));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        //Ensure all lines are written back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/users.txt"))) {
            //staff String as a single line in the txt file while executing for loop
            for (String user : usersData) {
                writer.write(user);
                //Ensure each record is on a new line
                writer.newLine();  
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return edit;
    }

    public Boolean delete_admin(String adname) {
        ArrayList<String> usersData = new ArrayList<>();
        boolean delete = false;
        try(BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            while((line = read.readLine())!= null){
                String[] data = line.split(",");
                if (data[0].equals(adname)){
                    delete = true;
                    //when succeffully searching and matching staff name, continue to delete
                    continue;

                }
                usersData.add(line);
        }          
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if(delete){
            //Ensure all line are written back to the file
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/users.txt"))) {
                for(String user: usersData){
                    writer.write(user);
                    writer.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return delete;
    }

}
