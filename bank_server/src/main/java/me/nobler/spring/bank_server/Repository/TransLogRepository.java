package me.nobler.spring.bank_server.Repository;

import me.nobler.spring.bank_server.Entity.TransLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TransLogRepository extends JpaRepository<TransLog,Integer> {
}
