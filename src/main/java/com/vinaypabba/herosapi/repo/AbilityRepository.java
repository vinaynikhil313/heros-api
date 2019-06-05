package com.vinaypabba.herosapi.repo;

import com.vinaypabba.herosapi.repo.entity.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {
}
