package iotchain.core.crypto;

import iotchain.core.model.RawTransaction;
import iotchain.core.model.SignedTransaction;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.sec.SecP256K1Curve;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;

public class Signer {
    public static final Integer NEGATIVE_POINT_SIGN = 27;
    public static final Integer POSITIVE_POINT_SIGN = 28;
    public static final Integer NEW_NEGATIVE_POINT_SIGN = 27;
    public static final Integer NEW_POSITIVE_POINT_SIGN = 28;
    public static final X9ECParameters CURVE_PARAMS = CustomNamedCurves.getByName("secp256k1");
    static final ECDomainParameters CURVE =
            new ECDomainParameters(
                    CURVE_PARAMS.getCurve(),
                    CURVE_PARAMS.getG(),
                    CURVE_PARAMS.getN(),
                    CURVE_PARAMS.getH());
    static final BigInteger HALF_CURVE_ORDER = CURVE_PARAMS.getN().shiftRight(1);

    public static BigInteger recover(int recId, ECDSASignature sig, byte[] message){
        BigInteger n = CURVE.getN(); // Curve order.
        BigInteger prime = SecP256K1Curve.q;

        if (sig.r.compareTo(prime)<0){
            ECPoint R = constructPoint(sig.r, recId);
            if (R.multiply(n).isInfinity()) {
                BigInteger e = new BigInteger(1,message);
                BigInteger rInv = sig.r.modInverse(n);
                BigInteger eInv = BigInteger.ZERO.subtract(e).mod(n);
                BigInteger eInvrInv = rInv.multiply(eInv).mod(n);
                BigInteger srInv = rInv.multiply(sig.s).mod(n);
                ECPoint q = ECAlgorithms.sumOfTwoMultiplies(CURVE.getG(), eInvrInv, R, srInv);
                byte[] qBytes = q.getEncoded(false);

                return new BigInteger(1, Arrays.copyOfRange(qBytes, 1, qBytes.length));
            }
        }

        return null;
    }

    public static String getSender(SignedTransaction tx){
        return tx.getSender();
    }

    public static SignedTransaction signTx(RawTransaction tx, String privateKey, Long chainId){
        SignedTransaction stx = new SignedTransaction(
                tx.getNonce(),
                tx.getGasPrice(),
                tx.getGasLimit(),
                tx.getReceivingAddress(),
                tx.getValue(),
                tx.getPayload(),
                BigInteger.ZERO,
                BigInteger.ZERO,
                BigInteger.ZERO
        );

        ECKeyPair keyPair = ECKeyPair.create(Numeric.toBigInt(privateKey));
        Sign.SignatureData sd = Sign.signMessage(stx.bytesToSign(chainId),keyPair,false);
        BigInteger recoveryId = getRecoveryId(chainId, Numeric.toBigInt(sd.getV()));
        stx.setV(recoveryId);
        stx.setR(Numeric.toBigInt(sd.getR()));
        stx.setS(Numeric.toBigInt(sd.getS()));
        return stx;
    }

    private static BigInteger getRecoveryId(Long chainId, BigInteger pointSign){
        if (pointSign.compareTo(BigInteger.valueOf(NEGATIVE_POINT_SIGN)) == 0){
            return BigInteger.valueOf(chainId * 2 + NEW_NEGATIVE_POINT_SIGN);
        }else if (pointSign.compareTo(BigInteger.valueOf(POSITIVE_POINT_SIGN)) == 0){
            return BigInteger.valueOf(chainId * 2 + NEW_POSITIVE_POINT_SIGN);
        }else return null;
    }

    private static ECPoint constructPoint(BigInteger xBN, Integer recId) {
        X9IntegerConverter x9 = new X9IntegerConverter();
        byte[] compEnc = x9.integerToBytes(xBN, 1 + x9.getByteLength(CURVE.getCurve()));
        compEnc[0] = (byte) (recId == 28 ? 0x03 : 0x02);
        return CURVE.getCurve().decodePoint(compEnc);
    }
}
