package lt.codeacademy.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Word {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NotBlank(message = "Word is mandatory")
	String wordx;
	String translation;
	Long frequencyNr;
	@OneToMany (mappedBy= "parent")
	List<Wordsplit> wordsplits;
	
	@Override
	public String toString() {
		return "Word ["+ "wordx=" + wordx + ", translation=" + translation + ", frequencyNr=" + frequencyNr
				+ "]";
	}
	
	
}
