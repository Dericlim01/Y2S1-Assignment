import java.io.*;
import java.util.Date;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class Staff_Management {
    private String line;
    private String role;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        public Staff_Management(String r){
            role = r;
        }

        //Check the unique staffname
        Create_file file = new Create_file();
        public Boolean check_staff (String name){
        if(file.staffs_file()){
            try (BufferedReader read_staff = new BufferedReader(new FileReader("staffs.txt"))){
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
                FileWriter staffs = new FileWriter("staffs.txt",true);
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

}
