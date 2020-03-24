package bank;

public class Account {
    private String accountNr;
    private String personId;
    private int bankId;
    private String accountType;
    private double balance;

    public Account(String accountNr, String personId, int bankId, String accountType, double balance) {
        this.accountNr = accountNr;
        this.personId = personId;
        this.bankId = bankId;
        this.accountType = accountType;
        this.balance = balance;
    }

    public Account() {

    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "\nSaskaita: " +
                "\nSaskaitos numeris: " + accountNr +
                "\nAsmens kodas: " + personId +
                "\nBanko id: " + bankId +
                "\nKortelės tipas: " + accountType +
                "\nBalansas: " + balance;
    }
}
