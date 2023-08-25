package com.example.thyproject.entity;

import com.example.thyproject.service.ReportService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseHistoryChangeListener {

    @Autowired
    private ReportService reportService;



    @PostPersist
    public void afterPurchaseHistoryCreate(PurchaseHistory purchaseHistory) {
        reportService.addToReport("PurchaseHistory created with id : " + purchaseHistory.getId());
    }

    @PostUpdate
    public void afterPurchaseHistoryUpdate(PurchaseHistory purchaseHistory) {
        reportService.addToReport("PurchaseHistory updated with id : " + purchaseHistory.getId());
    }

    @PostRemove
    public void afterPurchaseHistoryDelete(PurchaseHistory purchaseHistory) {
        reportService.addToReport("PurchaseHistory deleted with id : " + purchaseHistory.getId());
    }
}
