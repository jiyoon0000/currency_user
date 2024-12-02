package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.ExchangeSummaryDto;
import com.sparta.currency_user.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {

    @Query("SELECT  new com.sparta.currency_user.dto.ExchangeSummaryDto(er.user.id, COUNT(er), SUM(er.beforeExchange))" +
            "FROM ExchangeRequest er" +
            "GROUP BY er.user.id")
    List<ExchangeSummaryDto> findExchangeSummaryByUser();
    //특정 사용자 id로 환전 요청 조회
    List<ExchangeRequest> findAllByUserId(Long userId);
}
