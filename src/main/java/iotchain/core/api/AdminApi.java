package iotchain.core.api;

import iotchain.core.model.PeerUri;
import iotchain.core.model.SignedTransaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AdminApi extends Api{
    private static String PATH = "admin";
    public AdminApi(String url) {
        super(url);
    }

    public String peerUri() throws IOException {
        HashMap<String,String> map = new HashMap<>();

        return call(PATH+"/peerUri", map, String.class);
    }

    public void addPeer(String peerUri) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("peerUri", peerUri);

        call(PATH+"/addPeer", map, Void.class);
    }

    public void dropPeer(String peerUri) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("peerUri", peerUri);

        call(PATH+"/dropPeer", map, Void.class);
    }

    public List<PeerUri> incomingPeers() throws IOException {
        HashMap<String,String> map = new HashMap<>();

        return call2(PATH+"/incomingPeers", map, PeerUri.class);
    }

    public List<PeerUri> outgoingPeers() throws IOException {
        HashMap<String,String> map = new HashMap<>();

        return call2(PATH+"/outgoingPeers", map, PeerUri.class);
    }

    public List<SignedTransaction> pendingTransactions() throws IOException {
        HashMap<String,String> map = new HashMap<>();

        return call2(PATH+"/pendingTransactions", map, SignedTransaction.class);
    }
}
