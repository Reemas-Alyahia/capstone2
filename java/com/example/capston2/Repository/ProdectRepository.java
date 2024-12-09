package com.example.capston2.Repository;

import com.example.capston2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdectRepository extends JpaRepository<Product,Integer> {
    Product findProductByProductId(Integer productId);


    @Query("select p from Product p where p.productId=?1 and p.stock=?2")
    Product getByProductProductIdAndQuantity(Integer productId, Integer stock);

    @Query("select p from Product p where p.discount> :discount")
    List<Product> getProductsByDiscountGreaterThan(Double discount);
///
@Query("SELECT p FROM Product p WHERE p.productId = :productId AND p.stock >= :quantity")
Product findProductByProductIdAndStock(Integer productId, Integer quantity);

@Query("select p from Product p where p.category=?1")
List<Product> getProductsByCategory(String Category);


    @Query("select p from Product p where p.category=?1 and p.ageFor>=?2 and p.ageFor<=?3")
    List<Product> getProductsByCategoryAndage(String Category,Integer min, Integer max);

@Query("select p from Product p where p.price>=?1 and p.price<=?2")
    List<Product>getProductsByPrice(Integer min, Integer max);

@Query("select p from Product p where p.name=?1")
List<Product>getProductsByName(String name);

}
