package iotchain.core.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.WalletFile;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UtilTest {
    @Test
    public void testExtractAddress(){
        String address1 = Util.extractAddress("0xf6df328deb0df489caad847df5761a6f7e3a082c");
        assertThat(address1, is("f6df328deb0df489caad847df5761a6f7e3a082c"));

        String address2 = Util.extractAddress("ITCf6df328deb0df489caad847df5761a6f7e3a082c");
        assertThat(address2, is("f6df328deb0df489caad847df5761a6f7e3a082c"));

        String address3 = Util.extractAddress("0xITCf6df328deb0df489caad847df5761a6f7e3a082c");
        assertThat(address3, is("f6df328deb0df489caad847df5761a6f7e3a082c"));
    }

    @Test
    public void testCreateNewWallet() throws NoSuchAlgorithmException, CipherException, InvalidAlgorithmParameterException, NoSuchProviderException {
        WalletFile wallet = Util.createNewWallet("1");
        System.out.println(JSON.toJSONString(wallet));
    }
}
