package com.example.thyproject.dto;

import com.example.thyproject.entity.ApiData;
import org.springframework.stereotype.Component;
import com.example.thyproject.entity.PurchaseHistory;
import com.example.thyproject.entity.Stock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Converter {

    public static PurchaseHistoryDTO PHConvertToDto(PurchaseHistory history) {
        PurchaseHistoryDTO dto = new PurchaseHistoryDTO();
        dto.setId(history.getId());
        dto.setQuantity(history.getQuantity());
        dto.setPurchaseDate(history.getPurchaseDate());
        dto.setPurchasePrice(history.getPurchasePrice());
        dto.setTotalPrice(history.getTotalPrice());
        dto.setType(history.getType());
        dto.setStockName(history.getStockName());

        return dto;
    }

    public static StockDTO SConvertToDto(Stock stock) {

        StockDTO dto = new StockDTO();

        dto.setId(stock.getId());
        dto.setSymbol(stock.getSymbol());
        dto.setName(stock.getName());
        dto.setExchange(stock.getExchange());
        dto.setType(stock.getType());
        dto.setPrice(stock.getPrice());
        dto.setQuantity(stock.getQuantity());

        return dto;
    }

    public static Stock SDTOConvertToS(StockDTO stock){
        Stock newStock = new Stock();
        newStock.setSymbol(stock.getSymbol());
        newStock.setName(stock.getName());
        newStock.setExchange(stock.getExchange());
        newStock.setQuantity(stock.getQuantity());
        newStock.setPrice(stock.getPrice());
        newStock.setType(stock.getType());

        return newStock;
    }

    public static PurchaseHistory SDTOConvertToPH(StockDTO stock){
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setQuantity(stock.getQuantity());
        purchaseHistory.setPurchaseDate(new Date());
        purchaseHistory.setPurchasePrice(stock.getPrice() / stock.getQuantity());
        purchaseHistory.setTotalPrice(stock.getPrice());
        purchaseHistory.setStockName(stock.getName());
        return purchaseHistory;

    }


    public static ApiDataDTO ApiDataConvertToDTO(ApiData entity) {
        ApiDataDTO dto = new ApiDataDTO();
        dto.setSymbol(entity.getSymbol());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setChangesPercentage(entity.getChangesPercentage());
        dto.setChange(entity.getChange());
        dto.setDayLow(entity.getDayLow());
        dto.setDayHigh(entity.getDayHigh());
        dto.setYearHigh(entity.getYearHigh());
        dto.setYearLow(entity.getYearLow());
        dto.setMarketCap(entity.getMarketCap());
        dto.setPriceAvg50(entity.getPriceAvg50());
        dto.setPriceAvg200(entity.getPriceAvg200());
        dto.setExchange(entity.getExchange());
        dto.setVolume(entity.getVolume());
        dto.setAvgVolume(entity.getAvgVolume());
        dto.setOpen(entity.getOpen());
        dto.setPreviousClose(entity.getPreviousClose());
        dto.setEps(entity.getEps());
        dto.setPe(entity.getPe());
        dto.setEarningsAnnouncement(entity.getEarningsAnnouncement());
        dto.setSharesOutstanding(entity.getSharesOutstanding());
        dto.setTimestamp(entity.getTimestamp());
        return dto;
    }

    public static List<ApiDataDTO> ApiDataDTOtoDTOList(List<ApiData> entities) {
        List<ApiDataDTO> dtos = new ArrayList<>();
        for (ApiData entity : entities) {
            dtos.add(ApiDataConvertToDTO(entity));
        }
        return dtos;
    }

    public static ApiData ApiDataDTOConvertToAD(ApiDataDTO dto) {
        ApiData entity = new ApiData();
        entity.setSymbol(dto.getSymbol());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setChangesPercentage(dto.getChangesPercentage());
        entity.setChange(dto.getChange());
        entity.setDayLow(dto.getDayLow());
        entity.setDayHigh(dto.getDayHigh());
        entity.setYearHigh(dto.getYearHigh());
        entity.setYearLow(dto.getYearLow());
        entity.setMarketCap(dto.getMarketCap());
        entity.setPriceAvg50(dto.getPriceAvg50());
        entity.setPriceAvg200(dto.getPriceAvg200());
        entity.setExchange(dto.getExchange());
        entity.setVolume(dto.getVolume());
        entity.setAvgVolume(dto.getAvgVolume());
        entity.setOpen(dto.getOpen());
        entity.setPreviousClose(dto.getPreviousClose());
        entity.setEps(dto.getEps());
        entity.setPe(dto.getPe());
        entity.setEarningsAnnouncement(dto.getEarningsAnnouncement());
        entity.setSharesOutstanding(dto.getSharesOutstanding());
        entity.setTimestamp(dto.getTimestamp());
        return entity;
    }

    public static List<ApiData> toEntityList(List<ApiDataDTO> dtos) {
        List<ApiData> entities = new ArrayList<>();
        for (ApiDataDTO dto : dtos) {
            entities.add(ApiDataDTOConvertToAD(dto));
        }
        return entities;
    }
}
