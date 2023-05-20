package com.iset.produits.controller;

import com.iset.produits.entites.Categorie;
import com.iset.produits.entites.Produit;
import com.iset.produits.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CategoireController {


    @Autowired
    CategorieService categorieService;
    @RequestMapping("/showCat")
    public String showCreate(ModelMap modelMap) {
        modelMap.addAttribute("cat", new Categorie() );
        return "createCategorie";
    }

    @RequestMapping("/saveCategorie")
    public String saveCategorie(@Valid Categorie categorie,
                              BindingResult bindingResult,
                              ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            return "createCategorie";
        }
       Categorie saveCategorie = categorieService.saveCategorie(categorie);
        String msg = "catégorie enregistré avec Id " + saveCategorie.getIdCat();
        modelMap.addAttribute("msg", msg);
        return "createCategorie";
    }


    @RequestMapping("/ListeCategorie")
    public String listecats(
            ModelMap modelMap,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size)
    {
        Page<Categorie> cats=categorieService.getAllCategorieParPage(page,size);
        modelMap.addAttribute("cats", cats);
        modelMap.addAttribute("pages", new int[cats.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "ListeCategorie";
    }


    @RequestMapping("/supprimerCat")
    public String supprimerProduit(@RequestParam("id") Long id, ModelMap
            modelMap,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "2") int size) {
        categorieService.deleteCategorieById(id);

        Page<Categorie> cats=categorieService.getAllCategorieParPage(page,size);

        modelMap.addAttribute("cats", cats);
        modelMap.addAttribute("pages", new int[cats.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "ListeCategorie";
    }

    @RequestMapping("/modifierCat")
    public String editerCat(@RequestParam("id") Long id, ModelMap
            modelMap)
    {

        Categorie cat=categorieService.getCategorie(id);
        modelMap.addAttribute("cat", cat);
        return "editerCategorie";
    }

    @RequestMapping("/updateCategorie")
    public String updateProduit(@ModelAttribute("produit") Categorie categorie,
                               ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "2") int size) throws ParseException
    {
//conversion de la date
        categorieService.updateCategorie(categorie);

        Page<Categorie> cats=categorieService.getAllCategorieParPage(page,size);
        modelMap.addAttribute("cats", cats);
        modelMap.addAttribute("pages", new int[cats.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "ListeCategorie";
    }

    @RequestMapping("/filterCat")
    public String filterCategorie(@ModelAttribute("nom") String nom,ModelMap modelMap) {

        List<Categorie> prods =categorieService.findByNomCatContains(nom);
        Page<Categorie> prodspage=new PageImpl<>(prods);
        modelMap.addAttribute("cats", prodspage);

        return "ListeCategorie";
    }

}
