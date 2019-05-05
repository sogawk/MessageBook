package bean;

public class Message {
    int id;
    String time;
    String recentMobi;
    String mess;
    int userId;
    String dir;

    public Message() {
    }

    public Message(int id, String time, String recentMobi, String mess, int userId, String dir) {
        this.id = id;
        this.time = time;
        this.recentMobi = recentMobi;
        this.mess = mess;
        this.userId = userId;
        this.dir = dir;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getRecentMobi() {
        return recentMobi;
    }

    public void setRecentMobi(String recentMobi) {
        this.recentMobi = recentMobi;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
