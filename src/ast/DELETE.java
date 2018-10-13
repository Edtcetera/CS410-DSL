package ast;

import libs.SingleSchedule;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DELETE extends STATEMENT {
    String titleToDelete;
    Calendar scheduleDay = Calendar.getInstance();

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("DELETE");

        // Get Title to Delete.
        String next = tokenizer.getNext();
        titleToDelete = next;


        // Get Date to Delete said Title.
        next = tokenizer.getNext();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD", Locale.ENGLISH);
        try {
            scheduleDay.setTime(sdf.parse(next));
        } catch (ParseException e) {
            System.out.println("PARSE::DELETE - unable to parse MM/DD");
        }
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        SingleSchedule.getInstance().deleteEventObject(scheduleDay, titleToDelete);
        return null;
    }
}
