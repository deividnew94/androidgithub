package com.example.dao;

import com.example.bean.ProductoBean;

public class ProductoDAO {
	private ProductoBean objProductoBean;
	public String CalcularOperacion(ProductoBean objProductoBean){
		String mensaje = "";

		this.objProductoBean=new ProductoBean();
		this.objProductoBean.setMarca(objProductoBean.getMarca());
		this.objProductoBean.setTalla(objProductoBean.getTalla());
		this.objProductoBean.setNumpares(objProductoBean.getNumpares());
		int costo=CalcularCostoParZapatillas(this.objProductoBean);
		this.objProductoBean.setCosto(costo);
		int venta=CalcularVenta(this.objProductoBean);
		this.objProductoBean.setVenta(venta);
		double descuento=CalcularDescuento(this.objProductoBean);
		this.objProductoBean.setDescuento(descuento);
		double ventaneta=CalcularVentaNeta(this.objProductoBean);
		this.objProductoBean.setVentaneta(ventaneta);
		
//		calculo+="Venta : ";
//		calculo+=this.objProductoBean.getVenta();
//		calculo+="\n";
//		calculo+="Descuento : ";
//		calculo+=this.objProductoBean.getDescuento();
//		calculo+="\n";
//		calculo+="Neto de Venta : ";
//		calculo+=this.objProductoBean.getVentaneta();
//		calculo+="\n";
		
		mensaje="El costo del Par de Zapatillas :"+costo+"\n"+
				"La Venta de Zapatillas 		:"+venta+"\n"+
				"El Descuento 					:"+descuento+"\n"+
				"La Venta Neta 					:"+ventaneta;
		
		return mensaje;
	}
	public int CalcularCostoParZapatillas(ProductoBean objProductoBean){
		int costo=0;
		switch (objProductoBean.getMarca()) {
		case 0:
		{
			switch (objProductoBean.getTalla()) {
				case 0:	{
					costo= 150;	
					break;
				}	
				case 1:	{
					costo= 160;	
					break;
				}	
				case 2:	{
					costo= 160;	
					break;
				}	
			}
			break;
		}
		case 1:
		{
			switch (objProductoBean.getTalla()) {
				case 0:	{
					costo= 140;	
					break;
				}	
				case 1:	{
					costo= 150;	
					break;
				}	
				case 2:	{
					costo= 150;	
					break;
				}	
			}
			break;
		}
		case 2:
		{
			switch (objProductoBean.getTalla()) {
				case 0:	{
					costo= 80;	
					break;
				}	
				case 1:	{
					costo= 85;	
					break;
				}	
				case 2:	{
					costo= 90;	
					break;
				}	
			}
			break;
		}
		
		}
		return costo;
	}
	public int CalcularVenta(ProductoBean objProductoBean){
		int venta=0;
		venta=objProductoBean.getCosto()*objProductoBean.getNumpares();
		return venta;
	}
	public double CalcularDescuento(ProductoBean objProductoBean){
		double descuento=0;
		if(objProductoBean.getNumpares()>=2 && objProductoBean.getNumpares()<=5)
		{
			descuento=0.05*objProductoBean.getVenta();
		}
		else 
		{
			if(objProductoBean.getNumpares()>=6 && objProductoBean.getNumpares()<=10)
			{
				descuento=0.08*objProductoBean.getVenta();
			}
			else 
			{
				if(objProductoBean.getNumpares()>=11 && objProductoBean.getNumpares()<=20)
				{
					descuento=0.10*objProductoBean.getVenta();
				}
				else
				{ 
					if(objProductoBean.getNumpares()>20)
					{
						descuento=0.15*objProductoBean.getVenta();
					}
				}
			}
		}
		return descuento;
	}
	public double CalcularVentaNeta(ProductoBean objProductoBean){
		double ventaneta=0;
		ventaneta=objProductoBean.getVenta()-objProductoBean.getDescuento();
		return ventaneta;
	}
}
