package libs;

import java.sql.Time;
import java.util.*;


// Singleton implemented : Probably not amazingly. TODOTODO

public class SingleSchedule {
    int currentYear; //Update this value when program reads UPDATE year. Keeps track of current year we are working on
    Map<Calendar, ArrayList<EventObject>> dh = new HashMap();
    private static SingleSchedule singleSchedule = null;

    private SingleSchedule() {
    }

    public static SingleSchedule getInstance() {
        if (singleSchedule == null) {
            singleSchedule = new SingleSchedule();
        }
        return singleSchedule;
    }

    public void insertTimeObj(Calendar calendar, EventObject eventObject) {
        ArrayList singleDayData = dh.get(calendar);
        singleDayData.add(eventObject);
        dh.put(calendar, singleDayData);
    }

    public void deleteTimeObj(Calendar calendar, String titleOfTimeObjToDelete) {
        EventObject edit = new EventObject();
        ArrayList<EventObject> singleDayData = dh.get(calendar);
        for (EventObject t : singleDayData) {
            if (t.getTitle().equals(titleOfTimeObjToDelete)) {
                edit = t;
                singleDayData.remove(t);
            }
        }
        dh.put(calendar, singleDayData);
    }

    public void changeTimeObj(Calendar calendar, Time stime, Time etime, String note, String titleOfTimeObjtoDelete) {
        EventObject edit = new EventObject();
        ArrayList<EventObject> singleDayData = dh.get(calendar);
        for (EventObject t : singleDayData) {
            if (t.getTitle().equals(titleOfTimeObjtoDelete)) {
                edit = t;
                singleDayData.remove(t);
            }
        }
        if (stime != null && etime != null) {
            edit.setStart(stime);
            edit.setEnd(etime);
        }
        if (note != null) {
            edit.setNote(note);
        }
        singleDayData.add(edit);
        dh.put(calendar, singleDayData);
    }

    /** Gets the current year based on the last UDPATE year code*/
    public int getCurrentWorkingYear() {
        return currentYear;
    }

    /** Sets the current year ONLY through UPDATE.*/
    public int setCurrentWorkingYear(int currentYear) {
        this.currentYear =  currentYear;
        return this.currentYear;
    }
}