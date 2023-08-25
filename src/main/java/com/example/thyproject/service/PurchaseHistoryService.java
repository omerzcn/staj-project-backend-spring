package com.example.thyproject.service;

import com.example.thyproject.dto.Converter;
import com.example.thyproject.dto.PurchaseHistoryDTO;
import com.example.thyproject.dto.SaleStockDTO;
import com.example.thyproject.dto.StockDTO;
import com.example.thyproject.entity.PurchaseHistory;
import com.example.thyproject.repository.PurchaseHistoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseHistoryService {

    private final PurchaseHistoryRepo purchaseHistoryRepo;

    public PurchaseHistoryService(PurchaseHistoryRepo purchaseHistoryRepo) {
        this.purchaseHistoryRepo = purchaseHistoryRepo;
    }

    public List<PurchaseHistoryDTO> getAllPurchaseHistories() {
        try {
            List<PurchaseHistory> purchaseHistories = purchaseHistoryRepo.findAll();

            // PurchaseHistory nesnelerini PurchaseHistoryDTO nesnelerine dönüştürme işlemi
            return purchaseHistories.stream()
                    .map(Converter::PHConvertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Hata durumunda null yerine boş liste dönebilirsiniz.
            return new ArrayList<>();
        }
    }

    @Transactional
    public String purchaseHistoryRecord(StockDTO stock){

        try {

            PurchaseHistory purchaseHistory = Converter.SDTOConvertToPH(stock);
            purchaseHistory.setType("Buy");
            purchaseHistoryRepo.save(purchaseHistory);
            return "Stock Purchase Recorded Successfully";

        } catch (Exception e) {
            return "Error recording purchase history";
        }
    }

    @Transactional
    public String saleHistoryRecord(SaleStockDTO saleStockDTO){

        try {
            PurchaseHistory purchaseHistory = new PurchaseHistory();
            purchaseHistory.setPurchasePrice(saleStockDTO.getCurrentPrice() / saleStockDTO.getQuantity());
            purchaseHistory.setPurchaseDate(new Date());
            purchaseHistory.setQuantity(saleStockDTO.getQuantity());
            purchaseHistory.setTotalPrice(saleStockDTO.getCurrentPrice());
            purchaseHistory.setStockName(saleStockDTO.getName());
            purchaseHistory.setType("Sale");

            purchaseHistoryRepo.save(purchaseHistory);
            return "Stock Sale Recorded Successfully";
        } catch (Exception e) {
            return "Error recording sale history";
        }
    }


}
