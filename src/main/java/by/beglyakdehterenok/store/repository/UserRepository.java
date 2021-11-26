package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
