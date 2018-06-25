/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package converter;
//
//import bean.Endereco;
////Não consigo exportar mng.EnderecoMNG;
//import javax.el.ValueExpression;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.ConverterException;
//import javax.faces.convert.FacesConverter;
//import javax.persistence.Converter;
//
///**
// *
// * @author Marcos Pachola
// */
//@FacesConverter(value = "enderecoConverter")
//public class EnderecoConverter implements Converter {
//
//    @Override
//     public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ValueExpression vExp = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{enderecoMNG}", Endereco.class);
//        EnderecoMNG enderecoMNG = (EnderecoMNG) vExp.getValue(ctx.getELContext());
//        Endereco end = enderecoMNG.getEndereco(Long.valueOf(value));
//        if (end == null) {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor desconhecido", "Endereco não foi encontrado");
//            throw new ConverterException(msg);
//        }
//        return end;
//     }
//}
