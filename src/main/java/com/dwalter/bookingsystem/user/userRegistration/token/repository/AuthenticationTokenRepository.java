package com.dwalter.bookingsystem.user.userRegistration.token.repository;

import com.dwalter.bookingsystem.user.userRegistration.token.domain.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {
    Optional<AuthenticationToken> findByToken(String token);
}
