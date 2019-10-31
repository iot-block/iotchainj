package iotchain.core.model;

import iotchain.core.model.protocol.Hashable;

public class Block implements Hashable {
    private BlockHeader header;
    private BlockBody body;

    public BlockHeader getHeader() {
        return header;
    }

    public void setHeader(BlockHeader header) {
        this.header = header;
    }

    public BlockBody getBody() {
        return body;
    }

    public void setBody(BlockBody body) {
        this.body = body;
    }

    @Override
    public String hash() {
        return header.hash();
    }
}
