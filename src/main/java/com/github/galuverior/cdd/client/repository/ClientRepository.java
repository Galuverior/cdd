package com.github.galuverior.cdd.client.repository;

import com.github.galuverior.cdd.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT TRUE AS LOGIN FROM Client WHERE email like :email and senha like :senha")
    Boolean validateClientCredentials(@Param("email") String email, @Param("senha") String senha);

}
