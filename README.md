# iotchainj
iotchain sdk for java

### Getting started
Add the relevant dependency to your project:

### Maven
```
<dependency>
  <groupId>io.iotchain</groupId>
  <artifactId>iotchainj</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle
```
compile 'io.iotchain:iotchainj:1.0.0'
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
RawTransaction tx = new RawTransaction(
        account.getNonce(),
        gasPrice,
        BigInteger.valueOf(22000),
        "f6df328deb0df489caad847df5761a6f7e3a082c",
        new BigInteger("12330000000000"),
        ""
);

SignedTransaction stx = Signer.signTx(tx, privateKey, chainId);
String hash = ioTChain.transactionApi.sendTx(stx);
```

Send ITC:
```java
String address = "0x36bf8ff5ac929fa02c62d3366d05fd2f7ef56769";
String privateKey = "43034A1C0FCDFD7389E02FC45A7A83208AC8D66C80D5A877D44641D1D7CAC64C";
Long chainId = 100L;
String itcContractAddress = "0x866f68430344fb1a0b0271c588abae123a8c31dd";

Account account = ioTChain.accountApi.getAccount(address);
BigInteger gasPrice = ioTChain.contractApi.getGasPrice();
String payload = Encoder.encodeFunction(
        "transfer",
        Arrays.asList(new Address("f6df328deb0df489caad847df5761a6f7e3a082c"), new Uint256(new BigInteger("80000000000000000000"))),
        Collections.emptyList()
);

RawTransaction tx = new RawTransaction(
        account.getNonce(),
        gasPrice,
        BigInteger.valueOf(1500000),
        itcContractAddress,
        BigInteger.valueOf(0),
        payload
);

SignedTransaction stx = Signer.signTx(tx, privateKey, chainId);
String hash = ioTChain.transactionApi.sendTx(stx);
```

Get ITC balance:
```java
String itcContractAddress = "0x866f68430344fb1a0b0271c588abae123a8c31dd";
String account = "0xf6df328deb0df489caad847df5761a6f7e3a082c";
CallTx callTx = new CallTx();
callTx.setTo(itcContractAddress);
callTx.setGasPrice(BigInteger.ZERO);
callTx.setValue(BigInteger.ZERO);
String payload = Encoder.encodeFunction(
        "balanceOf",
        Arrays.asList(new Address(account)),
        Collections.emptyList()
);
callTx.setData(payload);
String resp = ioTChain.contractApi.call(callTx);

List<TypeReference<?>> types = Arrays.asList(new TypeReference<Uint256>() {});
List<Type> datas = Decoder.decodeFunctionReturn(resp, types);
Uint256 balance = (Uint256) datas.get(0);
```

For more information refer to [`TestCase`](https://github.com/iot-block/iotchainj/tree/master/src/test/java/iotchain/core) in project.