package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ClothingRepository extends JpaRepository<Clothing, Long>,
        CrudRepository<Clothing,Long> {

//    @Query("select c.sizes from Clothing c")
//    public Set<Size> findAllSizes();


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
