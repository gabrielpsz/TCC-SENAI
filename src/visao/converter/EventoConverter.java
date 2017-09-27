package visao.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import modelo.Evento;
import controle.IngressoControle;

@FacesConverter(value = "EventoConverter", forClass=Evento.class)
public class EventoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Evento evento = null;
		if (value != null && !value.equals("Selecione...") && !value.equals("")) {
			try {
				IngressoControle ingressoControle = new IngressoControle();
				evento = ingressoControle.buscarEventoPorId(Long.parseLong(value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return evento;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value.getClass().equals(Evento.class)) {
			return String.valueOf(((Evento) value).getIdEvento());
		}
		return null;
	}

}
