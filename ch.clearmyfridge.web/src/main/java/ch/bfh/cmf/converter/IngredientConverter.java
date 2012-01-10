package ch.bfh.cmf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ch.bfh.cmf.domain.Ingredient;

@FacesConverter("ingredientConverter")
public class IngredientConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// 
		return 2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Ingredient) arg2).getId().toString();
	}

}
