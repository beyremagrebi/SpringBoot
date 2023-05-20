package com.iset.produits.service;

import com.iset.produits.entites.Categorie;
import com.iset.produits.entites.Produit;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategorieService {
    Page<Categorie> getAllCategorieParPage(int page, int size);

    List<Categorie> findByNomCatContains(String nom);

    List<Categorie> getAllCategorie();
    Categorie saveCategorie(Categorie cat);
    Categorie updateCategorie(Categorie cat);
    void deleteCategorie(Categorie cat);
    void deleteCategorieById(Long id);
    Categorie getCategorie(Long id);

}
