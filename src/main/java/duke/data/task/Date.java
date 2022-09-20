package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Date implements Attributes {

    private static final String PRINT_FORMAT = "dd MMM yyyy";
    public String dateString;
    public LocalDate date;

    public Date(){
        this.setData("");
    }
    public Date(String data){
        this();
        this.setData(data.trim());
    }

    /** Get Date data */
    @Override
    public String getData(){
        if(this.date != null){
            return this.date.format(DateTimeFormatter.ofPattern(PRINT_FORMAT));
        }
        return this.dateString;
    }

    /** Set Date data */
    @Override
    public void setData(String data){
        try {
            this.date = LocalDate.parse(data);
            this.dateString = data;
        } catch (DateTimeParseException e) {
            this.date = null;
            this.dateString = data;
        }
    }

    /** Check blank or null */
    @Override
    public boolean isValid(){
        return !this.dateString.isBlank();
    }
    public boolean isNull(){
        return this.date == null;
    }
}
