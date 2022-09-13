package com.example.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersioning(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersioning(){
        return new PersonV2(new Name("Daniel", "Albornoz"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getVersioning(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getVersioningV2(){
        return new PersonV2(new Name("Daniel", "Albornoz"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getVersioningWithHeaders(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getVersioningSecondWithHeaders(){
        return new PersonV2(new Name("Daniel", "Albornoz"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getVersioningWithAccept(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getVersioningSecondWithAccept(){
        return new PersonV2(new Name("Daniel", "Albornoz"));
    }
}
