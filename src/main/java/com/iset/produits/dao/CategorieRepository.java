package com.iset.produits.dao;

import com.iset.produits.entites.Categorie;
import com.iset.produits.entites.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
    List<Produit> findByNomCat(String nom);
    List<Categorie> findByNomCatContains(String nom);

}
