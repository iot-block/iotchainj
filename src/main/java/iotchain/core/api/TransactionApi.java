package iotchain.core.api;

import iotchain.core.codec.Encoder;
import iotchain.core.crypto.Signer;
import iotchain.core.model.*;
import iotchain.core.util.Util;
import iotchain.core.util.Validator;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

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

    public String sendItg(TransactionRequest tx, boolean compatible) throws IOException {
        if (!Validator.isValidAddress(tx.getReceiver(),compatible)){
            throw new IllegalArgumentException("receiver address invalid");
        }
        RawTransaction rawTx = new RawTransaction(
                tx.getNonce(),
                tx.getGasPrice(),
                tx.getGasLimit(),
                Util.extractAddress(tx.getReceiver()),
                tx.getValue(),
                ""
        );

        SignedTransaction stx = Signer.signTx(rawTx, tx.getPrivateKey(), tx.getChainId());
        return sendTx(stx);
    }

    public String sendItc(ItcTransactionRequest tx, boolean compatible) throws IOException {
        if (!Validator.isValidAddress(tx.getReceiver(),compatible)){
            throw new IllegalArgumentException("receiver address invalid");
        }
        String payload = Encoder.encodeFunction(
                "transfer",
                Arrays.asList(new Address(Util.extractAddress(tx.getReceiver())), new Uint256(tx.getValue())),
                Collections.emptyList()
        );
        RawTransaction rawTx = new RawTransaction(
                tx.getNonce(),
                tx.getGasPrice(),
                tx.getGasLimit(),
                tx.getContractAddress(),
                BigInteger.valueOf(0),
                payload
        );

        SignedTransaction stx = Signer.signTx(rawTx, tx.getPrivateKey(), tx.getChainId());
        return sendTx(stx);
    }
}
