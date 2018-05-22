package com.botkul.externaldbrpcusercredential.controller;

import com.botkul.externaldbrpcusercredential.NodeRPCConnection;
import net.corda.core.node.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/obligation")
public class ObligationApi {
    @Autowired
    NodeRPCConnection rpc;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String whoami() {
        return rpc.getProxy().nodeInfo().getLegalIdentities().stream().findFirst().toString();
    }

    @RequestMapping(value = "/parties", method = RequestMethod.GET)
    public List<String> parties() {
        return rpc.getProxy().networkMapSnapshot().stream()
                .map(it -> it.getLegalIdentities().stream().findFirst().toString()).collect(Collectors.toList());
    }
}
