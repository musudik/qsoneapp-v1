package au.com.qsone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.qsone.entity.Product;
import au.com.qsone.entity.ProductType;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private au.com.qsone.service.ProductService productService;

    @Autowired
    private au.com.qsone.service.ProductTypeService productTypeService;

    private String add_edit_template="/admin/product/add-edit-product";
    private String list_template="/admin/product/list-product";
    private String list_redirect="redirect:/product/list";


    @GetMapping("/add")
    public String addProduct(Product product, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }
    

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model){
        Product product = productService.get(id);
        model.addAttribute("product", product);

        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        if(result.hasErrors()){
            return add_edit_template;
        }

        productService.save(product);
        return list_redirect+"?success";
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        productService.delete(id);

        return list_redirect+"?deleted";
    }

    @GetMapping("/list")
    public String listProduct(Model model) {
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);

        return list_template;
    }
}