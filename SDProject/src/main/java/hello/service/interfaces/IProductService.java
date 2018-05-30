package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.ProductBModel;

public interface IProductService {
	
	public List<ProductBModel> getAllProducts();
	
	public ProductBModel getProductById(int id);
	
	public boolean addProduct(ProductBModel product);
	
	public boolean updateProduct(int id, ProductBModel product);
	
	public boolean deleteProduct(int id);

	void rentProduct(int customerId, int productId);

	List<ProductBModel> getProductsByCustomerId(int customerId);

	int returnProduct(int customerId, int productId);

}
