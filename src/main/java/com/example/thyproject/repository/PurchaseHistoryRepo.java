package com.example.thyproject.repository;
import com.example.thyproject.entity.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseHistoryRepo extends JpaRepository<PurchaseHistory, Long> {
}
