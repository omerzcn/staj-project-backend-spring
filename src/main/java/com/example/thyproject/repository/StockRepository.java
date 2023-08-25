package com.example.thyproject.repository;
import com.example.thyproject.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findBySymbol(String symbol);

    boolean existsBySymbol(String symbol);

}

