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
@NoArgsConstructor //we need them because empty constructor 
@AllArgsConstructor //is rewritten by Lombok's @Data
public class Role { //TODO 06 - Create Role class
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
}
