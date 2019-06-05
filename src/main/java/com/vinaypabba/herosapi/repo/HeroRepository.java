package com.vinaypabba.herosapi.repo;

import com.vinaypabba.herosapi.repo.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Integer> {

}
