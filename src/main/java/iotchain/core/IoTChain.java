package iotchain.core;

import iotchain.core.api.*;

public class IoTChain {
    public AccountApi accountApi;
    public AdminApi adminApi;
    public BlockApi blockApi;
    public ContractApi contractApi;
    public PersonalApi personalApi;
    public TransactionApi transactionApi;

    public IoTChain(String url) {
        this.accountApi = new AccountApi(url);
        this.adminApi = new AdminApi(url);
        this.blockApi = new BlockApi(url);
        this.contractApi = new ContractApi(url);
        this.personalApi = new PersonalApi(url);
        this.transactionApi = new TransactionApi(url);
    }
}

