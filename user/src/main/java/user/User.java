package user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	  private @Id @GeneratedValue Long id;
	  private String name;
	  private String username;
	  private String email;
	  
	  public String toString() {
		  return name + ": " +username;
	  }
}
