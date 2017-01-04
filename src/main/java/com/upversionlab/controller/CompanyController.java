package com.upversionlab.controller;

import com.upversionlab.model.Company;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rborcat on 1/3/2017.
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private Map<Integer, Company> companies = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Company> getCompanies() {
        return companies.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompany(@RequestParam int id) {
        return companies.get(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createCompany(@RequestBody Company company) {
        company.setId(companies.size());
        companies.put(company.getId(), company);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateCompany(@RequestParam int id, @RequestBody Company newCompany) {
        Company company = companies.get(id);
        company.update(newCompany);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCompany(@RequestParam int id) {
        companies.remove(id);
    }
}
