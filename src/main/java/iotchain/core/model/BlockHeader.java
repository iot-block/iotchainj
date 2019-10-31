package iotchain.core.model;

import iotchain.core.codec.Encoder;
import iotchain.core.crypto.Hash;
import iotchain.core.model.protocol.Hashable;
import iotchain.core.model.protocol.Rlpable;
import org.web3j.rlp.RlpDecoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockHeader implements Rlpable, Hashable {
    private String parentHash;
    private String beneficiary;
    private String stateRoot;
    private String transactionsRoot;
    private String receiptsRoot;
    private String logsBloom;
    private BigInteger difficulty;
    private BigInteger number;
    private BigInteger gasLimit;
    private BigInteger gasUsed;
    private Long unixTimestamp;
    private String extra;

    public BlockHeader(String parentHash,
                       String beneficiary,
                       String stateRoot,
                       String transactionsRoot,
                       String receiptsRoot,
                       String logsBloom,
                       BigInteger difficulty,
                       BigInteger number,
                       BigInteger gasLimit,
                       BigInteger gasUsed,
                       Long unixTimestamp,
                       String extra){
        this.parentHash = parentHash;
        this.beneficiary = beneficiary;
        this.stateRoot = stateRoot;
        this.transactionsRoot = transactionsRoot;
        this.receiptsRoot = receiptsRoot;
        this.logsBloom = logsBloom;
        this.difficulty = difficulty;
        this.number = number;
        this.gasLimit = gasLimit;
        this.gasUsed = gasUsed;
        this.unixTimestamp = unixTimestamp;
        this.extra = extra;
    }

    public String getParentHash() {
        return parentHash;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public String getStateRoot() {
        return stateRoot;
    }

    public String getTransactionsRoot() {
        return transactionsRoot;
    }

    public String getReceiptsRoot() {
        return receiptsRoot;
    }

    public String getLogsBloom() {
        return logsBloom;
    }

    public BigInteger getDifficulty() {
        return difficulty;
    }

    public BigInteger getNumber() {
        return number;
    }

    public BigInteger getGasLimit() {
        return gasLimit;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public Long getUnixTimestamp() {
        return unixTimestamp;
    }

    public String getExtra() {
        return extra;
    }

    @Override
    public List<RlpType> asRlpValues() {
        List<RlpType> result = new ArrayList();
        result.add(RlpString.create(Numeric.hexStringToByteArray(this.getParentHash())));
        result.add(RlpString.create(Numeric.hexStringToByteArray(this.getBeneficiary())));
        result.add(RlpString.create(Numeric.hexStringToByteArray(this.getStateRoot())));
        result.add(RlpString.create(Numeric.hexStringToByteArray(this.getTransactionsRoot())));
        result.add(RlpString.create(Numeric.hexStringToByteArray(this.getReceiptsRoot())));
        result.add(RlpString.create(Numeric.hexStringToByteArray(this.getLogsBloom())));
        result.add(RlpString.create(this.getDifficulty()));
        result.add(RlpString.create(this.getNumber()));
        result.add(RlpString.create(this.getGasLimit()));
        result.add(RlpString.create(this.getGasUsed()));
        result.add(RlpString.create(this.getUnixTimestamp()));

        byte[] extraBytes = Numeric.hexStringToByteArray(this.getExtra());
        RlpList extraDecoded = RlpDecoder.decode(extraBytes);
        result.addAll(extraDecoded.getValues());

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockHeader that = (BlockHeader) o;
        return Objects.equals(Numeric.cleanHexPrefix(parentHash), Numeric.cleanHexPrefix(that.parentHash)) &&
                Objects.equals(Numeric.cleanHexPrefix(beneficiary), Numeric.cleanHexPrefix(that.beneficiary)) &&
                Objects.equals(Numeric.cleanHexPrefix(stateRoot), Numeric.cleanHexPrefix(that.stateRoot)) &&
                Objects.equals(Numeric.cleanHexPrefix(transactionsRoot), Numeric.cleanHexPrefix(that.transactionsRoot)) &&
                Objects.equals(Numeric.cleanHexPrefix(receiptsRoot), Numeric.cleanHexPrefix(that.receiptsRoot)) &&
                Objects.equals(Numeric.cleanHexPrefix(logsBloom), Numeric.cleanHexPrefix(that.logsBloom)) &&
                Objects.equals(difficulty, that.difficulty) &&
                Objects.equals(number, that.number) &&
                Objects.equals(gasLimit, that.gasLimit) &&
                Objects.equals(gasUsed, that.gasUsed) &&
                Objects.equals(unixTimestamp, that.unixTimestamp) &&
                Objects.equals(Numeric.cleanHexPrefix(extra), Numeric.cleanHexPrefix(that.extra));
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentHash, beneficiary, stateRoot, transactionsRoot, receiptsRoot, logsBloom, difficulty, number, gasLimit, gasUsed, unixTimestamp, extra);
    }

    public String hash(){
        byte[] rlp = Encoder.encode(this);
        return Numeric.toHexString(Hash.sha3(rlp));
    }
}
