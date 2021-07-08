package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries", path = "countries") //localhost:8080/api/countries
public interface CountryRepository extends JpaRepository<Country, Integer> { //Country es la entity class e Integer es el tipo de dato de la PK

}
