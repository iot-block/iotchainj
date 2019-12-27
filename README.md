# iotchainj
iotchain sdk for java

### Getting started
Add the relevant dependency to your project:

### Maven
```
<dependency>
  <groupId>io.iotchain</groupId>
  <artifactId>iotchainj</artifactId>
  <version>1.1.0</version>
</dependency>
```

### Gradle
```
compile 'io.iotchain:iotchainj:1.1.0'
```

### Usage

Firstly, setup IoTChain instance. 
```java
import iotchain.core.IoTChain;

IoTChain ioTChain = new IoTChain("http://139.224.255.21:30315");
```

Get account info:
```java
Account account = ioTChain.accountApi.getAccount("0x33d0466fd53dbf58c034c851a0289ff2cc8514ca");
```

Get best block number:
```java
BigInteger bestBlockNumber = ioTChain.blockApi.getBestBlockNumber();
```

Send ITG:
```java
String address = "0x36bf8ff5ac929fa02c62d3366d05fd2f7ef56769";
String privateKey = "43034A1C0FCDFD7389E02FC45A7A83208AC8D66C80D5A877D44641D1D7CAC64C";
Long chainId = 100L;

Account account = ioTChain.accountApi.getAccount(address);
BigInteger gasPrice = ioTChain.contractApi.getGasPrice();
String hash = ioTChain.transactionApi.sendItg(new TransactionRequest(
                chainId,
                account.getNonce(),
                privateKey,
                "itcf6df328deb0df489caad847df5761a6f7e3a082c",
                new BigInteger("12330000000000"),
                gasPrice,
                BigInteger.valueOf(22000)), false);
```

Send ITC:
```java
String address = "0x36bf8ff5ac929fa02c62d3366d05fd2f7ef56769";
String privateKey = "43034A1C0FCDFD7389E02FC45A7A83208AC8D66C80D5A877D44641D1D7CAC64C";
Long chainId = 100L;
String itcContractAddress = "0x866f68430344fb1a0b0271c588abae123a8c31dd";

Account account = ioTChain.accountApi.getAccount(address);
BigInteger gasPrice = ioTChain.contractApi.getGasPrice();

String hash = ioTChain.transactionApi.sendItc(new ItcTransactionRequest(
                chainId,
                account.getNonce(),
                privateKey,
                "itcf6df328deb0df489caad847df5761a6f7e3a082c",
                new BigInteger("80000000000000000000"),
                gasPrice,
                BigInteger.valueOf(1500000),
                itcContractAddress), false);
```

Get ITC balance:
```java
String itcContractAddress = "0x866f68430344fb1a0b0271c588abae123a8c31dd";
String account = "itcf6df328deb0df489caad847df5761a6f7e3a082c";
BigInteger balance = ioTChain.contractApi.queryItcBalance(itcContractAddress, account);
```

Create new wallet:
```java
WalletFile wallet = Util.createNewWallet("password");
System.out.println(JSON.toJSONString(wallet));
// output:
{
  "address": "f5e998ae319bda42029b19d0cfa10110fca6cfdb",
  "crypto": {
    "cipher": "aes-128-ctr",
    "cipherparams": {
      "iv": "28b7cfc31f44e0de0618ad895864988d"
    },
    "ciphertext": "e0af833de55eb0e404c7fc738fb40a62507787d149a35eea7aaa6ec38774a0d6",
    "kdf": "scrypt",
    "kdfparams": {
      "dklen": 32,
      "n": 262144,
      "p": 1,
      "r": 8,
      "salt": "dd925445717e8913756cee549d95b35c048de59f3be1caf621d85a8795cc3bac"
    },
    "mac": "aafa21a16afee7dfed0268f285cdc3a42924b6d139f76278d75772be49f04d0b"
  },
  "id": "50a940d1-ab9e-4fca-a326-4e5717e0c461",
  "version": 3
}
```

Extract address:
```java
String address1 = Util.extractAddress("itcf6df328deb0df489caad847df5761a6f7e3a082c");
// address1: "f6df328deb0df489caad847df5761a6f7e3a082c"

String address2 = Util.extractAddress("0xitcf6df328deb0df489caad847df5761a6f7e3a082c");
// address2: "f6df328deb0df489caad847df5761a6f7e3a082c"

String address3 = Util.extractAddress("0xf6df328deb0df489caad847df5761a6f7e3a082c");
// address3: "f6df328deb0df489caad847df5761a6f7e3a082c"
```

Prepend symbol prefix:
```java
// right, address1 will be "itcf6df328deb0df489caad847df5761a6f7e3a082c"
String address1 = Util.prependSymbolPrefix("f6df328deb0df489caad847df5761a6f7e3a082c");

// wrong, address2 will be "itc0xf6df328deb0df489caad847df5761a6f7e3a082c"
String address2 = Util.prependSymbolPrefix("0xf6df328deb0df489caad847df5761a6f7e3a082c");
```


For more information refer to [`TestCase`](https://github.com/iot-block/iotchainj/tree/master/src/test/java/iotchain/core) in project.
