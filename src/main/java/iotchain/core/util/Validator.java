package iotchain.core.util;

import org.web3j.crypto.WalletUtils;

public class Validator {
    /**
     *
     * @param address
     * @param compatible: true for compatible with ethereum address
     * @return
     */
    public static boolean isValidAddress(String address, boolean compatible){
        if (compatible){
            return WalletUtils.isValidAddress(address) || isValidAddress(address, false);
        }

        if (address==null || !address.toLowerCase().startsWith("itc")){
            return false;
        }

        return WalletUtils.isValidAddress(address.substring(3));
    }
}
