package com.example.dao;

import com.example.bean.AlumnoBean;

public class AlumnoDAO {
	AlumnoBean objalAlumnoBean;
	public String CalcularOperacion(AlumnoBean objAlumnobean){
		String mensaje="";
		String categoria=objAlumnobean.getCategorias();
		String tipo=objAlumnobean.getTipo();
		int promedio=objAlumnobean.getPromedio();
		this.objalAlumnoBean=new AlumnoBean();
		this.objalAlumnoBean.setCategorias(categoria);
		this.objalAlumnoBean.setTipo(tipo);
		this.objalAlumnoBean.setPromedio(promedio);
		int pension=CalcularPensiones(this.objalAlumnoBean);
		this.objalAlumnoBean.setPension(pension);
		double descuento=CalcularDescuento(this.objalAlumnoBean);
		this.objalAlumnoBean.setDescuento(descuento);
		double nuevapension=CalcularNuevaPension(this.objalAlumnoBean);
		mensaje="La Pension Actual : "+pension+"\n"+
				"Descuento 		   : "+descuento+"\n"+
				"Nueva Pension	   :"+nuevapension;
		return mensaje;
	}
	public int CalcularPensiones(AlumnoBean objalumnoBean){
		int pension=0;
		if(objalumnoBean.getCategorias().equals("A")){
			if(objalumnoBean.getTipo().equals("INTERNO")){
				pension=600;
			}
			else{
				if(objalumnoBean.getTipo().equals("EXTERNO")){
					pension=550;
				}
			}
		}else
		{
			if(objalumnoBean.getCategorias().equals("B")){
				if(objalumnoBean.getTipo().equals("INTERNO")){
					pension=550;
				}
				else{
					if(objalumnoBean.getTipo().equals("EXTERNO")){
						pension=500;
					}
				}
			}else
			{
				if(objalumnoBean.getCategorias().equals("C")){
					if(objalumnoBean.getTipo().equals("INTERNO")){
						pension=500;
					}
					else{
						if(objalumnoBean.getTipo().equals("EXTERNO")){
							pension=460;
						}
					}
				}else
				{
					if(objalumnoBean.getCategorias().equals("D")){
						if(objalumnoBean.getTipo().equals("INTERNO")){
							pension=450;
						}
						else{
							if(objalumnoBean.getTipo().equals("EXTERNO")){
								pension=400;
							}
						}
					}
				}
			}
		}
		return pension;
	}
	public double CalcularDescuento(AlumnoBean objAlumnoBean){
		double descuento=0;
		if(objAlumnoBean.getPromedio()>=0 && objAlumnoBean.getPromedio()<=13.99){
			descuento=0;
		}else 
			{
			if (objAlumnoBean.getPromedio()>=14.00 && objAlumnoBean.getPromedio()<=15.99) {
				descuento=0.10;
			}else
				{
				if (objAlumnoBean.getPromedio()>=16.00 && objAlumnoBean.getPromedio()<=17.99) {
					descuento=0.12;
				}else{
					if (objAlumnoBean.getPromedio()>=18.00 && objAlumnoBean.getPromedio()<=20.00) {
						descuento=0.15;
					}
				}
			}
		}	
		return descuento;
	}
	
	public double CalcularNuevaPension(AlumnoBean objAlumnoBean){
		double nuevapension=0;
		nuevapension=objAlumnoBean.getPension()-objAlumnoBean.getDescuento();
		return nuevapension;
	}
}
