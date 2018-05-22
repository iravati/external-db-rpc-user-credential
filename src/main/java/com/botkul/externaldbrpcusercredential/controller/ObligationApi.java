package com.botkul.externaldbrpcusercredential.controller;

import com.botkul.externaldbrpcusercredential.NodeRPCConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String parties() {
        return rpc.getProxy().networkMapSnapshot().stream()
    }
}
