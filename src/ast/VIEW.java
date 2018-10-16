package ast;


import libs.EventObject;
import libs.SingleSchedule;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class VIEW extends STATEMENT {

    Calendar date = null;
    Calendar date_range_start = null;
    Calendar date_range_end = null;
    String time_range_start = "";
    String time_range_end = "";
    boolean view_reoccurring = false;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("VIEW");

        String n1 = tokenizer.getNext();

        if (!n1.equals("NO_MORE_TOKENS")){
            if (n1.contains("current") || n1.contains("this")){
                if (n1.contains("month")) {

                    int month = Calendar.getInstance().get(Calendar.MONTH);
                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    Calendar c_start = Calendar.getInstance();
                    Calendar c_end = Calendar.getInstance();
                    c_start.setTime(getMonthStartDate(year, month));
                    c_end.setTime(getMonthEndDate(year, month));
                    date_range_start = c_start;
                    date_range_end = c_end;

                } else if (n1.contains("year")) {

                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    Calendar c_start = Calendar.getInstance();
                    Calendar c_end = Calendar.getInstance();
                    c_start.setTime(getYearStartDate(year));
                    c_end.setTime(getYearEndDate(year));
                    date_range_start = c_start;
                    date_range_end = c_end;

                } else if (n1.contains("week")){
                    Calendar c_start = Calendar.getInstance();
                    Calendar c_end = Calendar.getInstance();
                    c_start.setTime(getWeekStartDate());
                    c_end.setTime(getWeekEndDate());
                    date_range_start = c_start;
                    date_range_end = c_end;
                } else System.out.println("Could not parse VIEW.");
            }

            else if (n1.equals("today")){
                date = Calendar.getInstance();
            }

            else if (n1.equals("reoccurring")){
                view_reoccurring = true;
            }

            // either single date or date range case is left
            else if (!n1.contains("/")){
                int year = Integer.parseInt(n1);
                Calendar c_start = Calendar.getInstance();
                Calendar c_end = Calendar.getInstance();
                c_start.setTime(getYearStartDate(year));
                c_end.setTime(getYearEndDate(year));
                date_range_start = c_start;
                date_range_end = c_end;

            } else if (!n1.contains("-")){
                date = getDate(n1);
            } else {
                // date range 10/10/2016 - 10/01/2017
                n1.replaceAll( " ", "");
                String[] parts = n1.split("-");
                date_range_start = getDate(parts[0]);
                date_range_end = getDate(parts[1]);
            }
        }

    }

    private static Calendar getDate(String token){
        int slashCount = token.length() - token.replace("/", "").length();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("MM/DD/YYYY", Locale.CANADA);
        try {
            c.setTime(sdf.parse(token + "/" + Integer.toString(SingleSchedule.getInstance().getCurrentWorkingYear())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    private static Date getWeekStartDate(){
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        return calendar.getTime();
    }

    private static Date getWeekEndDate(){
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    private static Date getMonthStartDate(int year, int month){
        LocalDate initial = LocalDate.of(year, month, 5);
        LocalDate start = initial.withDayOfMonth(1);
        Date dateStart = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dateStart;
    }

    private static  Date getMonthEndDate(int year, int month){
        LocalDate initial = LocalDate.of(year, month, 5);
        LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
        Date dateEnd = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dateEnd;
    }

    private static Date getYearStartDate(int year){
        LocalDate initial = LocalDate.of(year, 3, 5);
        LocalDate start = initial.with(firstDayOfYear());
        Date dateStart = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dateStart;
    }

    private static Date getYearEndDate(int year){
        LocalDate initial = LocalDate.of(year, 3, 5);
        LocalDate end = initial.with(lastDayOfYear());
        Date dateEnd = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return dateEnd;
    }

    private static String getDayScheduleStr(ArrayList<EventObject> events){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY", Locale.CANADA);
        StringBuilder result = new StringBuilder();
        for (EventObject event : events) {
            String start = sdf.format(event.getStart());
            String end = sdf.format(event.getEnd());
            String title = event.getTitle();
            String note = event.getNote();
            result.append(start + " - " + end + ": " + title);
            result.append("\n");
            result.append("* " + note);
            result.append("\n");
            result.append("------------");
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String evaluate() throws FileNotFoundException, UnsupportedEncodingException {

        StringBuilder result =new StringBuilder();
        SingleSchedule ss = SingleSchedule.getInstance();

        if (view_reoccurring == true){
            //TODO
        } else if (date != null) {

            SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY", Locale.CANADA);
            String day = sdf.format(date);
            result.append("MY SCHEDULE FOR " + day + ": \n");
            ArrayList<EventObject> events = ss.getDateEvents(date);
            if (events != null){
                result.append(getDayScheduleStr(events));
            } else {
                result.append("--- No events scheduled ---");
            }

        } else if (date_range_start != null && date_range_end != null){

            SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YYYY", Locale.CANADA);
            String range_start = sdf.format(date_range_start);
            String range_end = sdf.format(date_range_end);
            result.append("MY SCHEDULE FROM " + range_start + " TO " + range_end+ ": \n");
            TreeMap<Calendar, ArrayList<EventObject>> events = ss.getRangeEvents(date_range_start, date_range_end);

            if (events != null) {
                for (Map.Entry<Calendar, ArrayList<EventObject>> entry : events.entrySet()) {

                    Calendar key = entry.getKey();
                    ArrayList<EventObject> value = entry.getValue();

                    String day = sdf.format(key);
                    result.append(day + ": \n");
                    result.append("\n * * * ");

                    result.append(getDayScheduleStr(value));

                }
            } else {
                result.append("--- No events scheduled for this time period ---");
            }
        }

        return result.toString();
    }
}
