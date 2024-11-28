package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {
    List<ExchangeRequest> findAllByUserId(Long userId);
}
