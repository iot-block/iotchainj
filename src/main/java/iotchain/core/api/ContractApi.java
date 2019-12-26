package iotchain.core.api;

import iotchain.core.codec.Decoder;
import iotchain.core.codec.Encoder;
import iotchain.core.model.CallTx;
import iotchain.core.util.Util;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ContractApi extends Api{
    private static String PATH = "contract";
    public ContractApi(String url) {
        super(url);
    }

    public String call(CallTx callTx) throws IOException {
        return call(callTx, null);
    }

    public String call(CallTx callTx, BigInteger tag) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("callTx", callTx);
        map.put("tag",tag==null?"latest":tag+"");

        return call(PATH+"/call", map, String.class);
    }

    public BigInteger getEstimatedGas(CallTx callTx, BigInteger tag) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("callTx", callTx);
        map.put("tag",tag==null?"latest":tag+"");

        return call(PATH+"/getEstimatedGas", map, BigInteger.class);
    }

    public BigInteger getGasPrice() throws IOException {
        HashMap<String,Object> map = new HashMap<>();

        BigInteger gasPrice = call(PATH + "/getGasPrice", map, BigInteger.class);
        return gasPrice.max(BigInteger.valueOf(10).pow(12));
    }

    public BigInteger queryItcBalance(String contractAddress, String holder) throws IOException {
        CallTx callTx = new CallTx();
        callTx.setTo(contractAddress);
        callTx.setGasPrice(BigInteger.ZERO);
        callTx.setValue(BigInteger.ZERO);
        String payload = Encoder.encodeFunction(
                "balanceOf",
                Arrays.asList(new Address(Util.extractAddress(holder))),
                Collections.emptyList()
        );
        callTx.setData(payload);

        String resp = call(callTx);
        List<TypeReference<?>> types = Arrays.asList(new TypeReference<Uint256>() {});
        List<Type> datas = Decoder.decodeFunctionReturn(resp, types);
        Uint256 balance = (Uint256) datas.get(0);
        return balance.getValue();
    }
}
