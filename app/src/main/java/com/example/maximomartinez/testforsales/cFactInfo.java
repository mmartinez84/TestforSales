package com.example.maximomartinez.testforsales;

/**
 * Created by maximo.martinez on 7/19/2016.
 */
public class cFactInfo {
    private  int IdFact;
    private  int Custumer;
    private  double amount;
    private  double open;

    public int getIdFact() {
        return IdFact;
    }

    public void setIdFact(int idFact) {
        IdFact = idFact;
    }

    public int getCustumer() {
        return Custumer;
    }

    public void setCustumer(int custumer) {
        Custumer = custumer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }
}
