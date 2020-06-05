package user;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	  @GetMapping("/users")
	  List<User> all() {
	    return userRepository.findAll();
	  }

	  @PostMapping("/users")
	  User newEmployee(@RequestBody User newUser) {
	    return userRepository.save(newUser);
	  }

	  // Single item

	  @GetMapping("/users/{id}")
	  User one(@PathVariable Long id) {

	    return userRepository.findById(id)
	      .orElseThrow(() -> new UserNotFoundException(id));
	  }

	  @PutMapping("/users/{id}")
	  User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {

	    return userRepository.findById(id)
	      .map(user -> {
	    	  user.setName(newUser.getName());
	    	  user.setEmail(newUser.getEmail());
	    	  user.setUserName(newUser.getUserName());
	    	  return userRepository.save(user);
	      })
	      .orElseGet(() -> {
	    	  newUser.setId(id);
	        return userRepository.save(newUser);
	      });
	  }

	  @DeleteMapping("/users/{id}")
	  void deleteEmployee(@PathVariable Long id) {
		  userRepository.deleteById(id);
	  }
	
}
