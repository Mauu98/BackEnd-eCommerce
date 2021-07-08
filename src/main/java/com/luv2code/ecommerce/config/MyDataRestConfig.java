package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Country;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration //Permite que se escaneen todos los datos o ajustes hechos.
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        HttpMethod[] theUnssupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};


        //Se desactivan las opciones POST, PUT Y DELETE
        disableHttpMethods(ProductCategory.class, config, theUnssupportedActions);
        disableHttpMethods(Product.class, config, theUnssupportedActions);
        disableHttpMethods(Country.class, config, theUnssupportedActions);
        disableHttpMethods(State.class, config, theUnssupportedActions);


        exposeId(config);


    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnssupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnssupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnssupportedActions)));
    }

    private void exposeId(RepositoryRestConfiguration config) {

        //expose entitys ID
        //

        //get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        //Create an array of entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entitys type for the entitys
        for (EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }

        //Expose the entity id for the arrays of entity/domain type
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

    }


}
