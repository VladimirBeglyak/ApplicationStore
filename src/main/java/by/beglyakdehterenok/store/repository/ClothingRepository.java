package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ClothingRepository extends JpaRepository<Clothing, Long>,
        CrudRepository<Clothing, Long> {

    @Query("select c from Clothing c group by c.name")
    List<Clothing> findAllAndGroupByName();


    @Query("select c.size from Clothing c where c.name=:name")
    List<Size> findAllSizesByClothing(@Param("name") String name);


//    List<Clothing> findAllByOrderByPriceAsc();
//    List<Clothing> findAllByOrderByPriceDesc();
//
//    List<Clothing> findAllByCategoriesContainingOrderByPriceAsc(Category category);
//    List<Clothing> findAllByCategoriesContainingOrderByPriceDesc(Category category);
//
//    List<Clothing> findAllByBrand_NameOrderByPriceAsc(String name);
//    List<Clothing> findAllByBrand_NameOrderByPriceDesc(String name);
//
//    List<Clothing> findAllBySizesContainingOrderByPriceAsc(Size size);
//    List<Clothing> findAllBySizesContainingOrderByPriceDesc(Size size);
//
//    List<Clothing> findAllByNameIgnoreCaseContainingOrderByPriceAsc(String st);
//    List<Clothing> findAllByNameIgnoreCaseContainingOrderByPriceDesc(String st);

}
