package org.jspiders.SpringMvcProjectCrudDemo4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    List<Product> productList=new ArrayList<>();
    {
        productList.add(new Product(1,"LAPTOP",45000));
        productList.add(new Product(2,"MOBILE",5000));
        productList.add(new Product(3,"HEADPHONE",4000));
    }
    @GetMapping("/")
    public String getAllProduct(Model model){
        model.addAttribute("records",productList);
        return "productlist";
    }
    @GetMapping("/addproduct")
    public String displayProductForm(Model model){
        model.addAttribute("product",new Product());
        return "productform";
    }
    @PostMapping("/insertproduct")
    public String addProductDetails(Product p){
        productList.add(p);
        return "redirect:/";
    }
    @GetMapping("/updateproduct/{id}")
    public String showUpdateForm( @PathVariable (value = "id") int id,  Model model){
         Product p= productList.get(id-1);
        model.addAttribute("pro",p);
       return "updateproduct";
    }
    @GetMapping("/modifyproduct")
    public String updateProduct(Product p){
        productList.set(p.getProductId()-1,p);
        return "redirect:/";
    }

    public String removeProduct(@PathVariable (value = "id") int id,Model model){
       Product p= productList.get(id-1);
       productList.remove(p);
        return "redirect:/";
    }
}
