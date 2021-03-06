package bank;


import java.sql.Date;
import java.time.LocalDate;

public class Transaction {
    private String accountNrFrom;
    private String accountNrTo;
    private Date date;
    private double sum;

    public Transaction(String accountNrFrom, String accountNrTo, double sum) {
        this.accountNrFrom = accountNrFrom;
        this.accountNrTo = accountNrTo;
        this.date = Date.valueOf(LocalDate.now());
        this.sum = sum;
    }

    public Transaction() {

    }

    public String getAccountNrFrom() {
        return accountNrFrom;
    }

    public void setAccountNrFrom(String accountNrFrom) {
        this.accountNrFrom = accountNrFrom;
    }

    public String getAccountNrTo() {
        return accountNrTo;
    }

    public void setAccountNrTo(String accountNrTo) {
        this.accountNrTo = accountNrTo;
    }

    public Date getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "\nPavedimas" +
                "\nIš sąskaitos: " + accountNrFrom +
                "\nĮ sąskaitą: " + accountNrTo +
                "\nData: " + date +
                "\nSuma: " + sum;
    }
}
