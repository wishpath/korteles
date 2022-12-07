package lt.codeacademy.services;

import java.util.List;

import lt.codeacademy.entities.Role;
import lt.codeacademy.entities.User;

//TODO 15 - Create UserService 
public interface UserService {
	//TODO 16 - Add these methods to the interface
	User save(User user);
	Role save(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	/** Implementation needs paging to not load all users from big database* 
	 * @return
	 */
	List<User> getUsers();
	void updatePassword(String username, String newPassword);
	User registerNewUserAccount(User userDto);
}
