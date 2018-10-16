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

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
        try {
            scheduleDay.setTime(sdf.parse(next + "/" + SingleSchedule.getInstance().getCurrentWorkingYear()));
            scheduleDay.clear(Calendar.MILLISECOND);
        } catch (ParseException e) {
            System.out.println("PARSE::DELETE - unable to parse MM/dd");
        }
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        SingleSchedule.getInstance().deleteEventObject(scheduleDay, titleToDelete);
        if (!SingleSchedule.getInstance().doesCalendarDayHaveEvents(scheduleDay)) {
            SingleSchedule.getInstance().deleteCalendarDay(scheduleDay);
        }
        return null;
    }
}
