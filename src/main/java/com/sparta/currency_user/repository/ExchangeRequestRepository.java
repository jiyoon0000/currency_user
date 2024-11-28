package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {
    //특정 사용자 id로 환전 요청 조회
    List<ExchangeRequest> findAllByUserId(Long userId);
}
