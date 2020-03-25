package bank;

import java.util.Date;

public class Deposit {
    private String accountNr;
    private Date date;
    private double sum;

    public Deposit(String accountNr, Date date, double sum) {
        this.accountNr = accountNr;
        this.date = date;
        this.sum = sum;
    }

    public Deposit() {

    }

    public String getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(String accountNr) {
        this.accountNr = accountNr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "\nĮnešimas: " +
                "\nĮ sąskaitą: " + accountNr +
                "\nData: " + date +
                "\nSuma: " + sum;
    }
}
