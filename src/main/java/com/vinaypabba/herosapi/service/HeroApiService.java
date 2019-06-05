package com.vinaypabba.herosapi.service;

import com.vinaypabba.herosapi.repo.AbilityRepository;
import com.vinaypabba.herosapi.repo.HeroRepository;
import com.vinaypabba.herosapi.repo.entity.Ability;
import com.vinaypabba.herosapi.repo.entity.Hero;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class HeroApiService {

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private AbilityRepository abilityRepository;

    public Hero getHero(int id) {
        Optional<Hero> heroOptional = heroRepository.findById(id);
        return heroOptional.orElse(null);
    }

    public List<Hero> getAllHeros(){
        return heroRepository.findAll();
    }

    public Set<Ability> getHeroAbilities(int id) {
        Hero hero = getHero(id);
        Set<Ability> abilities = new HashSet<>();
        if (Objects.nonNull(hero)) {
            abilities = hero.getAbilities();
        }
        return abilities;
    }

    public Ability getAbility(int id) {
        Optional<Ability> abilityOptional = abilityRepository.findById(id);
        return abilityOptional.orElse(null);
    }

    public List<Ability> getAllAbilities(){
        return abilityRepository.findAll();
    }

}
