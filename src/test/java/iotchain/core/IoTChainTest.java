package iotchain.core;

import org.junit.Before;

public abstract class IoTChainTest {
    protected IoTChain ioTChain;
    protected final Long CHAIN_ID = 100L;
    protected final String ADDRESS = "36bf8ff5ac929fa02c62d3366d05fd2f7ef56769";
    protected final String PRIVATE_KEY = "43034A1C0FCDFD7389E02FC45A7A83208AC8D66C80D5A877D44641D1D7CAC64C";
    protected final String ITC_CONTRACT_ADDRESS = "0x866f68430344fb1a0b0271c588abae123a8c31dd";

    @Before
    public void setup(){
        ioTChain = new IoTChain("http://139.224.255.21:30315");
    }
}
