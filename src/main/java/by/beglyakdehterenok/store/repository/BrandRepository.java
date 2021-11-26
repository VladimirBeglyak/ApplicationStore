package by.beglyakdehterenok.store.repository;



import by.beglyakdehterenok.store.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findAll();

    Brand findByName(String name);

}
