package me.nobler.spring.ticketbooking.Repository;

import me.nobler.spring.ticketbooking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {
}
