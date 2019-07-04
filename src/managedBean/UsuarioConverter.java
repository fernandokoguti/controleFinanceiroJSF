package managedBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import DAO.UsuarioDAO;
import modelo.Usuario;

@FacesConverter(value ="usuarioConverter",forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario ret = null;
		if (value != null) {
			ret = dao.buscarPorId(Integer.parseInt(value));
			}
		return ret;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
	String str = "";
       if (value instanceof Usuario) {
            str = "" + ((Usuario) value).getId();
        }
        return str;
    }
}
