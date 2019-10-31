package iotchain.core.model.protocol;

import org.web3j.rlp.RlpType;

import java.util.List;

public interface Rlpable {
    List<RlpType> asRlpValues();
}
