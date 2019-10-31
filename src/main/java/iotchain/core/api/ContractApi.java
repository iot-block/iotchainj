package iotchain.core.api;

import iotchain.core.model.CallTx;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class ContractApi extends Api{
    private static String PATH = "contract";
    public ContractApi(String url) {
        super(url);
    }

    public String call(CallTx callTx) throws IOException {
        return call(callTx, null);
    }

    public String call(CallTx callTx, BigInteger tag) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("callTx", callTx);
        map.put("tag",tag==null?"latest":tag+"");

        return call(PATH+"/call", map, String.class);
    }

    public BigInteger getEstimatedGas(CallTx callTx, BigInteger tag) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("callTx", callTx);
        map.put("tag",tag==null?"latest":tag+"");

        return call(PATH+"/getEstimatedGas", map, BigInteger.class);
    }

    public BigInteger getGasPrice() throws IOException {
        HashMap<String,Object> map = new HashMap<>();

        BigInteger gasPrice = call(PATH + "/getGasPrice", map, BigInteger.class);
        return gasPrice.max(BigInteger.valueOf(10).pow(12));
    }
}
