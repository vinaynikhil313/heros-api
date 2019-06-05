package com.vinaypabba.herosapi.controller;

import com.vinaypabba.herosapi.repo.entity.Ability;
import com.vinaypabba.herosapi.service.HeroApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/abilities")
@Api(value = "Abilities Controller", description = "API for viewing abilities of heros")
public class AbilitiesController {

    @Autowired
    private HeroApiService heroApiService;

    @GetMapping
    @ApiOperation(value = "Get all the available abilities", response = List.class)
    public ResponseEntity getAllAbilities() {
        List<Ability> abilities = heroApiService.getAllAbilities();
        return ResponseEntity.ok(abilities);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all the required ability based on ID", response = Ability.class)
    public ResponseEntity getAbility(@PathVariable int id) {
        Ability ability = heroApiService.getAbility(id);
        if (Objects.nonNull(ability)) {
            return ResponseEntity.ok(ability);
        }
        else {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }

}
