package iotchain.core.crypto;

import org.web3j.utils.Numeric;

public class Hash {
    public static byte[] sha3(byte[] bytes){
        return org.web3j.crypto.Hash.sha3(bytes);
    }
}
