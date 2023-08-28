package com.example.thyproject.service;

import com.example.thyproject.dto.ApiDataDTO;
import com.example.thyproject.entity.ApiData;
import com.example.thyproject.repository.ApiDataRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.thyproject.dto.Converter.ApiDataConvertToDTO;
import static com.example.thyproject.dto.Converter.ApiDataDTOConvertToAD;


@Service
public class ApiService {

    private final String rapidApiHost;
    private final String rapidApiKey;
    private final String apiHost2;
    private final String apiKey2;
    private final WebClient api1WebClient;
    private final WebClient api2WebClient;
    private final WebClient api3WebClient;

    private final ApiDataRepo apiDataRepo;


    public ApiService(@Value("${rapidapi.host}") String rapidApiHost,
                      @Value("${rapidapi.key}") String rapidApiKey,
                      @Value("${api.host2}") String apiHost2,
                      @Value("${api.key2}") String apiKey2,
                      @Qualifier("api1WebClient") WebClient api1WebClient,
                      @Qualifier("api2WebClient") WebClient api2WebClient,
                      @Qualifier("api3WebClient") WebClient api3WebClient, ApiDataRepo apiDataRepo) {

        this.api1WebClient = api1WebClient;
        this.api2WebClient = api2WebClient;
        this.rapidApiHost = rapidApiHost;
        this.rapidApiKey = rapidApiKey;
        this.apiHost2 = apiHost2;
        this.apiKey2 = apiKey2;
        this.api3WebClient = api3WebClient;
        this.apiDataRepo = apiDataRepo;
    }

    public List<ApiDataDTO> getStockDataList() {
        try {
            List<ApiDataDTO> apiDataDTOList = new ArrayList<>();

            for (ApiData apiData: apiDataRepo.findAll()){
                apiDataDTOList.add(ApiDataConvertToDTO(apiData));
            }

            return apiDataDTOList;

        } catch (Exception e) {
            return null;
        }
    }
    
    @Scheduled(cron = "0/10 * * * * *")
    public void updatePriceValue() {
        try {
            List<ApiData> apiDataList = apiDataRepo.findAll();

            if(!apiDataList.isEmpty()){
                for (ApiData apiData : apiDataList) {

                    BigDecimal originalPrice = apiData.getPrice()!= null ? apiData.getPrice() : BigDecimal.ZERO;;

                    double randomChangePercentage = (Math.random() * 0.1) - 0.05;

                    BigDecimal priceChangeAmount = originalPrice.multiply(BigDecimal.valueOf(randomChangePercentage));

                    BigDecimal newPrice = originalPrice.add(priceChangeAmount);

                    apiData.setPrice(newPrice);

                    apiDataRepo.save(apiData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 300000)
    public void getStocks() {
        apiDataRepo.deleteAllInBatch();
        String data;
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

            data =  api2WebClient.get()
                    .uri(apiHost2+"/api/v3/quote/"+symbolsStr+"?apikey="+apiKey2)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            saveApiData(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveApiData(String data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ApiDataDTO> apiDataDTOList = objectMapper.readValue(data, new TypeReference<List<ApiDataDTO>>() {});

            for (ApiDataDTO api: apiDataDTOList){
                ApiData apiData = ApiDataDTOConvertToAD(api);
                apiDataRepo.save(apiData);
            }
        } catch (JsonProcessingException e) {
            // JSON verileri dönüştürme hatası
            e.printStackTrace();
        }
    }


    //yedek api'ler
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