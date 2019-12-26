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
        BigInteger balance = ioTChain.contractApi.queryItcBalance(ITC_CONTRACT_ADDRESS, "itcf6df328deb0df489caad847df5761a6f7e3a082c");
        System.out.println(balance);
        assertTrue(balance.compareTo(BigInteger.ZERO)>0);
    }
}
