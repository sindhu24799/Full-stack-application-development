package com.subscription.membership.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.subscription.membership.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

}
