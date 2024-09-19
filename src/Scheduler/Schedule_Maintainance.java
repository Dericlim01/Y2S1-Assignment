package src.Scheduler;

import src.shared.Create_file;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;

public class Schedule_Maintainance {
    String line;
    Integer newIDNum;
    ArrayList<Integer> findBiggest = new ArrayList<Integer>();
    // Set date and time format to store inside the txt file and also convert again to Date 
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public  ArrayList<String> search_hall_type() {
        ArrayList<String> hallType = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (!hallType.contains(data[1])) {
                        hallType.add(data[1]);
                    }
                }
                return hallType;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallType;
    }

    public  ArrayList<String> match_hall_type(String hall_type) {
        ArrayList<String> hallData = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (hall_type.equals(data[1])) {
                        for (String item : data) {
                            hallData.add(item);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallData;
    }

    public ArrayList<String> search_hall_ID() {
        ArrayList<String> hallID = new ArrayList<String>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (!hallID.contains(data[0])) {
                        hallID.add(data[0]);
                    }
                }
                return hallID;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallID;
    }

    public ArrayList<String> match_hall_data(String id) {
        ArrayList<String> hallData = new ArrayList<>();
        if (new Create_file().hall_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/halls.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data[0].equals(id)) {
                        for (String item : data) {
                            hallData.add(item);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallData;
    }

    public Object[][] search_hall_schedule(Object hall_type) {
         List<Object[]> hallData = new ArrayList<>();
        if (new Create_file().hall_stat_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/hall_status.txt"))) {
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    if (hall_type != null){
                        if (data[2].equals(hall_type.toString()) ) {
                                hallData.add(data);
                        }
                    } else if (hall_type == null){
                            hallData.add(data);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return hallData.toArray(new Object[0][]);
    }
    
    public Boolean check_schedule(String schID, String Hall_ID,Date add_sDate , Date add_eDate) {
        if (new Create_file().hall_stat_file()) {
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/hall_status.txt"))) {        
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(",");
                    Date sDate = dateFormat.parse(data[3]);
                    Date eDate = dateFormat.parse(data[4]);
                    System.out.println("SDate"+sDate);
                    System.out.println("added sDate"+add_sDate);
                    System.out.println(add_sDate.equals(sDate));
                    if (Hall_ID.equals(data[1]) && ! schID.equals(data[0]) ) {
                        // Check if the new booking exactly matches existing booking dates
                        if (add_sDate.equals(sDate) || add_eDate.equals(eDate) || add_sDate.equals(eDate) || add_eDate.equals(sDate) ) {
                            return false; // Exact match conflict found
                        }
                        // Check if the new booking overlaps with existing bookings
                        if (add_sDate.before(eDate) && add_eDate.after(sDate)) {
                            return false; // Overlap conflict found
                        }
                    }
                }
                return true; // No conflicts found
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    // Method to add a schedule entry for a hall
    public Boolean Add_Schedule(String Hall_ID,String HallType,Date StartD,Date EndD,String status,String remark ) {
        // Format the start and end dates to strings using dateFormat
        String formattedsDate = dateFormat.format(StartD);
        String formattedeDate = dateFormat.format(EndD);
        // Check if the hall status file exists, and if not, create it
        if (new Create_file().hall_stat_file()) { 
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/hall_status.txt"))) {        
                 // Read the existing hall status file line by line
                while ((line = read.readLine()) != null) {
                    // Split the line into data fields
                    String[] data = line.split(",");
                    // Extract the status ID from the data
                    String[] hallstat_ID = data[0].split("_");
                    int statID =  Integer.parseInt(hallstat_ID[1]);
                    // Add the status ID to the list for comparison
                    findBiggest.add(statID);  
                }  
                // Determine the new ID number
                if (findBiggest.isEmpty()) {
                    newIDNum = 1;  // No valid lines in file, start from 1
                } else {
                    // Otherwise, find the maximum ID and increment it by 1 for the new entry
                    newIDNum = Collections.max(findBiggest) + 1;
                }
            } catch (Exception e) {
                e.printStackTrace();// Print any exceptions that occur during reading
            }
            // Write the new schedule entry to the hall status file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/hall_status.txt",true))) {
                // Append the new schedule entry to the file in the specified format
                writer.append(String.format("HS_%d,%s,%s,%s,%s,%s,%s\n",newIDNum, Hall_ID, HallType, formattedsDate, formattedeDate, status, remark));
                return true;// Return true if the write operation was successful
            } catch (Exception e) {
                e.printStackTrace();// Print any exceptions that occur during writing
            }
        }  
        return false;    // Return false if the operation failed at any step
    }

    public Boolean edit_Schedule(String sch_ID, String H_ID, Date updatedsDate, Date updatedeDate, String Status, String Remark) {
        List<String> fileContent = new ArrayList<>();
        boolean edit = false;
        // Check if the hall status file exists, and if not, create it
        if (new Create_file().hall_stat_file()) { 
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/hall_status.txt"))) {        
                 // Read the existing hall status file line by line
                while ((line = read.readLine()) != null) {
                    // Split the line into data fields
                    String[] data = line.split(",");
                    if (data[0].equals(sch_ID)) {
                        data[1] = H_ID;
                        data[3] = dateFormat.format(updatedsDate);
                        data[4] = dateFormat.format(updatedeDate);
                        data[5] = Status;
                        data[6] = Remark;
                        edit = true;
                    } 
                    fileContent.add(String.join(",", data));
                }} catch (IOException e) {
                    e.printStackTrace();
                }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/hall_status.txt"))) {
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

    public Boolean delete_Schedule(String sch_ID) {
        List<String> fileContent = new ArrayList<>();
        boolean delete = false;
        // Check if the hall status file exists, and if not, create it
        if (new Create_file().hall_stat_file()) { 
            try (BufferedReader read = new BufferedReader(new FileReader("resources/Database/hall_status.txt"))) {        
                // Read the existing hall status file line by line
                while ((line = read.readLine()) != null) {
                    // Split the line into data fields
                    String[] data = line.split(",");
                    if (! data[0].equals(sch_ID)) {
                        fileContent.add(String.join(",", data));
                    } else if (data[0].equals(sch_ID)) {
                        delete = true;
                    }
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/Database/hall_status.txt"))) {
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
