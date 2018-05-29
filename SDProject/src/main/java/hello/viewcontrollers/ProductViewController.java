package hello.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductViewController {

	@RequestMapping("/listProducts")
	public ModelAndView listProducts() {
		ModelAndView mv = new ModelAndView("listProducts");
		return mv;
	}
	
	@RequestMapping("/addProductView")
	public ModelAndView addProduct() {
		ModelAndView mv = new ModelAndView("addProduct");
		return mv;
	}
	
	@RequestMapping("/modifyProduct/{productId}")
	public ModelAndView modifyProduct(@PathVariable("productId") int productId) {
		ModelAndView mv = new ModelAndView("modifyProduct");
		mv.addObject("productId", productId);
		return mv;
	}
	
}
