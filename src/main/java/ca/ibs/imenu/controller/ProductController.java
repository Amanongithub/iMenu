package ca.ibs.imenu.controller;

import ca.ibs.imenu.entity.Category;
import ca.ibs.imenu.entity.Product;
import ca.ibs.imenu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/commitSaveProduct", method = RequestMethod.POST)
    public String commitSaveProduct(Model model, Product product) {
        productService.save(product);
        return "redirect:listProduct";
    }

    @RequestMapping(value = "/commitDeleteProduct", method = RequestMethod.POST)
    public String commitDeleteProduct(Model model, Product product) {
        productService.delete(productService.findById(product.getId()));
        return "redirect:listProduct";
    }

    @RequestMapping(value = "/listProduct", method = RequestMethod.GET)
    public String listProduct(Model model) {
        model.addAttribute("body","products.jsp");
        model.addAttribute("object",productService.findAll());
        model.addAttribute("title", "List of Products");
        return "adminTemplate";
    }

    @RequestMapping(value = "/listProductByCategory", method = RequestMethod.GET)
    public String listProductByCategory(Model model,@RequestParam(name = "category", defaultValue = "APPETIZERS") String category) {
        model.addAttribute("body","productsByCategory.jsp");
        model.addAttribute("object",productService.findByCategory(Category.valueOf(category)));
        model.addAttribute("title", "List of "+category);
        return "customerTemplate";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProduct(Model model) {
        model.addAttribute("body","product.jsp");
        model.addAttribute("object",new Product());
        model.addAttribute("action","/commitSaveProduct");
        model.addAttribute("title", "Add Product");
        model.addAttribute("readonly", false);
        return "adminTemplate";
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.GET)
    public String editProduct(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("body","product.jsp");
        model.addAttribute("object",productService.findById(id));
        model.addAttribute("action","/commitSaveProduct");
        model.addAttribute("title", "Edit Product");
        model.addAttribute("readonly", false);
        return "adminTemplate";
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
    public String deleteProduct(Model model, @RequestParam(name = "id") Long id) {
        model.addAttribute("body","product.jsp");
        model.addAttribute("object",productService.findById(id));
        model.addAttribute("action","/commitDeleteProduct");
        model.addAttribute("title", "Delete Product");
        model.addAttribute("readonly", true);
        return "adminTemplate";
    }

}
