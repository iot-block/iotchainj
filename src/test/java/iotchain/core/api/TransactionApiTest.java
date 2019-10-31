package iotchain.core.api;

import com.alibaba.fastjson.JSON;
import iotchain.core.IoTChainTest;
import iotchain.core.codec.Decoder;
import iotchain.core.codec.Encoder;
import iotchain.core.crypto.Signer;
import iotchain.core.model.*;
import org.junit.Test;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TransactionApiTest extends IoTChainTest {

    @Test
    public void testGetTx() throws IOException {
        String hash = "0x0a7c261c1153d0a9180badf84568903d7deb52be105915062e9c67da8cb0cf02";
        SignedTransaction tx = ioTChain.transactionApi.getTx(hash);
        System.out.println(JSON.toJSONString(tx, true));
        assertThat(tx.hash(), is(hash));
    }

    @Test
    public void testGetReceipt() throws IOException {
        String hash = "0x0a7c261c1153d0a9180badf84568903d7deb52be105915062e9c67da8cb0cf02";
        Receipt receipt = ioTChain.transactionApi.getReceipt(hash);
        System.out.println(JSON.toJSONString(receipt, true));
        assertThat(receipt.getTxHash(), is(Numeric.cleanHexPrefix(hash)));
    }

    @Test
    public void testSendItg() throws IOException {
        Account account = ioTChain.accountApi.getAccount(ADDRESS);
        BigInteger gasPrice = ioTChain.contractApi.getGasPrice();
        RawTransaction tx = new RawTransaction(
                account.getNonce(),
                gasPrice,
                BigInteger.valueOf(22000),
                "f6df328deb0df489caad847df5761a6f7e3a082c",
                new BigInteger("12330000000000"),
                ""
        );

        SignedTransaction stx = Signer.signTx(tx, PRIVATE_KEY, CHAIN_ID);
        System.out.println(JSON.toJSONString(stx, true));
        String hash = ioTChain.transactionApi.sendTx(stx);
        System.out.println(hash);
        assertThat(Numeric.prependHexPrefix(hash), is(stx.hash()));
    }

    @Test
    public void testSendItc() throws IOException {
        Account account = ioTChain.accountApi.getAccount(ADDRESS);
        BigInteger gasPrice = ioTChain.contractApi.getGasPrice();
        String payload = Encoder.encodeFunction(
                "transfer",
                Arrays.asList(new Address("f6df328deb0df489caad847df5761a6f7e3a082c"), new Uint256(new BigInteger("80000000000000000000"))),
                Collections.emptyList()
        );

        RawTransaction tx = new RawTransaction(
                account.getNonce(),
                gasPrice,
                BigInteger.valueOf(1500000),
                ITC_CONTRACT_ADDRESS,
                BigInteger.valueOf(0),
                payload
        );

        SignedTransaction stx = Signer.signTx(tx, PRIVATE_KEY, CHAIN_ID);
        System.out.println(JSON.toJSONString(stx, true));
        String hash = ioTChain.transactionApi.sendTx(stx);
        System.out.println(hash);
    }


}
