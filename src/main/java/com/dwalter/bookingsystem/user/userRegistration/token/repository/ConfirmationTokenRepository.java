package com.dwalter.bookingsystem.user.userRegistration.token.repository;

import com.dwalter.bookingsystem.user.userRegistration.token.domain.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
}
