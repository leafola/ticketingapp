package ie.kevinmay.dao;


import ie.kevinmay.model.Users;

/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
public interface UsersDAO {
	
	public void saveOrUpdate(Users user);
	
	//public void delete(int contactId);
	
	//public Contact get(int contactId);
	
	//public List<Contact> list();
}
