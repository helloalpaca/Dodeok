package minseon.dodeok.Teacher;

public class ListItemInfo {
    private String sname; //학생 이름
    private int cnum; //단원 번호
    private int qnumber; //문제 번호
    private String question; //문제
    private String answer; //답변

    public void setSname(String sname){
        this.sname=sname;
    }
    public void setCnum(int cnum){
        this.cnum=cnum;
    }
    public void setQnumber(int qnumber){
        this.qnumber=qnumber;
    }
    public void setQuestion(String question){
        this.question=question;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }

    public String getSname(){
        return sname;
    }

    public int getCnum(){
        return cnum;
    }

    public int getQnumber(){
        return qnumber;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }

}