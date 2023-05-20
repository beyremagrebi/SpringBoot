package com.iset.produits.service;

import com.iset.produits.dao.ProduitRepository;
import com.iset.produits.entites.Categorie;
import com.iset.produits.entites.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService{

    @Autowired
    ProduitRepository produitRepository;
    @Override
    public Produit saveProduit(Produit p) {
        return produitRepository.save(p);
    }
    @Override
    public Produit updateProduit(Produit p) {
        return produitRepository.save(p);
    }
    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);
    }
    @Override
    public void deleteProduitById(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public Produit getProduit(Long id) {
        return produitRepository.findById(id).get();
    }
    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Page<Produit> getAllProduitsParPage(int page, int size) {
// TODO Auto-generated method stub
        return produitRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        List<Produit> prods = produitRepository.findByNomProduit(nom);
        for (Produit p : prods) {
            System.out.println("FindByNomProduit " + p.getNomProduit());
        }
        return prods;
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        List<Produit> prods = produitRepository.findByNomProduitContains(nom);
        for (Produit p : prods) {
            System.out.println(p.getNomProduit());
        }
        return prods;
    }

    @Override
    public List<Produit> findByNomPrix(String nom,Double prix) {
        List<Produit> prods = produitRepository.findByNomPrix(nom,prix);
        for (Produit p : prods) {
            System.out.println(p);
        }
        return prods;
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        Categorie cat = new Categorie();
        cat.setIdCat(1L);
        List<Produit> prods = produitRepository.findByCategorie(cat);
        for (Produit p : prods) {
            System.out.println(p);
        }
        return prods;
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        List<Produit> prods = produitRepository.findByCategorieIdCat(id);
        for (Produit p : prods) {
            System.out.println(p);
        }
        return prods;
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        List<Produit> prods = produitRepository.findByOrderByNomProduitAsc();
        for (Produit p : prods) {
            System.out.println(p);
        }
        return prods;
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        List<Produit> prods = produitRepository.trierProduitsNomsPrix();
        for (Produit p : prods) {
            System.out.println(p);
        }
        return prods;
    }

}
