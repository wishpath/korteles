package lt.codeacademy.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Word {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@NotBlank(message = "Word is mandatory")
	String wordx;
	String translation;
	Long frequencyNr;
}
