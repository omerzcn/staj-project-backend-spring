package com.example.thyproject.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ApiService {

    private final String rapidApiHost;
    private final String rapidApiKey;
    private final String apiHost2;
    private final String apiKey2;
    private final WebClient api1WebClient;
    private final WebClient api2WebClient;
    private final WebClient api3WebClient;

    private volatile String stockDataList; // Verileri saklamak için bir liste


    public ApiService(@Value("${rapidapi.host}") String rapidApiHost,
                      @Value("${rapidapi.key}") String rapidApiKey,
                      @Value("${api.host2}") String apiHost2,
                      @Value("${api.key2}") String apiKey2,
                      @Qualifier("api1WebClient") WebClient api1WebClient,
                      @Qualifier("api2WebClient") WebClient api2WebClient,
                      @Qualifier("api3WebClient") WebClient api3WebClient) {

        this.api1WebClient = api1WebClient;
        this.api2WebClient = api2WebClient;
        this.rapidApiHost = rapidApiHost;
        this.rapidApiKey = rapidApiKey;
        this.apiHost2 = apiHost2;
        this.apiKey2 = apiKey2;
        this.api3WebClient = api3WebClient;
    }

    public String getStockDataList() {
        try {
            return stockDataList;
        } catch (Exception e) {
            return "An error occurred while fetching stock data" + e.getMessage();
        }
    }
    
    @Scheduled(cron = "0/10 * * * * *")
    public void updateStockDataList() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode stockNodes = objectMapper.readTree(stockDataList != null ? stockDataList : "[]"); // Veri yoksa boş liste ile devam et

            ArrayNode newStockNodes = objectMapper.createArrayNode();

            for (JsonNode stockNode : stockNodes) {
                ObjectNode modifiedStockInfoNode = (ObjectNode) stockNode;

                double originalPrice = stockNode.get("price").asDouble();

                double randomChangePercentage = Math.random() * 0.1 - 0.05;

                double newPrice = originalPrice * (1 + randomChangePercentage);

                modifiedStockInfoNode.put("price", newPrice);

                newStockNodes.add(modifiedStockInfoNode);
            }

            stockDataList = objectMapper.writeValueAsString(newStockNodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 300000)
    public void getStocks() {
        try {
            String symbolsJson = api2WebClient.get()
                    .uri("https://financialmodelingprep.com/api/v3/search?query=AA&limit=200&exchange=NASDAQ&apikey=" + apiKey2)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode symbolsNode = objectMapper.readTree(symbolsJson);

            List<String> symbolsList = new ArrayList<>();
            for (JsonNode symbol : symbolsNode) {
                symbolsList.add(symbol.get("symbol").asText());
            }

            String symbolsStr = String.join(",", symbolsList);

            stockDataList=  api2WebClient.get()
                    .uri(apiHost2+"/api/v3/quote/"+symbolsStr+"?apikey="+apiKey2)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStockData() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Host", rapidApiHost);
        headers.add("X-RapidAPI-Key", rapidApiKey);

        try {
            return api1WebClient.get()
                    .uri("https://twelve-data1.p.rapidapi.com/stocks?format=json&country=Turkey")
                    .headers(h -> h.addAll(headers))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // Mono'yu bloke ederek sonucu bekleriz
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while fetching stock data"+e.getMessage();
        }
    }

    public String getRTApi(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Host", "real-time-finance-data.p.rapidapi.com");
        headers.add("X-RapidAPI-Key", "ee4694b46amsh98ecf5e36743db2p1bcf98jsnb8379604eddb");
        try{
            return  api3WebClient.get()
                    .uri("https://real-time-finance-data.p.rapidapi.com/market-trends?trend_type=MOST_ACTIVE")
                    .headers(h -> h.addAll(headers))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (Exception e){
        e.printStackTrace();
        return "An error occurred while fetching stock data"+e.getMessage();
        }
    }
}