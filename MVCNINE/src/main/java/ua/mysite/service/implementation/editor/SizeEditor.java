package ua.mysite.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.mysite.entity.Size;
import ua.mysite.service.SizeService;

public class SizeEditor extends PropertyEditorSupport {

	private final SizeService sizeService;

	public SizeEditor(SizeService sizeService) {
		this.sizeService = sizeService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Size size = sizeService.findById(Integer.valueOf(text));
		setValue(size);
	}
}