package libs;
import java.sql.Time;

public class EventObject {
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

//get

    public Time getStart(){
        return start;
    }

    public Time getEnd(){
        return end;
    }

    public String getTitle(){
        return title;
    }

    public String getNote(){
        return note;
    }

// set

    public void setStart(Time s){
        this.start = s;
    }

    public void setEnd(Time e){
        this.end = e;
    }

    public void setTitle(String t){
        this.title = t;
    }

    public void setNote(String n){
        this.note = n;
    }


}
