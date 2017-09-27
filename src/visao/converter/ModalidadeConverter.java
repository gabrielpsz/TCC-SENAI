package visao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Modalidade;
import controle.EventoControle;

@FacesConverter(value = "ModalidadeConverter", forClass=Modalidade.class)
public class ModalidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Modalidade modalidade= null;
		if (value != null) {
			try {
				EventoControle eventoControle = new EventoControle();
				modalidade = eventoControle.buscarModalidadePorId(Long.parseLong(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return modalidade;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value.getClass().equals(Modalidade.class)) {
			return String.valueOf(((Modalidade) value).getIdModalidade());
		}
		return null;
	}

}
