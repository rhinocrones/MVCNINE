package ua.mysite.service.implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.form.ProductFormFilter;
import ua.mysite.entity.Product;

public class ProductFormFilterAdapter implements Specification<Product>{
	
	private String search = "";
	
	public ProductFormFilterAdapter(ProductFormFilter form) {
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (query.getResultType() != Long.class
				&& query.getResultType() != long.class) {
			root.fetch("category");
			root.fetch("brand");
			root.fetch("size");
		}
		Expression<String> exp = root.get("name");
		return cb.like(cb.upper(exp), search.toUpperCase() + "%");
	}

}
