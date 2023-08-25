package com.example.thyproject.service;

import com.example.thyproject.dto.Converter;
import com.example.thyproject.dto.SaleStockDTO;
import com.example.thyproject.dto.StockDTO;
import com.example.thyproject.entity.Stock;
import com.example.thyproject.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final PurchaseHistoryService purchaseHistoryService;


    public StockService(StockRepository stockRepository, PurchaseHistoryService purchaseHistoryService) {
        this.stockRepository = stockRepository;
        this.purchaseHistoryService = purchaseHistoryService;
    }


    public String purchaseRecord(StockDTO stock) {
        try {
            String purchaseHistoryServiceReturn;
            if (stockRepository.existsBySymbol(stock.getSymbol())) {

                Stock existingStock = stockRepository.findBySymbol(stock.getSymbol());

                existingStock.setQuantity(existingStock.getQuantity() + stock.getQuantity());

                existingStock.setPrice(existingStock.getPrice()+stock.getPrice());

                stockRepository.save(existingStock);

            } else {
                //stock to stockDto
                stockRepository.save(Converter.SDTOConvertToS(stock));

            }

            purchaseHistoryServiceReturn = purchaseHistoryService.purchaseHistoryRecord(stock);
            return "Stock added successfully  --  " + purchaseHistoryServiceReturn;

        } catch (Exception e) {
            return "Error adding history: " + e.getMessage();
        }
    }


    public List<StockDTO> getStock() {
        try {
            List<Stock> stocks = stockRepository.findAll();

            return stocks.stream()
                    .map(Converter::SConvertToDto)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }


    public String deleteStock(SaleStockDTO saleStockDTO) {
        try {
            String PurchaseHistoryServiceReturn;
            PurchaseHistoryServiceReturn = purchaseHistoryService.saleHistoryRecord(saleStockDTO);
            stockRepository.deleteById(saleStockDTO.getId());
            return "Stock Sale is completed successfully --  "+PurchaseHistoryServiceReturn;
        } catch (Exception e) {
            return "Stock Sale failed.";
        }
    }

}
