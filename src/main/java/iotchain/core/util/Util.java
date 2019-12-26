package iotchain.core.util;

import org.web3j.crypto.*;
import org.web3j.utils.Numeric;
import org.web3j.utils.Strings;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class Util {
    public static final String SYMBOL = "itc";

    public static String prependSymbolPrefix(String input){
        if (!containSymbolPrefix(input)){
            return SYMBOL+input;
        }
        return input;
    }

    /**
     * extract real address from whether ethereum or itc address
     * @param input
     * @return
     */
    public static String extractAddress(String input){
        if (input==null){
            return input;
        }
        return cleanSymbolPrefix(Numeric.cleanHexPrefix(input.toLowerCase()));
    }

    public static boolean containSymbolPrefix(String input){
        return !Strings.isEmpty(input)
                && input.length() > 2
                && input.substring(0,3).equals(SYMBOL);
    }
    public static String cleanSymbolPrefix(String input){
        if (containSymbolPrefix(input)){
            return input.substring(3);
        }else {
            return input;
        }
    }

    public static WalletFile createNewWallet(String password) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        WalletFile wallet = Wallet.createStandard(password, ecKeyPair);

        return wallet;
    }
}
