package lt.codeacademy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.entities.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long>{
	
	
}
