package ua.mysite.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.form.BrandFilter;
import ua.mysite.entity.Brand;

public class BrandFilterAdapter implements Specification<Brand> {

	private String search = "";

	public BrandFilterAdapter(BrandFilter form) {
		search = form.getSearch();
	}

	public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
		}
		Expression<String> exp = root.get("brand");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}
