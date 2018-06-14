package me.nobler.spring.air_server.Repository;


import me.nobler.spring.air_server.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
