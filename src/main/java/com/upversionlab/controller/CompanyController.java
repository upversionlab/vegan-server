package com.upversionlab.controller;

import com.upversionlab.exception.EntityNotFoundException;
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
    private static final String COMPANY_RESOURCE_NAME = Company.class.getSimpleName();

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Company> getCompanies() {
        return companies.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable int id) {
        return getCompanyById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void createCompany(@RequestBody Company company) {
        company.setId(companies.size());
        companies.put(company.getId(), company);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateCompany(@PathVariable int id, @RequestBody Company newCompany) {
        Company company = getCompanyById(id);
        company.update(newCompany);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCompany(@PathVariable int id) {
        companies.remove(getCompanyById(id));
    }

    private Company getCompanyById(int id) {
        Company company = companies.get(id);
        if (company != null) {
            return company;
        } else {
            throw new EntityNotFoundException(COMPANY_RESOURCE_NAME + " " + id + " not found!");
        }
    }
}
