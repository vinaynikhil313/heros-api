package com.vinaypabba.herosapi.model;

import com.vinaypabba.herosapi.repo.entity.Ability;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AbilityDto {

    private int id;

    private String name;

    private String description;

    private boolean isUltimate;

    public Ability getAbilityEntity() {
        Ability ability = new Ability();
        ability.setId(this.id);
        ability.setName(this.name);
        ability.setDescription(this.description);
        ability.setUltimate(this.isUltimate);
        return ability;
    }

}
