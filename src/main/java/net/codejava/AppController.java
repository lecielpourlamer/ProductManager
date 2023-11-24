package net.codejava;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	private final ProductService productService;

	public AppController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/")
	public String viewHomePage(Model model) {

		Page<Product> page = productService.listAll();
		List<Product> listProducts = page.getContent();

		model.addAttribute("listProducts", listProducts);

		return "index";
	}

	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);

		return "new_product";
	}

	@PostMapping(value = "/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.save(product);

		return "redirect:/";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = productService.get(id);
		mav.addObject("product", product);

		return mav;
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		productService.delete(id);
		return "redirect:/";
	}
}
