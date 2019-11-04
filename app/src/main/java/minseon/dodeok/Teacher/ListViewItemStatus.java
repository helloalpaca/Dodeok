package minseon.dodeok.Teacher;

public class ListViewItemStatus {
    private String name ;
    private String emotion;
    private int now;

    public void setName(String _name) {
        name = _name ;
    }
    public void setEmotion(String _emotion){ emotion = _emotion; }
    public void setNow(int _now) {now = _now;}

    public String getName() {
        return this.name ;
    }
    public String getEmotion(){
        return this.emotion;
    }
    public int getNow() { return this.now; }
}
