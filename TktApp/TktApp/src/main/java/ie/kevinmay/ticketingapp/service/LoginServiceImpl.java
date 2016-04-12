package ie.kevinmay.ticketingapp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ie.kevinmay.ticketingapp.model.Account;

@Service("loginServiceImpl")
public class LoginServiceImpl implements UserDetailsService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Query query = entityManager.createQuery("select a from Account a");
		List<Account> accounts = (List<Account>) query.getResultList();
		Account account = new Account();
		for (Account acc : accounts){
			if (acc.getUsername().equals(username)) {
				account = acc;
			}
		}
		
		if (account.getUsername() != null) {
		
		System.out.println(account.getRole());
		System.out.println(account.getUsername());
		
		boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
		return new User(
                account.getUsername(), 
                account.getPassword(), 
                enabled, 
                accountNonExpired, 
                credentialsNonExpired, 
                accountNonLocked,
                getAuthorities(account.getRole()));
		
		}else {
		    throw new UsernameNotFoundException("User not found");
		}
	}
	
	
	
	 public Collection<? extends GrantedAuthority> getAuthorities(String role) {
	        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
	        return authList;
	    }
	 
	 public List<String> getRoles(String role) {
		 
	        List<String> roles = new ArrayList<String>();
	        if (role.equals("ROLE_ADMIN")) {
	            roles.add("ROLE_ADMIN");
	        } else if (role.equals("ROLE_USER")) {
	            roles.add("ROLE_USER");
	        } else if (role.equals("ROLE_SUPER")) {
	            roles.add("ROLE_SUPER");
	        }
	        return roles;
	    }
	 
	 public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	         
	        for (String role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role));
	        }
	        return authorities;
	    }
	
}
