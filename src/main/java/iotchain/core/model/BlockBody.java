package iotchain.core.model;

import java.util.List;

public class BlockBody {
    private List<SignedTransaction> transactionList;

    public List<SignedTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<SignedTransaction> transactionList) {
        this.transactionList = transactionList;
    }
}
