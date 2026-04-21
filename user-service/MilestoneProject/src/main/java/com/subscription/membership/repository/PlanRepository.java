package com.subscription.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.subscription.membership.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
