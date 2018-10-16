package ast;

import libs.EventObject;
import libs.SingleSchedule;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SCHEDULE extends STATEMENT {
    EventObject eventObj = new EventObject();
    Calendar scheduleDay = Calendar.getInstance();
    String reoccuring;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("SCHEDULE");
        String next = tokenizer.getNext();
        if (next.equals("daily")) {
            reoccuring = "daily";
            next = tokenizer.getNext();
            eventObj.setTitle(next);
        } else if (next.equals("weekly")) {
            reoccuring = "weekly";
            next = tokenizer.getNext();
            eventObj.setTitle(next);
        } else if (next.equals("monthly")) {
            reoccuring = "monthly";
            next = tokenizer.getNext();
            eventObj.setTitle(next);
        } else if (next.equals("annually")) {
            reoccuring = "annually";
            next = tokenizer.getNext();
            eventObj.setTitle(next);
        } else { //non-recurring event
            reoccuring = "none";
            eventObj.setTitle(next);
        }

        next = tokenizer.getNext();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/yyyy");
        try {
            scheduleDay.setTime(sdf.parse(next + "/" + SingleSchedule.getInstance().getCurrentWorkingYear()));
            System.out.println(scheduleDay.getTime().toString());
        } catch (ParseException e) {
            System.out.println("PARSE::SCHEDULE - unable to parse MM/DD");
        }

        next = tokenizer.getNext();
        try {
            //TODO: check regex for correct time format (00:00-11:11), else throw exception
            String[] splitArray = next.split("[-\\s]");
            sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            eventObj.setStart(new Time(sdf.parse(splitArray[0]).getTime()));
            eventObj.setEnd(new Time(sdf.parse(splitArray[1]).getTime()));
        } catch (ParseException e) {
            System.out.println("PARSE::SCHEDULE - unable to parse HH:mm");
        } catch (Exception e) {
            System.out.println("PARSE::SCHEDULE - must have both start and end time");
        }
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {
        if (reoccuring.equals("none")) {
            SingleSchedule.getInstance().insertEventObject(scheduleDay, eventObj);
        } else if (reoccuring.equals("daily")) {
            Calendar insertedScheduleDay = Calendar.getInstance();
            insertedScheduleDay.setTime(scheduleDay.getTime());
            for (int i = 0; i <= 365; i++) {
                insertedScheduleDay.add(Calendar.DATE, i);
                SingleSchedule.getInstance().insertEventObject(insertedScheduleDay, eventObj);
            }
        } else if (reoccuring.equals("weekly")) {
            Calendar insertedScheduleDay = Calendar.getInstance();
            insertedScheduleDay.setTime(scheduleDay.getTime());
            for (int i = 0; i <= 52; i++) {
                insertedScheduleDay.add(Calendar.DAY_OF_WEEK, i);
                SingleSchedule.getInstance().insertEventObject(insertedScheduleDay, eventObj);
            }
        } else if (reoccuring.equals("monthly")) {
            Calendar insertedScheduleDay = Calendar.getInstance();
            insertedScheduleDay.setTime(scheduleDay.getTime());
            for (int i = 0; i <= 12; i++) {
                insertedScheduleDay.add(Calendar.MONTH, i);
                SingleSchedule.getInstance().insertEventObject(insertedScheduleDay, eventObj);
            }
        } else if (reoccuring.equals("annually")) {
            Calendar insertedScheduleDay = Calendar.getInstance();
            insertedScheduleDay.setTime(scheduleDay.getTime());
            for (int i = 0; i <= 1; i++) {
                insertedScheduleDay.add(Calendar.YEAR, i);
                SingleSchedule.getInstance().insertEventObject(insertedScheduleDay, eventObj);
            }
        } else {
            System.out.println("Cannot recognize reoccurence");
        }
        System.out.println("Inserted: " + scheduleDay.toString() + " " + eventObj.getTitle() + " " +
                eventObj.getStart().toString() + " " + eventObj.getEnd());
        return null;
    }
}
