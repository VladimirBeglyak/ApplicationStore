package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage,Long> {

    @Query("select s.clothing from Storage s where s.clothing.id=:clothingId")
    Clothing findByClothing_Id(@Param("clothingId") Long id);
}
