package hello.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodel.ProductAPIModel;
import hello.service.bllmodel.ProductBModel;
import hello.service.interfaces.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private ModelMapper mapper;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductAPIModel>> getAllProducts() {
		List<ProductBModel> list = productService.getAllProducts();
		List<ProductAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, ProductAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{productId}")
	public ResponseEntity<ProductAPIModel> getProductById(@PathVariable("productId") int productId){
		ProductBModel productB = productService.getProductById(productId);
		if(productB == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		ProductAPIModel product = mapper.map(productB, ProductAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProductAPIModel> addProduct(@RequestBody ProductAPIModel product){
		if(productService.addProduct(mapper.map(product, ProductBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(product);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{productId}")
	public ResponseEntity<ProductAPIModel> updateProduct(@PathVariable("productId") int productId,@RequestBody ProductAPIModel product){
		if(productService.updateProduct(productId, mapper.map(product, ProductBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(product);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
	public ResponseEntity<ProductAPIModel> deleteProduct(@PathVariable("productId") int productId){
		if(productService.deleteProduct(productId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/customer")
	public ResponseEntity<ProductAPIModel> rentProduct(@RequestParam("productId") int productId, @RequestParam("customerId") int customerId){
		productService.rentProduct(customerId, productId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
