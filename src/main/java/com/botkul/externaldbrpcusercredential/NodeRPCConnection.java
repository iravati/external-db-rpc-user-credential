package com.botkul.externaldbrpcusercredential;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class NodeRPCConnection {
    private CordaRPCConnection cordaRPCConnection;
    private CordaRPCOps proxy;

    @PostConstruct
    public void initialiseNodeRpcConnection() {
        NetworkHostAndPort hostAndPort = new NetworkHostAndPort("localhost", 10006);
        CordaRPCClient client = new CordaRPCClient(hostAndPort);
        cordaRPCConnection = client.start("user1", "user1@syne");
        proxy = cordaRPCConnection.getProxy();
    }

    @PreDestroy
    public void close() {
        cordaRPCConnection.notifyServerAndClose();
    }

    public CordaRPCOps getProxy() {
        return proxy;
    }
}
