package lt.codeacademy.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Course {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	Long id;
	String name;
	String description;

	@ManyToMany
	@JoinTable(name = "course_mema",  //jungiamosios lenteles pavadinimas
	joinColumns = @JoinColumn(name = "course_id"), //column pavadinimas jungiamojoje lenteleje
	inverseJoinColumns = @JoinColumn(name = "mema_id")) //column pavadinimas jungiamojoje lenteleje
	@JsonManagedReference
	List<Mema> memas;
}
