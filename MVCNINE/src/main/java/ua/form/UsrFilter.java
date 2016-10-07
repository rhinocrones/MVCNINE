package ua.form;

import java.util.ArrayList;
import java.util.List;

public class UsrFilter {

	private String search = "";
	
	private List<Integer> roleIds = new ArrayList<>();

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
}
