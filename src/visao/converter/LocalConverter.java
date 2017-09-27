package visao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Local;
import controle.EventoControle;

@FacesConverter(value = "LocalConverter", forClass=Local.class)
public class LocalConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Local local = null;
		if (value != null) {
			try {
				EventoControle eventoControle = new EventoControle();
				local = eventoControle.buscarLocalPorId(Long.parseLong(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return local;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value.getClass().equals(Local.class)) {
			return String.valueOf(((Local) value).getIdLocal());
		}
		return null;
	}

}
