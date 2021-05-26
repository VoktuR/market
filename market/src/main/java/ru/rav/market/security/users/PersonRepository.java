package ru.rav.market.security.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByUsername(String username);

}
