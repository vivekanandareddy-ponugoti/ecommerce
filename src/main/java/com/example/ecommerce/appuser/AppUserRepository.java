package com.example.ecommerce.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author vivek
 */

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * find all app users with given email address
     * @param email string
     * @return list of AppUser with that email or none
     */
    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("delete from AppUser i where i.email = ?1")
    void deleteByEmail(String email);

}
