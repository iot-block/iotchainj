package iotchain.core.crypto;

import com.alibaba.fastjson.JSON;
import iotchain.core.IoTChainTest;
import iotchain.core.model.RawTransaction;
import iotchain.core.model.SignedTransaction;
import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SignerTest extends IoTChainTest {

    @Test
    public void testGetSender(){
        SignedTransaction tx = new SignedTransaction(
                BigInteger.valueOf(304),
                BigInteger.valueOf(1),
                BigInteger.valueOf(1500000),
                "0x2ca70e7d0c396c36e8b9d206d988607a013483cf",
                BigInteger.valueOf(0),
                "a9059cbb000000000000000000000000f5545fbc52b21d213800a0c0e5b2ee4664d9b44c00000000000000000000000000000000000000000000001043561a8829300000",
                BigInteger.valueOf(227),
                new BigInteger("55113393165985094343662658717312129825917605727853257061498114363018940497147"),
                new BigInteger("5245308981052085251246691524488036920001015537702559390004269273852025263745")
        );
        String address = tx.getSender();
        System.out.println(address);
        assertThat(address, is(ADDRESS));
    }

    @Test
    public void testSignTx(){
        RawTransaction tx = new RawTransaction(
                BigInteger.valueOf(304),
                BigInteger.valueOf(1),
                BigInteger.valueOf(1500000),
                "0x2ca70e7d0c396c36e8b9d206d988607a013483cf",
                BigInteger.valueOf(0),
                "a9059cbb000000000000000000000000f5545fbc52b21d213800a0c0e5b2ee4664d9b44c00000000000000000000000000000000000000000000001043561a8829300000"
        );
        SignedTransaction stx = Signer.signTx(tx, PRIVATE_KEY, CHAIN_ID);
        System.out.println(JSON.toJSONString(stx, true));
        assertThat(stx.getV().toString(), is("227"));
        assertThat(stx.getR().toString(), is("55113393165985094343662658717312129825917605727853257061498114363018940497147"));
        assertThat(stx.getS().toString(), is("5245308981052085251246691524488036920001015537702559390004269273852025263745"));

        String address = Signer.getSender(stx);
        assertThat(address, is(ADDRESS));
    }

}
