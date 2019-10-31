package iotchain.core.api;

import com.alibaba.fastjson.JSON;
import iotchain.core.IoTChainTest;
import iotchain.core.model.Block;
import iotchain.core.model.BlockBody;
import iotchain.core.model.BlockHeader;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BlockApiTest extends IoTChainTest {
    @Test
    public void testGetBestBlockNumber() throws IOException {
        BigInteger bestBlockNumber = ioTChain.blockApi.getBestBlockNumber();
        System.out.println(bestBlockNumber);
        assertTrue(bestBlockNumber.compareTo(BigInteger.ZERO)>0);
    }

    @Test
    public void testGetBlockHeaderByNumber() throws IOException {
        BlockHeader header = ioTChain.blockApi.getBlockHeaderByNumber(BigInteger.ZERO);
        System.out.println(JSON.toJSONString(header, true));
        assertTrue(header.getNumber().equals(BigInteger.ZERO));
    }

    @Test
    public void testGetBlockHeaderByHash() throws IOException {
        BlockHeader header = ioTChain.blockApi.getBlockHeaderByHash("0x21f66efd02bfb2ec4fccc464baaeaf06efb819792739bb89d2b0afabb22b6273");
        System.out.println(JSON.toJSONString(header, true));
        String sha3 = header.hash();
        assertThat(sha3, is("0x21f66efd02bfb2ec4fccc464baaeaf06efb819792739bb89d2b0afabb22b6273"));
    }

    @Test
    public void testGetBlockHeadersByNumber() throws IOException {
        List<BlockHeader> headers = ioTChain.blockApi.getBlockHeadersByNumber(BigInteger.ZERO, 10);
        List<BigInteger> blockNums = headers.stream().map(h -> h.getNumber()).collect(Collectors.toList());
        List<BigInteger> wanted = IntStream.range(0, 10).mapToObj(i -> BigInteger.valueOf(i)).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(headers, true));
        assertThat(blockNums, is(wanted));
    }

    @Test
    public void testGetBlockBodyByHash() throws IOException {
        BlockBody body = ioTChain.blockApi.getBlockBodyByHash("0x9649b99266816ae91a6072aa2aa18a6e99df43924ad3e607ef45a4c15e44e96f");
        System.out.println(JSON.toJSONString(body, true));
        assertTrue(body.getTransactionList().size()==1);
    }

    @Test
    public void testGetBlockbodies() throws IOException {
        List<BlockHeader> headers = ioTChain.blockApi.getBlockHeadersByNumber(BigInteger.valueOf(1035), 3);
        List<String> hashes = headers.stream().map(h -> h.hash()).collect(Collectors.toList());
        System.out.println(hashes);
        List<BlockBody> blockBodies = ioTChain.blockApi.getBlockBodies(hashes);
        System.out.println(JSON.toJSONString(blockBodies, true));
        assertTrue(blockBodies.size()==3);
    }

    @Test
    public void testGetBlockByNumber() throws IOException {
        Block block = ioTChain.blockApi.getBlockByNumber(BigInteger.ZERO);
        System.out.println(JSON.toJSONString(block, true));
        assertTrue(block.getHeader().getNumber().equals(BigInteger.ZERO));
    }

    @Test
    public void testGetBlockByHash() throws IOException {
        Block block = ioTChain.blockApi.getBlockByHash("0x21f66efd02bfb2ec4fccc464baaeaf06efb819792739bb89d2b0afabb22b6273");
        System.out.println(JSON.toJSONString(block, true));
        assertThat(block.hash(), is("0x21f66efd02bfb2ec4fccc464baaeaf06efb819792739bb89d2b0afabb22b6273"));
    }
}
