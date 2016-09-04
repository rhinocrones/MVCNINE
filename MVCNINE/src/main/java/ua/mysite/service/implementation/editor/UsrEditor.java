package ua.mysite.service.implementation.editor;

import java.beans.PropertyEditorSupport;

import ua.mysite.entity.Usr;
import ua.mysite.service.UsrService;

public class UsrEditor extends PropertyEditorSupport {

	private final UsrService usrService;

	public UsrEditor(UsrService usrService) {
		this.usrService = usrService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Usr usr = usrService.findById(Integer.valueOf(text));
		setValue(usr);
	}
}
