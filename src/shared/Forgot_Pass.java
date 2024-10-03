package src.shared;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Forgot_Pass {
    String role;
    String line;

    // Read User File
    private ArrayList<String[]> read_user_file(String name, String password) {
        ArrayList<String[]> user_list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/users.txt"))) {
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(name)) {
                    data[1] = password;
                    role = data[2];
                }
                user_list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user_list;
    }

    private ArrayList<String[]> read_staff_file(String name, String password) {
        ArrayList<String[]> staff_list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/staffs.txt"))) {
            while ((line = read.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(name)) {
                    data[1] = password;
                }
                staff_list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff_list;
    }

    // Write User File
    public Boolean write_user_file(String name, String password) {
        ArrayList<String[]> user = read_user_file(name, password);
        try (PrintWriter user_file = new PrintWriter(new FileWriter("resources/Database/users.txt"))) {
            for (String[] data : user) {
                user_file.println(String.join(",", data));
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
            return false;
        }
        if (!role.equals("customer")) {
            write_staff_file(name, password);
        }
        return true;
    }

    // Write Staff File
    public Boolean write_staff_file(String name, String password) {
        ArrayList<String[]> staff_list = read_staff_file(name, password);
        try (PrintWriter staff_file = new PrintWriter(new FileWriter("resources/Database/staffs.txt"))) {
            for (String[] data : staff_list) {
                staff_file.println(String.join(",", data));
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Write File
    public Boolean write_file(String name, String password) {
        if (write_user_file(name, password)) {
            return true;
        }
        return false;
    }
}
