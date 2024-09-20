package com.jsp.ets.user;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

	public abstract List<User> findByRole(UserRole role);


	@Query("from User where email=:username")
	Optional<User> findByEmail(String username);

}
