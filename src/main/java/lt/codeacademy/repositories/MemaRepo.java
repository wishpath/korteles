package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.Mema;

@Repository
public interface MemaRepo extends JpaRepository<Mema,Long>{
	
	
}
