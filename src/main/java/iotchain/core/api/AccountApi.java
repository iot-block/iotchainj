package iotchain.core.api;

import iotchain.core.model.Account;
import iotchain.core.model.HistoryTransaction;
import iotchain.core.model.SignedTransaction;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class AccountApi extends Api{
    private static String PATH = "account";
    public AccountApi(String url){
        super(url);
    }

    public Account getAccount(String address) throws IOException {
        return getAccount(address, null);
    }
    public Account getAccount(String address, BigInteger tag) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);
        map.put("tag",tag==null?"latest":tag+"");

        return call(PATH + "/getAccount", map, Account.class);
    }

    public String getCode(String address) throws IOException {
        return getCode(address, null);
    }
    public String getCode(String address, BigInteger tag) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);
        map.put("tag",tag==null?"latest":tag+"");

        return call(PATH+"/getCode", map, String.class);
    }

    public BigInteger getBalance(String address) throws IOException {
        return getBalance(address, null);
    }
    public BigInteger getBalance(String address, BigInteger tag) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);
        map.put("tag",tag==null?"latest":tag+"");

        return call(PATH+"/getBalance", map, BigInteger.class);
    }

    public String getStorageAt(String address, BigInteger position) throws IOException {
        return getStorageAt(address, position, null);
    }
    public String getStorageAt(String address, BigInteger position, BigInteger tag) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);
        map.put("position",position+"");
        map.put("tag",tag==null?"latest":tag+"");

        return call(PATH+"/getStorageAt", map, String.class);
    }

    public List<HistoryTransaction> getTransactions(String address, Integer page, Integer size) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);
        map.put("page",page+"");
        map.put("size",size+"");

        return call2(PATH+"/getTransactions", map, HistoryTransaction.class);
    }

    public List<HistoryTransaction> getTransactionsByNumber(String address, BigInteger blockNumber) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);
        map.put("number",blockNumber+"");

        return call2(PATH+"/getTransactionsByNumber", map, HistoryTransaction.class);
    }

    public List<SignedTransaction> getPendingTxs(String address) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);

        return call2(PATH+"/getPendingTxs", map, SignedTransaction.class);
    }

    public BigInteger getEstimatedNonce(String address) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address",address);

        return call(PATH+"/getEstimatedNonce", map, BigInteger.class);
    }
}
