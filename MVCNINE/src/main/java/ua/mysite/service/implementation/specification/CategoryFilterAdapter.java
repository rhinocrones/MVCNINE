package ua.mysite.service.implementation.specification;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.form.CategoryFilter;
import ua.mysite.entity.Category;

public class CategoryFilterAdapter implements Specification<Category> {

	private String search = "";

	public CategoryFilterAdapter(CategoryFilter form) {
		search = form.getSearch();
	}

	public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
		}
		Expression<String> exp = root.get("category");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}