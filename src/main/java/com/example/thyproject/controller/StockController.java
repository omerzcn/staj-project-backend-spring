package com.example.thyproject.controller;
import com.example.thyproject.dto.ApiDataDTO;
import com.example.thyproject.dto.PurchaseHistoryDTO;
import com.example.thyproject.dto.SaleStockDTO;
import com.example.thyproject.dto.StockDTO;
import com.example.thyproject.service.ApiService;
import com.example.thyproject.service.PurchaseHistoryService;
import com.example.thyproject.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class StockController {

    public final StockService stockService;
    public final PurchaseHistoryService purchaseHistoryService;
    public final ApiService apiService;

    public StockController(StockService stockService, PurchaseHistoryService purchaseHistoryService, ApiService apiService) {
        this.stockService = stockService;
        this.purchaseHistoryService = purchaseHistoryService;
        this.apiService = apiService;
    }


    @GetMapping(path = "/stocks")
    public ResponseEntity<String> getTrendStocks() {
        String responseData = apiService.getStockData();
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/stocks1")
    public ResponseEntity<Page<ApiDataDTO>> getTrendStocks1(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "volume") String sort,
                                                            @RequestParam(defaultValue = "ASC") String sortDirect) {

        Page<ApiDataDTO> responseData = apiService.getStockDataList(page,size,sort,sortDirect);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(path = "/stocks2")
    public ResponseEntity<String> getStocks2() {
        String responseData = apiService.getRTApi();
        return ResponseEntity.ok(responseData);
    }


    @PostMapping(path = "/postStock")
    public ResponseEntity<String> postStock(@RequestBody StockDTO stock) {
        String result = stockService.purchaseRecord(stock);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<StockDTO>> getOwnedStock() {
        List<StockDTO> stocks = stockService.getStock();
        return ResponseEntity.ok(stocks);
    }


    @GetMapping(path = "getHistory")
    public ResponseEntity<List<PurchaseHistoryDTO>> getAllPurchaseHistories() {
        List<PurchaseHistoryDTO> histories = purchaseHistoryService.getAllPurchaseHistories();
        return ResponseEntity.ok(histories);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteStock")
    public ResponseEntity<String> deleteStock(@RequestBody SaleStockDTO saleStockDTO) {
        try {
            return ResponseEntity.ok(stockService.deleteStock(saleStockDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting Stock with ID " + saleStockDTO.getId());
        }
    }



}
