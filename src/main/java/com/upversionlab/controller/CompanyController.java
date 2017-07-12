package com.upversionlab.controller;

import com.upversionlab.exception.EntityNotFoundException;
import com.upversionlab.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private static final String COMPANY_RESOURCE_NAME = Company.class.getSimpleName();

    private final Map<Integer, Company> companies;

    @Autowired
    public CompanyController(Map<Integer, Company> companies) {
        this.companies = companies;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Company> getCompanies() {
        return companies.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable int id) {
        return getCompanyById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Company createCompany(@RequestBody Company company) {
        company.setId(companies.size());
        companies.put(company.getId(), company);
        return company;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Company updateCompany(@PathVariable int id, @RequestBody Company newCompany) {
        Company company = getCompanyById(id);
        company.update(newCompany);
        return company;
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
