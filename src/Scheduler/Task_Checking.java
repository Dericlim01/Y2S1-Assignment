package src.Scheduler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import src.Create_file;

public class Task_Checking {
    String line;

    public ArrayList<String> search_task_assigned(String staff) {
        ArrayList<String> taskID = new ArrayList<String>();
        if (new Create_file().task_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/task.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[6].equals(staff) && data[8].equals("In progress")) {
                        taskID.add(data[0]);
                    }
                }
                return taskID;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taskID;
    }

    public  ArrayList<String> search_task_data(String TaskID) {
        ArrayList<String> taskData = new ArrayList<>();
        if (new Create_file().task_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/task.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (TaskID.equals(data[0])) {
                        for (String item : data) {
                            taskData.add(item);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return taskData;
    }
    


    public Boolean change_task_done(String task_ID){
        List<String> fileContent = new ArrayList<>();
        boolean change = false;
        // Check if the hall status file exists, and if not, create it
        if (new Create_file().task_file()) { 
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/task.txt"))) {        
                 // Read the existing hall status file line by line
                while ((line = read.readLine()) != null) {
                    // Split the line into data fields
                    String[] data = line.split(",");
                    if (data[0].equals(task_ID)){
                        data[8] = "Done";
                        change = true;
                    } 
                    fileContent.add(String.join(",", data));
                }} catch (IOException e) {
                    e.printStackTrace();
                }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/task.txt"))) {
                for (String lines : fileContent) {
                    writer.write(lines);
                    writer.newLine();
                }
                } catch (IOException g) {
                    g.printStackTrace(); 
            }
        return change;
}
        return change;}
}
