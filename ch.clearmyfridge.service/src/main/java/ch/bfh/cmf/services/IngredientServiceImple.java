package ch.bfh.cmf.services;

import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ch.bfh.cmf.domain.Ingredient;
import ch.bfh.cmf.repositories.IngredientRepository;

@Service
public class IngredientServiceImple implements IngredientService {

	@Inject
	private IngredientRepository ingredientRepository;

	@Override
	public Collection<Ingredient> searchIngredients(String partialIngredientName) {
		final String searchString = StringUtils.stripToNull(partialIngredientName);
		if (searchString == null)
			return Collections.emptyList();
		return ingredientRepository.findByNameLikeIgnoringCase("%" + searchString + "%");
	}

}
