package iotchain.core.model;

import java.math.BigInteger;

public class ItcTransactionRequest extends TransactionRequest{
    private String contractAddress;

    public ItcTransactionRequest(
            Long chainId,
            BigInteger nonce,
            String privateKey,
            String receiver,
            BigInteger value,
            BigInteger gasPrice,
            BigInteger gasLimit,
            String contractAddress
    ) {
        super(chainId, nonce, privateKey, receiver, value, gasPrice, gasLimit);
        this.contractAddress = contractAddress;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
}
