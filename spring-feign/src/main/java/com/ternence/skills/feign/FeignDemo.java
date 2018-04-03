package com.ternence.skills.feign;

import com.netflix.client.ClientFactory;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.ternence.skills.feign.api.Github;
import com.ternence.skills.feign.bean.Contributor;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.ribbon.LBClient;
import feign.ribbon.LBClientFactory;
import feign.ribbon.RibbonClient;
import feign.slf4j.Slf4jLogger;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * spring整合feign
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/3 17:08
 */
public class FeignDemo {

    public static void main(String[] args) {
        RibbonClient client = RibbonClient.builder().lbClientFactory(clientName -> {
            IClientConfig config = ClientFactory.getNamedConfig(clientName);
            DynamicServerListLoadBalancer balancer = (DynamicServerListLoadBalancer) ClientFactory.getNamedLoadBalancer(clientName);
            balancer.setRule(new RandomRule());
            Server server = new Server("https://api.github.com");
            server.setZone("china");

            Server serverAmerica = new Server("https://api.github.com");
            serverAmerica.setZone("america");

            balancer.addServers(Arrays.asList(server, serverAmerica));
            return LBClient.create(balancer, config);
        }).build();

        Github github = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder())
                .logger(new Slf4jLogger()).logLevel(Logger.Level.HEADERS)
//                .client(client)
                .target(Github.class, "https://api.github.com");
        List<Contributor> contributorList = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributorList) {
            System.out.println(contributor.getLogin() + " (" + contributor.getContributions() + ")");
        }
    }
}
