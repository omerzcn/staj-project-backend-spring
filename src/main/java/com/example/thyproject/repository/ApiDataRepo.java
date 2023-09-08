package com.example.thyproject.repository;

import com.example.thyproject.entity.ApiData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ApiDataRepo extends JpaRepository<ApiData, Long> {
    Page<ApiData> findAll(Pageable pageable);


}
