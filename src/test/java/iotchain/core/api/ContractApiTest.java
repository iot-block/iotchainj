package iotchain.core.api;

import iotchain.core.IoTChainTest;
import iotchain.core.codec.Decoder;
import iotchain.core.codec.Encoder;
import iotchain.core.model.CallTx;
import org.junit.Test;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ContractApiTest extends IoTChainTest {
    @Test
    public void testGetGasPrice() throws IOException {
        BigInteger gasPrice = ioTChain.contractApi.getGasPrice();
        System.out.println(gasPrice);
    }

    @Test
    public void testQueryItc() throws IOException {
        CallTx callTx = new CallTx();
        callTx.setTo(ITC_CONTRACT_ADDRESS);
        callTx.setGasPrice(BigInteger.ZERO);
        callTx.setValue(BigInteger.ZERO);
        String payload = Encoder.encodeFunction(
                "balanceOf",
                Arrays.asList(new Address("0xf6df328deb0df489caad847df5761a6f7e3a082c")),
                Collections.emptyList()
        );
        callTx.setData(payload);
        String resp = ioTChain.contractApi.call(callTx);

        List<TypeReference<?>> types = Arrays.asList(new TypeReference<Uint256>() {});
        List<Type> datas = Decoder.decodeFunctionReturn(resp, types);
        Uint256 balance = (Uint256) datas.get(0);
        System.out.println(balance.getValue());
        assertTrue(datas.size()==1);
        assertTrue(balance.getValue().compareTo(BigInteger.ZERO)>0);
    }
}
