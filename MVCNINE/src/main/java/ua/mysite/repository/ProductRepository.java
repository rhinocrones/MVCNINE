package ua.mysite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.mysite.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	Product findByName(String name);

	Product findById(int id);

	@Modifying
	@Query("DELETE FROM Product p WHERE p.id=:id")
	@Transactional
	void deleteById(@Param("id") int id);

	@Query("select p from Product p left join fetch p.category left join fetch p.brand left join fetch p.size")
	List<Product> products();

	@Query("select p from Product p left join fetch p.category left join fetch p.brand left join fetch p.size where p.id=:id")
	Product findOneCategoryBrandSizeInited(@Param("id") int id);

	@Query(value = "select p from Product p left join fetch p.category left join fetch p.brand left join fetch p.size", countQuery = "SELECT count(p.id) from Product p")
	Page<Product> findAll(Pageable pageable);
}
