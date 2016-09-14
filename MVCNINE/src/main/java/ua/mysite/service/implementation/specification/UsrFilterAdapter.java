package ua.mysite.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.form.UsrFilter;
import ua.mysite.entity.Usr;

public class UsrFilterAdapter implements Specification<Usr>{

	private String search = "";
	
	public UsrFilterAdapter(UsrFilter form) {
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Usr> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
			root.fetch("role");
		}
		Expression<String> exp = root.get("username");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}
