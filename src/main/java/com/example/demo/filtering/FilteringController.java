package com.example.demo.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("filtering")
    public SomeBean filtering(){
        return new SomeBean("team1", "team2", "team3");
    }

    @GetMapping("filtering-list")
    public List<SomeBean> filteringList(){
        return Arrays.asList(
                new SomeBean("team1", "team2", "team3"),
                new SomeBean("team4", "team5", "team6"),
                new SomeBean("team7", "team8", "team9"),
                new SomeBean("team10", "team11", "team12")
        );
    }

    @GetMapping("/filteringV2")
    public MappingJacksonValue filteringV2(){
        SomeBean someBean = new SomeBean("1", "2", "3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
