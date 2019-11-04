package minseon.dodeok.Student;

public class ListItemFeedback {
    private String emotion;
    private String nick;
    private String student_id;
    private String feed_fighting;
    private String feed_good;
    private String feed_metoo;
    private String feed_curious;
    private String feed_special;

    public void setEmotion(String emotion){
        this.emotion=emotion;
    }
    public void setNick(String nick) { this.nick = nick; }
    public void setStudent_id(String student_id){
        this.student_id=student_id;
    }
    public void setFeed_fighting(String feed_fighting){
        this.feed_fighting=feed_fighting;
    }
    public void setFeed_good(String feed_good){
        this.feed_good=feed_good;
    }
    public void setFeed_metoo(String feed_metoo) { this.feed_metoo = feed_metoo; }
    public void setFeed_curious(String feed_curious) { this.feed_curious = feed_curious; }
    public void setFeed_special(String feed_special) { this.feed_special = feed_special; }

    public String getEmotion(){
        return emotion;
    }
    public String getNick() { return nick; }
    public String getStudent_id(){
        return student_id;
    }
    public String getFeed_fighting(){
        return feed_fighting;
    }
    public String getFeed_good(){
        return feed_good;
    }
    public String getFeed_metoo(){
        return feed_metoo;
    }
    public String getFeed_curious(){
        return feed_curious;
    }
    public String getFeed_special(){
        return feed_special;
    }

}
