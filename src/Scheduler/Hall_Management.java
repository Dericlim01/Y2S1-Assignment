package src.Scheduler;

import src.shared.Create_file;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * Hall_Management
 */
public class Hall_Management {
    String line;
    Integer newIDNum;
    ArrayList<Integer> findBiggest = new ArrayList<Integer>();

    public Boolean Add_Hall(String HallType,Integer Capacity,String PricePerHour) {
        if (new Create_file().hall_file()) { 
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {        
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    String[] Hall_ID = data[0].split("_");
                    int IDNumber =  Integer.parseInt(Hall_ID[1]);
                    findBiggest.add(IDNumber);  
                }  
                if (findBiggest.isEmpty()) {
                    // No valid lines in file, start from 1
                    newIDNum = 1;
                } else {
                    newIDNum = Collections.max(findBiggest) + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // HallID = ;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/halls.txt",true))) {
                writer.append(String.format("H_%d,%s,%d,%s\n", newIDNum, HallType, Capacity, PricePerHour));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  
        return false;    
    }

    public Object[][] search_hall_data(Object hall_type) {
         List<Object[]> hallData = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (hall_type != null) {
                        if (data[1].equals(hall_type.toString())) {
                            hallData.add(data);
                        }
                    } else if (hall_type == null) {
                        hallData.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallData.toArray(new Object[0][]);
    }

    public Boolean edit_Hall_Info(String H_ID, String Hall_Type, Integer Capacity, Double Price) {
        List<String> fileContent = new ArrayList<>();
        boolean edit = false;
        // Check if the hall status file exists, and if not, create it
        if (new Create_file().hall_file()) { 
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {        
                 // Read the existing hall status file line by line
                while ((line = read.readLine()) != null) {
                    // Split the line into data fields
                    String[] data = line.split(",");
                    if (data[0].equals(H_ID)){
                        data[1] = Hall_Type;
                        data[2] = Capacity.toString();
                        data[3] = Price.toString();
                        edit = true;
                    } 
                    fileContent.add(String.join(",", data));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/halls.txt"))) {
                for (String lines : fileContent) {
                    writer.write(lines);
                    writer.newLine();
                }
            } catch (IOException g) {
                g.printStackTrace(); 
            }
            return edit;
        }
        return edit;
    }

    public Boolean delete_Hall(String hall_ID) {
        List<String> fileContent = new ArrayList<>();
        boolean delete = false;
        // Check if the hall status file exists, and if not, create it
        if (new Create_file().hall_file()) { 
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {        
                    // Read the existing hall status file line by line
                while ((line = read.readLine()) != null) {
                    // Split the line into data fields
                    String[] data = line.split(",");
                    if (! data[0].equals(hall_ID)) {
                        fileContent.add(String.join(",", data));
                    } else if (data[0].equals(hall_ID)) {
                        delete = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/halls.txt"))) {
                for (String lines : fileContent) {
                    writer.write(lines);
                    writer.newLine();
                }
            } catch (IOException g) {
                g.printStackTrace(); 
            }
            return delete;
        }   
        return delete;
    }    
}
