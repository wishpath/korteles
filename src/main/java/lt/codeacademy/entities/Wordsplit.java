package lt.codeacademy.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Wordsplit {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NotBlank(message = "Word is mandatory")
	String word;
	@NotBlank(message = "Translation is mandatory")
	String translation;
	//@JsonManagedReference
	@JsonBackReference
	@ManyToOne
	@JoinColumn //uždėjau ir po to netikrinau
	Word parent;
	
	@ManyToMany(mappedBy = "wordsplits")
	@JsonBackReference
	List<Mema> memas;
	
	//Double frequencySplitFractionWeigt; //is parent
	//String grammar; //pvz verb_regular, tuomet formos automatinės, jei verb_irregular - formos prie additionalform // 
	//@OneToMany Wordform additionalform // prie formos parasyta ar regular
	
	
	//private String espanol;
	//private String espanolIPA;
	//private String espanolSoundFile; // koks kintamasis?
	//private String espanolMask; // atidengtos žodžio dalys, rodomas žodžio ilgis, rodomi kirčiai
	
	//private String english;
	//private String englishIPA;
	//private String englishSoundFile; // koks kintamasis?
	//private String englishMask; // atidengtos žodžio dalys, rodomas žodžio ilgis, rodomi kirčiai
	
	//private String topic;
	//private String pictue;// koks kintamasis?
	
	//private int streak; // 0-8
	//private boolean readyToPractice; // priklausomai nuo streak ir lastPracticed(timeFinished) // gal metodas, o gal į stats klasę
	//Stats prie Mema
	//Courses prie Mema
}
