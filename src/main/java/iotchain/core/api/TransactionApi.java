package iotchain.core.api;

import com.alibaba.fastjson.JSON;
import iotchain.core.model.Receipt;
import iotchain.core.model.SignedTransaction;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class TransactionApi extends Api{
    private static String PATH = "transaction";
    public TransactionApi(String url) {
        super(url);
    }

    public SignedTransaction getTx(String hash) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);

        return call(PATH+"/getTx", map, SignedTransaction.class);
    }

    public SignedTransaction getPendingTx(String hash) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);

        return call(PATH+"/getPendingTx", map, SignedTransaction.class);
    }

    public Receipt getReceipt(String hash) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);

        return call(PATH+"/getReceipt", map, Receipt.class);
    }

    public SignedTransaction getTxByBlockHashAndIndex(String hash, Integer index) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);
        map.put("index", index+"");

        return call(PATH+"/getTxByBlockHashAndIndex", map, SignedTransaction.class);
    }

    public SignedTransaction getTxByBlockTagAndIndex(BigInteger tag, Integer index) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("tag",tag==null?"latest":tag+"");
        map.put("index", index+"");

        return call(PATH+"/getTxByBlockTagAndIndex", map, SignedTransaction.class);
    }

    public String sendTx(SignedTransaction transaction) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("stx", transaction);

        return call(PATH+"/sendTx", map, String.class);
    }
}
