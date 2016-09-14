package ua.mysite.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.form.SizeForm;
import ua.form.SizeFormFilter;
import ua.mysite.entity.Size;
import ua.mysite.repository.SizeRepository;
import ua.mysite.service.SizeService;
import ua.mysite.service.implementation.specification.SizeFormFilterAdapter;

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

	public Size findBySize(int size) {
		return sizeRepository.findBySize(size);
	}

	public Page<Size> findAll(Pageable pageable) {
		return sizeRepository.findAll(pageable);
	}

	@Override
	public Page<Size> findAll(Pageable pageable, SizeFormFilter form) {
		return sizeRepository.findAll(new SizeFormFilterAdapter(form), pageable);
	}

}
