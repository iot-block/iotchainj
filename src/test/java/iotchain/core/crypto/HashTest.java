package iotchain.core.crypto;

import iotchain.core.model.BlockHeader;
import iotchain.core.model.SignedTransaction;
import org.junit.Test;

import java.math.BigInteger;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class HashTest {
    @Test
    public void testTransactionHash(){
        SignedTransaction tx = new SignedTransaction(
                BigInteger.ONE,
                BigInteger.valueOf(1),
                BigInteger.valueOf(20000),
                "e3b6f948139e86a36d2421eb01b43eec72006e6a",
                BigInteger.valueOf(100000),
                "a9059cbb0000000000000000000000001f68569be9660aecf345fe6f47940f21fee272800000000000000000000000000000000000000000000000008ac7230489e80000",
                BigInteger.valueOf(47),
                new BigInteger("87943571959770171205439940493381448938079608149992805174612893211086812822243"),
                new BigInteger("19063619772539772086135068106034353823731945615802570915163832710298780911179")
        );

        String hash = tx.hash();
        assertThat(hash, is("0x5738006b7d2c8c7e8c1842c52acb7e1ad0796a4bb7699752ced87342cce8ef2a"));
    }

    @Test
    public void testBlockHash(){
        BlockHeader header = new BlockHeader(
                "62e53cf4b0364630c05bb70eedeae7f8c4917e5fd324accd9b171b8867a1f695",
                "fba6ebcb8a45cec3019e1dcd39ad74353b64935a",
                "2dc565eb7fb29bf2f49d25f208fcbdb5bf8006cc90e1e352b09f559c9cdd1e8a",
                "56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
                "56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421",
                "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
                BigInteger.valueOf(11),
                BigInteger.valueOf(545395),
                BigInteger.valueOf(16716680),
                BigInteger.valueOf(0),
                1571216350000L,
                "0xf849c0f843a0cd8cb62270e070cfafac1e7e76f35785fdbae3e4d263a7b8dd008120fa697056a017e9eeeaff40fe6ebf0e4be092f98bbae4f8529376fbae66e88f9436ff51ea0b308200c0"
        );

        String hash = header.hash();
        assertThat(hash, is("0xdabc719004d33d91b1f7ab643d944bdbf589ad5f20ba15504dacbf916839d464"));
    }
}
