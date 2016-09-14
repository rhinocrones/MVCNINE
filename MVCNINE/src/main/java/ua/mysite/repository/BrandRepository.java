package ua.mysite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.mysite.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand>{

	Brand findById(int id);
	
	Brand findByBrand(String brand);
	
	@Modifying
	@Query("DELETE FROM Brand b WHERE b.id=:id")
	@Transactional
	void deleteById(@Param("id") int id);
}
