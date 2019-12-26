package iotchain.core.model;

import java.math.BigInteger;

public class TransactionRequest {
    private Long chainId;
    private BigInteger nonce;
    private String privateKey;
    private String receiver;
    private BigInteger value;
    private BigInteger gasPrice;
    private BigInteger gasLimit;

    public TransactionRequest(
            Long chainId,
            BigInteger nonce,
            String privateKey,
            String receiver,
            BigInteger value,
            BigInteger gasPrice,
            BigInteger gasLimit
    ) {
        this.chainId = chainId;
        this.nonce = nonce;
        this.privateKey = privateKey;
        this.receiver = receiver;
        this.value = value;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
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

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public Long getChainId() {
        return chainId;
    }

    public void setChainId(Long chainId) {
        this.chainId = chainId;
    }
}
