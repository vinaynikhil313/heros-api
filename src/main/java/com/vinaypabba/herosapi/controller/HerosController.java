package com.vinaypabba.herosapi.controller;

import com.vinaypabba.herosapi.repo.entity.Ability;
import com.vinaypabba.herosapi.repo.entity.Hero;
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
import java.util.Set;

@RestController
@RequestMapping("/heros")
@Api(value = "Heros Controller", description = "API for viewing heros")
public class HerosController {

    @Autowired
    private HeroApiService heroApiService;

    @GetMapping
    @ApiOperation(value = "Get all the available heros", response = List.class)
    public ResponseEntity getAllHeros() {
        List<Hero> heros = heroApiService.getAllHeros();
        return ResponseEntity.ok(heros);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all the required hero based on ID", response = Hero.class)
    public ResponseEntity getHero(@PathVariable int id) {
        Hero hero = heroApiService.getHero(id);
        if (Objects.nonNull(hero)) {
            return ResponseEntity.ok(hero);
        } else {
            return ResponseEntity.badRequest().body(new HashMap<>());
        }
    }

    @GetMapping("/{id}/abilities")
    @ApiOperation(value = "Get all the abilities of a hero", response = List.class)
    public ResponseEntity getHeroAbilities(@PathVariable int id) {
        Set<Ability> abilities = heroApiService.getHeroAbilities(id);
        return ResponseEntity.ok(abilities);
    }

}
