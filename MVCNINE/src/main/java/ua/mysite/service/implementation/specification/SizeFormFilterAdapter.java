package ua.mysite.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.form.SizeFormFilter;
import ua.mysite.entity.Size;

public class SizeFormFilterAdapter implements Specification<Size> {

	private String search = "";

	public SizeFormFilterAdapter(SizeFormFilter form) {
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Size> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
		}
		Expression<String> exp = root.get("size");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}
