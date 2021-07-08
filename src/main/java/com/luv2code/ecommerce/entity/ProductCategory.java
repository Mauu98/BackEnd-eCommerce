package com.luv2code.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="product_category")
// @Data -- known bug
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    //La categoria puede ser obtenida por varios productos. Categoria - 0 productos. Productos || categorias.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;

}