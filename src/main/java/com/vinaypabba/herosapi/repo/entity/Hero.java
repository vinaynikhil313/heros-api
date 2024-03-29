package com.vinaypabba.herosapi.repo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinaypabba.herosapi.model.HeroDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "HERO")
@Getter
@Setter
public class Hero {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REAL_NAME")
    private String realName;

    @Column(name = "HEALTH")
    private int health;

    @Column(name = "ARMOUR")
    private int armour;

    @Column(name = "SHIELD")
    private int shield;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "HERO_ABILITIES",
            joinColumns = { @JoinColumn(name = "HERO_ID") },
            inverseJoinColumns = { @JoinColumn(name = "ABILITY_ID") })
    @JsonIgnore
    private Set<Ability> abilities = new HashSet<>();

    @JsonIgnore
    public HeroDto getHeroDto() {
        HeroDto heroDto = new HeroDto();
        heroDto.setId(this.id);
        heroDto.setName(this.name);
        heroDto.setRealName(this.realName);
        heroDto.setArmour(this.armour);
        heroDto.setHealth(this.health);
        heroDto.setShield(this.shield);
        return heroDto;
    }

}
