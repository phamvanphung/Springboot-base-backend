package com.fucota.base.component.transaction.repository;


import com.fucota.base.component.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {
    Optional<Transaction> findByVirtualAccount(String virtualAccount);
}
