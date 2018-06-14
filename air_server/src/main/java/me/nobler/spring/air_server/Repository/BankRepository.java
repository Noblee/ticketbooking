package me.nobler.spring.air_server.Repository;

import me.nobler.spring.air_server.Entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankRepository extends JpaRepository<Bank, String> {
}

