package iotchain.core.api;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

public class PersonalApi extends Api{
    private static String PATH = "personal";
    public PersonalApi(String url) {
        super(url);
    }

    public String importRawKey(String privateKey, String passphrase) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("privateKey", privateKey);
        map.put("passphrase", passphrase);

        return call(PATH+"/importRawKey",map,String.class);
    }

    public String newAccount(String passphrase) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("passphrase", passphrase);

        return call(PATH+"/newAccount",map,String.class);
    }

    public Boolean delAccount(String address) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address", address);

        return call(PATH+"/delAccount", map, Boolean.class);
    }

    public List<String> listAccounts() throws IOException {
        HashMap<String,String> map = new HashMap<>();

        return call2(PATH+"/listAccounts", map, String.class);
    }

    public Boolean changePassphrase(String address,String oldPassphrase, String newPassphrase) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("address", address);
        map.put("oldPassphrase", oldPassphrase);
        map.put("newPassphrase", newPassphrase);

        return call(PATH+"/changePassphrase", map, Boolean.class);
    }

    public String sendTransaction(String from,
                                  String passphrase,
                                  String to,
                                  BigInteger value,
                                  BigInteger gasLimit,
                                  BigInteger gasPrice,
                                  BigInteger nonce,
                                  String data) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("from", from);
        map.put("passphrase", passphrase);
        map.put("to", to);
        map.put("value", value+"");
        map.put("gasLimit", gasLimit+"");
        map.put("gasPrice", gasPrice+"");
        map.put("nonce", nonce+"");
        map.put("data", data);

        return call(PATH+"sendTransaction",map,String.class);
    }
}
