package iotchain.core.api;

import com.alibaba.fastjson.JSON;
import iotchain.core.IoTChain;
import iotchain.core.IoTChainTest;
import iotchain.core.model.Account;
import iotchain.core.model.HistoryTransaction;
import iotchain.core.model.SignedTransaction;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class AccountApiTest extends IoTChainTest {
    @Test
    public void testGetAccount() throws IOException {
        Account account = ioTChain.accountApi.getAccount("0x33d0466fd53dbf58c034c851a0289ff2cc8514ca");
        System.out.println(JSON.toJSONString(account, true));
        assertNotEquals(null, account);
    }

    @Test
    public void testGetCode() throws IOException {
        String code = ioTChain.accountApi.getCode("0x2ca70e7d0c396c36e8b9d206d988607a013483cf");
        System.out.println(code);
        assertNotEquals(null, code);
    }

    @Test
    public void testGetBalance() throws IOException {
        BigInteger balance = ioTChain.accountApi.getBalance("0x33d0466fd53dbf58c034c851a0289ff2cc8514ca");
        System.out.println(balance);
        assertNotEquals(null, balance);
    }

    @Test
    public void testGetStorageAt() throws IOException {
        String value = ioTChain.accountApi.getStorageAt("0x33d0466fd53dbf58c034c851a0289ff2cc8514ca", BigInteger.ZERO);
        System.out.println(value);
        assertNotEquals(null, value);
    }

    @Test
    public void testGetTransactions() throws IOException {
        List<HistoryTransaction> transactions = ioTChain.accountApi.getTransactions("0x33d0466fd53dbf58c034c851a0289ff2cc8514ca", 1, 10);
        System.out.println(JSON.toJSONString(transactions, true));
        assertFalse(transactions.isEmpty());
        assertTrue(transactions.size()==10);
    }

    @Test
    public void testGetTransactionsByNumber() throws IOException {
        List<HistoryTransaction> transactions = ioTChain.accountApi.getTransactionsByNumber("0x33d0466fd53dbf58c034c851a0289ff2cc8514ca", BigInteger.valueOf(648379));
        System.out.println(JSON.toJSONString(transactions, true));
        assertFalse(transactions.isEmpty());
        assertTrue(transactions.size()==1);
    }

    @Test
    public void testGetPendingTxs() throws IOException {
        List<SignedTransaction> pendingTxs = ioTChain.accountApi.getPendingTxs("0x33d0466fd53dbf58c034c851a0289ff2cc8514ca");
        System.out.println(JSON.toJSONString(pendingTxs, true));
        assertTrue(pendingTxs.isEmpty());
    }

    @Test
    public void testGetEstimatedNonce() throws IOException {
        BigInteger nonce = ioTChain.accountApi.getEstimatedNonce("0x33d0466fd53dbf58c034c851a0289ff2cc8514ca");
        System.out.println(nonce);
        assertTrue(nonce != null);
        assertTrue(nonce.compareTo(BigInteger.ZERO)>0);
    }
}
