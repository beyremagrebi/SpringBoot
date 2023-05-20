package com.iset.produits.service;

import com.iset.produits.dao.CategorieRepository;
import com.iset.produits.entites.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoireServiceImpl implements CategorieService{

    @Autowired
    CategorieRepository categorieRepository;
    @Override
    public Page<Categorie> getAllCategorieParPage(int page, int size) {
        return categorieRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Categorie> findByNomCatContains(String nom) {
        List<Categorie> cats = categorieRepository.findByNomCatContains(nom);
        for (Categorie c : cats) {
            System.out.println(c.getNomCat());
        }
        return cats;
    }

    @Override
    public Categorie saveCategorie(Categorie cat) {
        return categorieRepository.save(cat);
    }

    @Override
    public Categorie updateCategorie(Categorie cat) {

        return categorieRepository.save(cat);
    }

    @Override
    public void deleteCategorie(Categorie cat) {
        categorieRepository.delete(cat);
    }

    @Override
    public void deleteCategorieById(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepository.findById(id).get();
    }



    @Override
    public List<Categorie> getAllCategorie(){
        return categorieRepository.findAll();

    }
}
