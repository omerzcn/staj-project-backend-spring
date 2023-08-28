package com.example.thyproject.repository;

import com.example.thyproject.entity.ApiData;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ApiDataRepo extends JpaRepository<ApiData, Long> {

}
