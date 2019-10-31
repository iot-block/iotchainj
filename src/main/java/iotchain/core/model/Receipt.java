package iotchain.core.model;

import java.math.BigInteger;
import java.util.List;

public class Receipt {
    private String postTransactionStateHash;
    private BigInteger cumulativeGasUsed;
    private String logsBloomFilter;
    private List<TxLogEntry> logs;
    private String txHash;
    private String contractAddress;
    private BigInteger gasUsed;
    private Boolean status;
    private BigInteger blockNumber;

    public String getPostTransactionStateHash() {
        return postTransactionStateHash;
    }

    public void setPostTransactionStateHash(String postTransactionStateHash) {
        this.postTransactionStateHash = postTransactionStateHash;
    }

    public BigInteger getCumulativeGasUsed() {
        return cumulativeGasUsed;
    }

    public void setCumulativeGasUsed(BigInteger cumulativeGasUsed) {
        this.cumulativeGasUsed = cumulativeGasUsed;
    }

    public String getLogsBloomFilter() {
        return logsBloomFilter;
    }

    public void setLogsBloomFilter(String logsBloomFilter) {
        this.logsBloomFilter = logsBloomFilter;
    }

    public List<TxLogEntry> getLogs() {
        return logs;
    }

    public void setLogs(List<TxLogEntry> logs) {
        this.logs = logs;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigInteger gasUsed) {
        this.gasUsed = gasUsed;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigInteger getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(BigInteger blockNumber) {
        this.blockNumber = blockNumber;
    }
}
