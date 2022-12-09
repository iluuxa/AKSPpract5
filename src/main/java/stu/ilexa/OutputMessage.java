package stu.ilexa;

public class OutputMessage {
    private int id;
    private String time;
    private String from;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public OutputMessage(int id, String time, String from, String text) {
        this.id=id;
        this.time = time;
        this.from = from;
        this.text = text;
    }
}
