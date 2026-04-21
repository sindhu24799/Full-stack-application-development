package com.subscription.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.subscription.membership.entity.User;

public interface UserRepository  extends JpaRepository<User, Long> {

}
