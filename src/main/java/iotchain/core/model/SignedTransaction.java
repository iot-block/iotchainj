package iotchain.core.model;

import iotchain.core.codec.Encoder;
import iotchain.core.crypto.Hash;
import iotchain.core.crypto.Signer;
import iotchain.core.model.protocol.Hashable;
import iotchain.core.model.protocol.Rlpable;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.Keys;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class SignedTransaction extends RawTransaction implements Rlpable, Hashable {
    private BigInteger v;
    private BigInteger r;
    private BigInteger s;

    public SignedTransaction(BigInteger nonce,
                             BigInteger gasPrice,
                             BigInteger gasLimit,
                             String receivingAddress,
                             BigInteger value,
                             String payload,
                             BigInteger v,
                             BigInteger r,
                             BigInteger s){
        super(nonce, gasPrice, gasLimit, receivingAddress, value, payload);
        this.v = v;
        this.r = r;
        this.s = s;
    }

    public SignedTransaction(RawTransaction rawTx,
                             BigInteger v,
                             BigInteger r,
                             BigInteger s){
        this(rawTx.getNonce(),
                rawTx.getGasPrice(),
                rawTx.getGasLimit(),
                rawTx.getReceivingAddress(),
                rawTx.getValue(),
                rawTx.getPayload(),
                v,r,s);
    }

    public BigInteger getV() {
        return v;
    }

    public void setV(BigInteger v) {
        this.v = v;
    }

    public BigInteger getR() {
        return r;
    }

    public void setR(BigInteger r) {
        this.r = r;
    }

    public BigInteger getS() {
        return s;
    }

    public void setS(BigInteger s) {
        this.s = s;
    }

    @Override
    public List<RlpType> asRlpValues() {
        List<RlpType> result = rlpValuesToSign();

        result.add(RlpString.create(this.getV()));
        result.add(RlpString.create(this.getR()));
        result.add(RlpString.create(this.getS()));

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignedTransaction that = (SignedTransaction) o;
        return Objects.equals(nonce, that.nonce) &&
                Objects.equals(gasPrice, that.gasPrice) &&
                Objects.equals(gasLimit, that.gasLimit) &&
                Objects.equals(Numeric.cleanHexPrefix(receivingAddress), Numeric.cleanHexPrefix(that.receivingAddress)) &&
                Objects.equals(value, that.value) &&
                Objects.equals(Numeric.cleanHexPrefix(payload), Numeric.cleanHexPrefix(that.payload)) &&
                Objects.equals(v, that.v) &&
                Objects.equals(r, that.r) &&
                Objects.equals(s, that.s);
    }

    public Long chainId(){
        Long recoveryId = v.longValue();
        if (recoveryId < 27){
            return null;
        }
        if (recoveryId % 2 == 0){
            return (recoveryId - 28) / 2;
        }
        return (recoveryId - 27) / 2;
    }

    public String getSender(){
        Long chainId = chainId();
        byte[] bytesToSign = bytesToSign(chainId);
        ECDSASignature ecdsaSig = new ECDSASignature(this.r,this.s);
        Integer recId = getPointSign();
        BigInteger publicKey = Signer.recover(recId, ecdsaSig, bytesToSign);
        return Keys.getAddress(publicKey);
    }

    private Integer getPointSign() {
        Long chainId = chainId();
        BigInteger recoverId = this.getV();
        Integer diff = recoverId.subtract(BigInteger.valueOf(chainId * 2)).intValue();
        if (diff == 27 || diff==28){
            return diff;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nonce, gasPrice, gasLimit, receivingAddress, value, payload, v, r, s);
    }

    @Override
    public String hash() {
        byte[] rlp = Encoder.encode(this);
        return Numeric.toHexString(Hash.sha3(rlp));
    }
}
