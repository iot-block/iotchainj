package iotchain.core.codec;

import iotchain.core.model.BlockHeader;
import iotchain.core.model.SignedTransaction;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.Type;
import org.web3j.rlp.*;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;


public class Decoder {
    public static SignedTransaction decodeTransaction(String rlpEncoded){
        return decodeTransaction(Numeric.hexStringToByteArray(rlpEncoded));
    }
    public static SignedTransaction decodeTransaction(byte[] rlpEncoded){
        RlpList rlpList = (RlpList) RlpDecoder.decode(rlpEncoded).getValues().get(0);

        BigInteger nonce = ((RlpString)rlpList.getValues().get(0)).asPositiveBigInteger();
        BigInteger gasPrice = ((RlpString)rlpList.getValues().get(1)).asPositiveBigInteger();
        BigInteger gasLimit = ((RlpString)rlpList.getValues().get(2)).asPositiveBigInteger();
        String receivingAddress = ((RlpString)rlpList.getValues().get(3)).asString();
        BigInteger value = ((RlpString)rlpList.getValues().get(4)).asPositiveBigInteger();
        String payload = ((RlpString)rlpList.getValues().get(5)).asString();
        BigInteger v = ((RlpString)rlpList.getValues().get(6)).asPositiveBigInteger();
        BigInteger r = ((RlpString)rlpList.getValues().get(7)).asPositiveBigInteger();
        BigInteger s = ((RlpString)rlpList.getValues().get(8)).asPositiveBigInteger();

        SignedTransaction tx = new SignedTransaction(
                nonce,
                gasPrice,
                gasLimit,
                receivingAddress,
                value,
                payload,
                v,
                r,
                s
        );
        return tx;
    }

    public static BlockHeader decodeBlockHeader(String rlpEncoded){
        return decodeBlockHeader(Numeric.hexStringToByteArray(rlpEncoded));
    }
    public static BlockHeader decodeBlockHeader(byte[] rlpEncoded){
        RlpList rlpList = (RlpList) RlpDecoder.decode(rlpEncoded).getValues().get(0);

        String parentHash = ((RlpString)rlpList.getValues().get(0)).asString();
        String beneficiary = ((RlpString)rlpList.getValues().get(1)).asString();
        String stateRoot = ((RlpString)rlpList.getValues().get(2)).asString();
        String transactionsRoot = ((RlpString)rlpList.getValues().get(3)).asString();
        String receiptsRoot = ((RlpString)rlpList.getValues().get(4)).asString();
        String logsBloom = ((RlpString)rlpList.getValues().get(5)).asString();
        BigInteger difficulty = ((RlpString)rlpList.getValues().get(6)).asPositiveBigInteger();
        BigInteger number = ((RlpString)rlpList.getValues().get(7)).asPositiveBigInteger();
        BigInteger gasLimit = ((RlpString)rlpList.getValues().get(8)).asPositiveBigInteger();
        BigInteger gasUsed = ((RlpString)rlpList.getValues().get(9)).asPositiveBigInteger();
        Long unixTimestamp = ((RlpString)rlpList.getValues().get(10)).asPositiveBigInteger().longValue();
        List<RlpType> rlpExtra = ((RlpList) rlpList.getValues().get(11)).getValues();
        byte[] encodeExtra = RlpEncoder.encode(new RlpList(rlpExtra));
        String extra = Numeric.toHexString(encodeExtra);

        BlockHeader header = new BlockHeader(
                parentHash,
                beneficiary,
                stateRoot,
                transactionsRoot,
                receiptsRoot,
                logsBloom,
                difficulty,
                number,
                gasLimit,
                gasUsed,
                unixTimestamp,
                extra
        );
        return header;
    }

    public static List<Type> decodeFunctionReturn(String rawInput, List<TypeReference<?>> outputParameters){
        return FunctionReturnDecoder.decode(rawInput, Utils.convert(outputParameters));
    }
}
