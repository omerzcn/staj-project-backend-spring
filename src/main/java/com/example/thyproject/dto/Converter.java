package com.example.thyproject.dto;

import org.springframework.stereotype.Component;
import com.example.thyproject.entity.PurchaseHistory;
import com.example.thyproject.entity.Stock;

import java.util.Date;

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
}
