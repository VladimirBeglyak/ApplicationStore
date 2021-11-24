package by.beglyakdehterenok.store.repository;

import by.beglyakdehterenok.store.entity.Category;
import by.beglyakdehterenok.store.entity.Clothing;
import by.beglyakdehterenok.store.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ClothingRepository extends JpaRepository<Clothing, Long>,
        CrudRepository<Clothing, Long>, PagingAndSortingRepository<Clothing,Long> {

    @Query("select c from Clothing c group by c.name")
    List<Clothing> findAllAndGroupByName();

    @Query("select c from Clothing c " +
            "group by c.name")
    Page<Clothing> findAllForShopPage(String keyword,Pageable pageable);

    @Query("select c.size from Clothing c where c.name=:name")
    List<Size> findAllSizesByClothing(@Param("name") String name);

    @Query("select c from Clothing c where c.category.name=:name group by c.name")
    List<Clothing> findAllByCategory(@Param("name") String name);

    @Query("select c from Clothing c where c.brand.name=:name group by c.name")
    List<Clothing> findAllByBrand(@Param("name") String name);

    Clothing findByNameAndSize(String name, Size size);

    @Query("select c from Clothing c where " +
            " concat(c.id,' ',c.name,' ',c.count,' ',c.price,' ',c.season,' ',c.size,' ',c.type,' ',c.brand.name,' ',c.category.name) " +
            "like %?1%")
    Page<Clothing> findAll(String keyword, Pageable pageable);




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
