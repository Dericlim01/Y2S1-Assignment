import java.io.*;
import java.util.*;

public class Manager {

    public String[] read_user_Information(String n){
        ArrayList<String[]> users = new ArrayList<String[]>();
        String line;
        String[] data;
        try(BufferedReader read = new BufferedReader(new FileReader("users.txt"));){
            while((line = read.readLine()) != null){
                // Create array and store data 
                data = line.split(",");
                users.add(data);
            }
            // Search in arrayList
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i)[0].equals(n)){
                    return users.get(i);
                }
            }
   
        }catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
