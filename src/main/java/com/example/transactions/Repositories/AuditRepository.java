package com.example.transactions.Repositories;

import com.example.transactions.Entities.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository  extends JpaRepository<AuditLog,Long> {

}
