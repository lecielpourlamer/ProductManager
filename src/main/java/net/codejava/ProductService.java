package net.codejava;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

	private final ProductRepository repo;

	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public Page<Product> listAll() {
		Pageable pageable = PageRequest.of(0, 10);
		return repo.findAll(pageable);
	}

	public void save(Product product) {
		repo.save(product);
	}

	public Product get(long id) {

		Optional<Product> findById = repo.findById(id);

		return findById.orElse(new Product());
	}

	public void delete(long id) {
		repo.deleteById(id);
	}
}
