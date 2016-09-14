package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.form.BrandFilter;
import ua.mysite.entity.Brand;
import ua.mysite.repository.BrandRepository;
import ua.mysite.service.BrandService;
import ua.mysite.service.implementation.specification.BrandFilterAdapter;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	public void save(Brand brand) {
		brandRepository.save(brand);
	}

	public Brand findById(int id) {
		return brandRepository.findById(id);
	}

	public void deleteById(int id) {
		brandRepository.deleteById(id);
	}

	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	public Brand findByBrand(String brand) {
		return brandRepository.findByBrand(brand);
	}

	public Page<Brand> findAll(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	@Override
	public Page<Brand> findAll(Pageable pageable, BrandFilter form) {
		return brandRepository.findAll(new BrandFilterAdapter(form), pageable);
	}

}
