package bank;

public class PersonInfo {
    private long personId;
    private String name;
    private String lastname;
    private String accNr;
    private String accType;
    private double balance;

    public PersonInfo(long personId, String name, String lastname, String accNr, String accType, double balance) {
        this.personId = personId;
        this.name = name;
        this.lastname = lastname;
        this.accNr = accNr;
        this.accType = accType;
        this.balance = balance;
    }


    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccNr() {
        return accNr;
    }

    public void setAccNr(String accNr) {
        this.accNr = accNr;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "\nInformacija:" +
                "\nAsmens kodas: " + personId +
                "\nVardas: " + name +
                "\nPavardė" + lastname +
                "\nSąskaitos numeris: " + accNr +
                "\nSąskaitos tipas" + accType +
                "\nBalansas" + balance;
    }
}
