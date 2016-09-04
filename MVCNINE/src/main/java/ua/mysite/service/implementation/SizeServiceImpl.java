package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.form.SizeForm;
import ua.mysite.entity.Size;
import ua.mysite.repository.SizeRepository;
import ua.mysite.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeRepository sizeRepository;

	public void save(SizeForm form) {
		Size size = new Size();
		size.setId(form.getId());
		size.setSize(Integer.parseInt(form.getSize()));
		sizeRepository.save(size);
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

	public SizeForm findForForm(int id) {
		Size size = sizeRepository.findById(id);
		SizeForm form = new SizeForm();
		form.setId(size.getId());
		form.setSize(String.valueOf(size.getSize()));
		return form;

	}

}
