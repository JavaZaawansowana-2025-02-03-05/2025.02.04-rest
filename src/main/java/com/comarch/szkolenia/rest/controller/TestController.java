package com.comarch.szkolenia.rest.controller;

import com.comarch.szkolenia.rest.model.EntityParent;
import com.comarch.szkolenia.rest.services.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final EntityService entityService;

    @RequestMapping(path = "/entity/{id}", method = RequestMethod.GET)
    public EntityParent getById(@PathVariable("id") Long id) {
        EntityParent ep = this.entityService.getById(id);
        System.out.println("controller !!!!");
        return ep;
    }

    @RequestMapping(path = "/entity", method = RequestMethod.POST)
    public void persist(@RequestBody EntityParent entityParent) {
        this.entityService.persist(entityParent);
    }
}
