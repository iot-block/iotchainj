package iotchain.core.api;

import iotchain.core.model.Block;
import iotchain.core.model.BlockBody;
import iotchain.core.model.BlockHeader;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockApi extends Api{
    private static String PATH = "block";
    public BlockApi(String url) {
        super(url);
    }

    public BigInteger getBestBlockNumber() throws IOException {
        Map map = new HashMap();

        return call(PATH+"/getBestBlockNumber", map, BigInteger.class);
    }

    public BlockHeader getBlockHeaderByNumber(BigInteger blockNumber) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("number",blockNumber+"");

        return call(PATH+"/getBlockHeaderByNumber", map, BlockHeader.class);
    }

    public List<BlockHeader> getBlockHeadersByNumber(BigInteger start, Integer limit) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("start",start+"");
        map.put("limit",limit+"");

        return call2(PATH+"/getBlockHeadersByNumber", map, BlockHeader.class);
    }

    public BlockHeader getBlockHeaderByHash(String hash) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);

        return call(PATH+"/getBlockHeaderByHash", map, BlockHeader.class);
    }

    public BlockBody getBlockBodyByHash(String hash) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);

        return call(PATH+"/getBlockBodyByHash", map, BlockBody.class);
    }

    public List<BlockBody> getBlockBodies(List<String> hashes) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        map.put("hashes", hashes);

        return call2(PATH+"/getBlockBodies", map, BlockBody.class);
    }

    public Block getBlockByNumber(BigInteger blockNumber) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("number", blockNumber+"");

        return call(PATH+"/getBlockByNumber", map, Block.class);
    }

    public Block getBlockByHash(String hash) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);

        return call(PATH+"/getBlockByHash", map, Block.class);
    }

    public Integer getTransactionCountByHash(String hash) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);

        return call(PATH+"/getTransactionCountByHash", map, Integer.class);
    }

    public BigInteger getTotalDifficultyByNumber(BigInteger number) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("number", number+"");

        return call(PATH+"/getTotalDifficultyByNumber", map, BigInteger.class);
    }

    public BigInteger getTotalDifficultyByHash(String hash) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("hash", hash);

        return call(PATH+"/getTotalDifficultyByHash", map, BigInteger.class);
    }
}
