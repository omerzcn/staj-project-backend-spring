package com.example.thyproject.entity;

import com.example.thyproject.service.ReportService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockChangeListener {


    @Autowired
    private ReportService reportService;

    @PostPersist
    public void afterStockCreate(Stock stock) {
        reportService.addToReport("Stock created with id : " + stock.getId());
    }

    @PostUpdate
    public void afterStockUpdate(Stock stock) {
        reportService.addToReport("Stock updated with id : " + stock.getId());
    }

    @PostRemove
    public void afterStockDelete(Stock stock) {
        reportService.addToReport("Stock deleted with id : " + stock.getId());
    }
}

