package iotchain.core.model;

import iotchain.core.crypto.Hash;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RawTransaction {
    protected BigInteger nonce;
    protected BigInteger gasPrice;
    protected BigInteger gasLimit;
    protected String receivingAddress;
    protected BigInteger value;
    protected String payload;

    public RawTransaction(){

    }

    public RawTransaction(BigInteger nonce,
                          BigInteger gasPrice,
                          BigInteger gasLimit,
                          String receivingAddress,
                          BigInteger value,
                          String payload){
        this.nonce = nonce;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.receivingAddress = receivingAddress;
        this.value = value;
        this.payload = payload;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public BigInteger getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigInteger gasPrice) {
        this.gasPrice = gasPrice;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(BigInteger gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }

    public void setReceivingAddress(String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    protected List<RlpType> rlpValuesToSign(){
        List<RlpType> result = new ArrayList();
        result.add(RlpString.create(this.getNonce()));
        result.add(RlpString.create(this.getGasPrice()));
        result.add(RlpString.create(this.getGasLimit()));
        String receivingAddress = this.getReceivingAddress();
        if (receivingAddress != null && receivingAddress.length() > 0) {
            result.add(RlpString.create(Numeric.hexStringToByteArray(receivingAddress)));
        } else {
            result.add(RlpString.create(""));
        }
        result.add(RlpString.create(this.getValue()));
        byte[] data = Numeric.hexStringToByteArray(this.getPayload());
        result.add(RlpString.create(data));

        return result;
    }

    public byte[] bytesToSign(Long chainId){
        List<RlpType> result = rlpValuesToSign();
        result.add(RlpString.create(chainId));
        result.add(RlpString.create(0));
        result.add(RlpString.create(0));

        RlpList rlpList = new RlpList(result);
        byte[] encode = RlpEncoder.encode(rlpList);

        return Hash.sha3(encode);
    }
}
