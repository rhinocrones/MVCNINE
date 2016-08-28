package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.mysite.entity.Size;
import ua.mysite.repository.SizeRepository;
import ua.mysite.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeRepository sizeRepository;

	public void save(int size) {
		if(sizeRepository.findBySize(size)==null){
		Size size2 = new Size();
		size2.setSize(size);
		sizeRepository.save(size2);
		} else System.out.println("Exist");
	}

	public Size findById(int id) {
		return sizeRepository.findById(id);
	}

	public void deleteById(int id) {
		sizeRepository.deleteById(id);
	}

	public List<Size> findAll() {
		return sizeRepository.findAll();
	}

}
