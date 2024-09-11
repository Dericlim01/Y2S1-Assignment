package src.shared;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;

//to handle the conversion both from an Object to a String, and back from a String to an Object
public class DateFormat extends JFormattedTextField.AbstractFormatter {
    //create custom date format
    private String dateform = "dd-MM-yyyy";
    //import library Simple date format
    private SimpleDateFormat dateFormat = new SimpleDateFormat(dateform);
    @Override
    public Object stringToValue(String text) throws ParseException {
        //convert String into Date format
        //return by Date type
        return dateFormat.parse(text);
    }
    @Override
    public String valueToString(Object value) throws ParseException {
        if(value != null){    
            //casting the value to Date following the Calendar        
            Calendar cal = (Calendar) value;
            return dateFormat.format(cal.getTime());
        }
        //return empty when the value is null
        return "";
    }
}
