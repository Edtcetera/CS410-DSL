package libs;
import java.sql.Time;
import java.util.*;


// Singleton implemented : Probably not amazingly. TODOTODO

public class SingleSchedule {
    int currentYear;
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

    public void insertEventObject(Calendar calendar, EventObject eventObject) {
        ArrayList singleDayData = dh.get(calendar);
        singleDayData.add(eventObject);
        dh.put(calendar, singleDayData);
    }

    public void deleteEventObject(Calendar calendar, String titleOfEventObjectToDelete) {
        EventObject edit = new EventObject();
        ArrayList<EventObject> singleDayData = dh.get(calendar);
        for (EventObject t : singleDayData) {
            if (t.getTitle().equals(titleOfEventObjectToDelete)) {
                edit = t;
                singleDayData.remove(t);
            }
        }
        dh.put(calendar, singleDayData);
    }

    public void changeEventObject(Calendar calendar, Time stime, Time etime, String note, String titleOfEventObjecttoDelete) {
        EventObject edit = null;
        ArrayList<EventObject> singleDayData = dh.get(calendar);
        for (EventObject t : singleDayData) {
            if (t.getTitle().equals(titleOfEventObjecttoDelete)) {
                edit = t;
                singleDayData.remove(t);
            }
        }
        if (edit == null) {
            System.out.println("SingleSchedule::ChangeEventObject - Fail to Edit: No Such eventObject.");
            return;
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
