package com.vinaypabba.herosapi.model;

import com.vinaypabba.herosapi.repo.entity.Ability;
import com.vinaypabba.herosapi.repo.entity.Hero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class HeroDto {

    private int id;

    private String name;

    private String realName;

    private int health;

    private int armour;

    private int shield;

    private List<AbilityDto> abilities = null;

    public Hero getHeroEntity() {
        Hero hero = new Hero();
        hero.setId(this.id);
        hero.setName(this.name);
        hero.setRealName(this.realName);
        hero.setArmour(this.armour);
        hero.setHealth(this.health);
        this.setShield(this.shield);
        Set<Ability> abilitySet = new HashSet<>();
        abilities.forEach(ability -> abilitySet.add(ability.getAbilityEntity()));
        hero.setAbilities(abilitySet);
        return hero;
    }

}
