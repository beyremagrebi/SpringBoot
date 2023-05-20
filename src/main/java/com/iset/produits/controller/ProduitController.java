package com.iset.produits.controller;

import com.iset.produits.entites.Categorie;
import com.iset.produits.entites.Produit;
import com.iset.produits.service.CategorieService;
import com.iset.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ProduitController {
    @Autowired
    ProduitService produitService;

    @Autowired
    CategorieService categorieService;
    @RequestMapping("/showCreate")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("produit", new Produit() );
        List<Categorie> categories=categorieService.getAllCategorie();
        modelMap.addAttribute("cats", categories );
        return "createProduit";
    }

    @RequestMapping ("/saveProduit")
    public String saveProduit(@Valid Produit produit,
                              BindingResult bindingResult,
                              ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "createProduit";
        }
        Produit saveProduit = produitService.saveProduit(produit);
        String msg = "produit enregistré avec Id " + saveProduit.getIdProduit();
        List<Categorie> categories=categorieService.getAllCategorie();
        modelMap.addAttribute("cats", categories );
        modelMap.addAttribute("msg", msg);
        return "createProduit";
    }

    @RequestMapping("/ListeProduits")
    public String listeProduits(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size)
    {
        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeProduits";
    }

    @RequestMapping("/supprimerProduit")
    public String supprimerProduit(@RequestParam("id") Long id, ModelMap
            modelMap,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "2") int size) {
        produitService.deleteProduitById(id);
        Page<Produit> prods = produitService.getAllProduitsParPage(page,
                size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "listeProduits";
    }

    @RequestMapping("/modifierProduit")
    public String editerProduit(@RequestParam("id") Long id, ModelMap
            modelMap)
    {
      Produit p=produitService.getProduit(id);
        modelMap.addAttribute("produit", p);
        return "editerProduit";
    }
    @RequestMapping("/updateProduit")
    public String updateProduit(@ModelAttribute("produit") Produit produit,
                                @RequestParam("date") String date, ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "2") int size) throws ParseException
    {
//conversion de la date
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(String.valueOf(date));
        produit.setDateCreation(dateCreation);
        produitService.updateProduit(produit);
        Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", prods);
        modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "listeProduits";
    }

    @RequestMapping("/filter")
    public String filterProduit(@ModelAttribute("nom") String nom,ModelMap modelMap) {

        List<Produit> prods =produitService.findByNomProduitContains(nom);
        Page<Produit> prodspage=new PageImpl<>(prods);
        modelMap.addAttribute("produits", prodspage);

        return "listeProduits";
    }
}
