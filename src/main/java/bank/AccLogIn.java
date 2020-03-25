package bank;

public class AccLogIn {
    private String username;
    private String password;
    private long personId;

    public AccLogIn(String username, String password, long personId) {
        this.username = username;
        this.password = password;
        this.personId = personId;
    }

    public AccLogIn() {

    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
