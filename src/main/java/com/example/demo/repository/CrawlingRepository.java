package com.example.demo.repository;

import com.example.demo.entity.Crawling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrawlingRepository extends JpaRepository<Crawling,Long> {
    Crawling findFirstByOrderByCreatedTimeDesc();

    List<Crawling> findAllByOrderByCreatedTimeAsc();
}
