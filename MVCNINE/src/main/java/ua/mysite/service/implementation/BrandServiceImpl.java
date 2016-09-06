package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.mysite.entity.Brand;
import ua.mysite.repository.BrandRepository;
import ua.mysite.service.BrandService;

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

}
