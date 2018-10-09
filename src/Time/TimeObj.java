package Time;
import java.sql.Time;

public class TimeObj {
    Time start = new Time(0);
    Time end = new Time(0);
    String title = new String();
    String note = new String();


    public void TimeObj(Time start,Time end,String title,String note){
        this.start = start;
        this.end = end;
        this.title = title;
        this.note = note;
    }

    public Time getStart(TimeObj t){
        return t.start;
    }

    public Time getEnd(TimeObj t){
        return t.end;
    }

    public String  getTitle(TimeObj t){
        return t.title;
    }

    public String  getNote(TimeObj t){
        return t.note;
    }


}
