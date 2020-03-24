package bank;

import java.util.Date;

public class Transaction {
    private int transactionNr;
    private String accountNrFrom;
    private String accountNrTo;
    private Date date;
    private double sum;

    public Transaction(int transactionNr, String accountNrFrom, String accountNrTo, Date date, double sum) {
        this.transactionNr = transactionNr;
        this.accountNrFrom = accountNrFrom;
        this.accountNrTo = accountNrTo;
        this.date = date;
        this.sum = sum;
    }

    public int getTransactionNr() {
        return transactionNr;
    }

    public void setTransactionNr(int transactionNr) {
        this.transactionNr = transactionNr;
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
        return "\nPavedimas" +
                "\nPavedimo numeris: " + transactionNr +
                "\nIš sąskaitos: " + accountNrFrom +
                "\nĮ sąskaitą: " + accountNrTo +
                "\nData: " + date +
                "\nSuma: " + sum;
    }
}
