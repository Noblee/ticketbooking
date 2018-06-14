package me.nobler.spring.ticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@ServletComponentScan
@EnableDiscoveryClient
@SpringBootApplication
public class TicketbookingApplication {
    public static String[] airProvider = {"American-Airlines","Airchina"};
    public static String[] bankProvider = {"ICBC","BankOfChina"};

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(TicketbookingApplication.class, args);
    }
}
