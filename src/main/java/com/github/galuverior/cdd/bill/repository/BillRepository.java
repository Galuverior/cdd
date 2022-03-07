package com.github.galuverior.cdd.bill.repository;

import com.github.galuverior.cdd.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("SELECT e FROM Bill e WHERE e.cliente.id = :clientId")
    Optional<Bill> getBillByClient(@Param("clientId") Long clientId);
}
