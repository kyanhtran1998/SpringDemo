package com.example.demo.controller;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;



@Controller

public class mainController {

	@Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "list";
    }
	
    
    @GetMapping("/product/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/search";
        }

        model.addAttribute("products", productService.search(term));
        return "list";
    }
    
    
    
    @GetMapping("/product/add")
    public String add( Model model) {
    	model.addAttribute("product", new Product());
        return "add";
    }
    
    
    @PostMapping("/product/save")
    public String save(@Valid Product contact, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "add";
        }
        productService.save(contact);
        redirect.addFlashAttribute("successMessage", "Saved product successfully!");
        return "redirect:/product";
    }
    
    
    @GetMapping("/product/delete/{id}")
    public String detele(@PathVariable int id, RedirectAttributes reAttributes) {
    	productService.delete(id);
    	reAttributes.addFlashAttribute("successMessage", "Deleted product succsessfully");
        return "redirect:/product";

    }
    
    @RequestMapping("/product/update/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Optional<Product> product = productService.findOne(id);
			model.addAttribute("product", product.get());
		return "add";
	}

}
