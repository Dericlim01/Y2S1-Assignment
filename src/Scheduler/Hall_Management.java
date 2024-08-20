package src.Scheduler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import src.Create_file;

/**
 * Hall_Management
 */
public class Hall_Management {

    public static String name;
    
    public Hall_Management(String n) {
        name = n;
    }

    String line;
    Integer newIDNum;
    ArrayList<Integer> findBiggest = new ArrayList<Integer>();
    public Boolean Add_Hall(String HallType,Integer Capacity,Double PricePerHour) {
        if (new Create_file().hall_file()) { 
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {        
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    String[] Hall_ID = data[0].split("_");
                    int IDNumber =  Integer.parseInt(Hall_ID[1]);
                    findBiggest.add(IDNumber);  
                }  
                if (findBiggest.isEmpty()) {
                    newIDNum = 1;  // No valid lines in file, start from 1
                } else{
                newIDNum = Collections.max(findBiggest) + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                //HallID = ;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/halls.txt",true))) {
                writer.append(String.format("H_%d,%s,%d,%f\n", newIDNum, HallType, Capacity, PricePerHour));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  
        return false;    
    }
}
