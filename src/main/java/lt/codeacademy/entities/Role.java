package lt.codeacademy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Lombok: includes @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together
@NoArgsConstructor 
@AllArgsConstructor 
public class Role { 
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
}
