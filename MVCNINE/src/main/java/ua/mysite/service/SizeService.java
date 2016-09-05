package ua.mysite.service;

import java.util.List;

import ua.form.SizeForm;
import ua.mysite.entity.Size;

public interface SizeService {

	void save(SizeForm form);
	
	Size findById(int id);
	
	SizeForm findForForm(int id);
	
	void deleteById(int id);
	
	List<Size> findAll();

	Size findBySize(int size);
}
