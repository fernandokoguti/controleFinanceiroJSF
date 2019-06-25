package managedBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import DAO.TipoMovimentacaoDAO;
import modelo.TipoMovimentacao;

//@FacesConverter(value = "classeConverter")    
//@FacesConverter(forClass = TipoMovimentacao.class)
@FacesConverter(value ="tipoMovimentacaoConverter",forClass = TipoMovimentacao.class)
public class TipoMovimentacaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		TipoMovimentacaoDAO dao = new TipoMovimentacaoDAO();
		TipoMovimentacao ret = null;
		if (value != null) {
			ret = dao.buscarPorId(Integer.parseInt(value));
			}
		return ret;
//		if(value == null)
//			return null;
//		}
//		return (TipoMovimentacao) uiComponent.getAttributes().get(value);
//		if (value != null && !value.isEmpty()) {
//			return (TipoMovimentacao) uiComponent.getAttributes().get(value);
//		} else
//		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
	String str = "";
       if (value instanceof TipoMovimentacao) {
            str = "" + ((TipoMovimentacao) value).getId();
        }
        return str;
    }
//		if (value instanceof TipoMovimentacao) {
//			TipoMovimentacao entity = (TipoMovimentacao) value;
//			if (entity != null && entity instanceof TipoMovimentacao && String.valueOf(entity.getId()) != null) {
//				uiComponent.getAttributes().put(String.valueOf(entity.getId()), entity);
//				return String.valueOf(entity.getId());
//			}
//		}
//		return "";
//	}
}
