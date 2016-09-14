package ua.mysite.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.form.RoleFilter;
import ua.mysite.entity.Role;

public class RoleFilterAdapter implements Specification<Role> {

	private String search = "";

	public RoleFilterAdapter(RoleFilter form) {
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
		}
		Expression<String> exp = root.get("role");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}
