package iotchain.core.codec;

import iotchain.core.model.protocol.Rlpable;
import org.web3j.abi.DefaultFunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpType;

import java.util.List;

public class Encoder {
    public static byte[] encode(Rlpable rlpable){
        List<RlpType> values = rlpable.asRlpValues();
        RlpList rlpList = new RlpList(values);
        return RlpEncoder.encode(rlpList);
    }

    public static String encodeFunction(String funcName, List<Type> inputParams, List<TypeReference<?>> outputParams){
        Function func = new Function(funcName, inputParams, outputParams);
        return DefaultFunctionEncoder.encode(func);
    }
}
