package com.example.dao;

import com.example.bean.AlumnoBean;

public class AlumnoDAO {

	AlumnoBean objAlumnoBean;
	public String CalcularOperacion(AlumnoBean objAlumnoBean){
		String msj="";
		this.objAlumnoBean=new AlumnoBean();
		this.objAlumnoBean=CalcularFacultad(objAlumnoBean);
		int montototal=CalcularMontoTotal(this.objAlumnoBean);
		msj="Importe de la matricula : "+this.objAlumnoBean.getImporte()+"\n"+
			"Monto de la mensualidad : "+this.objAlumnoBean.getMensualidad()+"\n"+
			"Monto total			 : "+montototal;
		return msj;
	}
	public AlumnoBean CalcularFacultad(AlumnoBean objAlumnoBean){
		switch (objAlumnoBean.getFacultad()) {
			case 0:	objAlumnoBean.setImporte(350);
					objAlumnoBean.setMensualidad(590);
					break;
			case 1:	objAlumnoBean.setImporte(300);
					objAlumnoBean.setMensualidad(550);
					break;
			case 2:	objAlumnoBean.setImporte(300);
					objAlumnoBean.setMensualidad(500);
					break;
			case 3:	objAlumnoBean.setImporte(310);
					objAlumnoBean.setMensualidad(550);
					break;
			case 4:	objAlumnoBean.setImporte(280);
					objAlumnoBean.setMensualidad(490);
					break;
		}
		return objAlumnoBean;
		}
	public int CalcularMontoTotal(AlumnoBean objAlumnoBean){
		int montototal;
		montototal=objAlumnoBean.getImporte()+objAlumnoBean.getMensualidad();
		return montototal;
	}
}
