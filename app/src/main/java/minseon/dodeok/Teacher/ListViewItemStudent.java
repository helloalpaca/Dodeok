package minseon.dodeok.Teacher;

public class ListViewItemStudent {
    private String name ;
    private String status;
    private String id;
    private String pw;
    private boolean flag;

    public void setName(String _name) {
        name = _name ;
    }
    public void setStatus(String _status){ status = _status; }
    public void setId(String _id) { id = _id; }
    public void setPw(String _pw) { pw = _pw; }
    public void setFlag(boolean _b) {flag = _b;}

    public String getName() {
        return this.name ;
    }
    public String getStatus(){
        return this.status;
    }
    public String getId() { return this.id; }
    public String getPw() { return this.pw; }
    public Boolean getFlag() { return this.flag; }
}
