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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Mema {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mema_id")
	Long id;
	String nonLiteral;

	@ManyToMany
	@JoinTable(name = "mema_wordsplit",  //jungiamosios lenteles pavadinimas
	joinColumns = @JoinColumn(name = "mema_id"), //column pavadinimas jungiamojoje lenteleje
	inverseJoinColumns = @JoinColumn(name = "wordsplit_id")) //column pavadinimas jungiamojoje lenteleje
	@JsonManagedReference
	List<Wordsplit> wordsplits;
	
	@ManyToMany(mappedBy = "memas")
	@JsonBackReference
	List<Course> courses;
}
