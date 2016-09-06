package ua.mysite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ua.mysite.entity.Usr;

public interface UsrRepository extends JpaRepository<Usr, Integer>{

	Usr findByUsername(String username);
	
	Usr findByEmail(String email);
	
	Usr findByPassword (String password);
	
	@Query("select u from Usr u left join fetch u.role where u.id=:id")
	Usr findById(@Param("id") int id);
	
	@Modifying
	@Query("DELETE FROM Usr u WHERE u.id=:id")
	@Transactional
	void deleteById(@Param("id") int id);
	
	@Query("select u from Usr u left join fetch u.role")
	List<Usr> usrs();
	
	
}
