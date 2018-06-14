package me.nobler.spring.bank_server.Repository;
import me.nobler.spring.bank_server.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,String> {
}
