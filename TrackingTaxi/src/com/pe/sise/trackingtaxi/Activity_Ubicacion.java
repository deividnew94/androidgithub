package com.pe.sise.trackingtaxi;

import static com.pe.sise.trackingtaxi.push.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.pe.sise.trackingtaxi.push.CommonUtilities.EXTRA_MESSAGE;

import com.pe.sise.trackingtaxi.library.MarkerJSONParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pe.sise.trackingtaxi.library.DatabaseHandler;
import com.pe.sise.trackingtaxi.library.UserFunctions;
import com.pe.sise.trackingtaxi.push.WakeLocker;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class Activity_Ubicacion extends FragmentActivity {

	protected static final LatLng LIMA = new LatLng(-12.044425642104926,
			-77.04471575634764);

	private GoogleMap mapa;
	private int vista = 0;
	
	Context context;
	
	Polyline line;
	LatLng startLatLng;

	Vector<Double> vectorList = new Vector<Double>();
    
    ArrayList<LatLng> pointList = new ArrayList<LatLng>();

    Marker mark;
    
    static int ruta = 1;
    static int cont = 0; 
    
    double lat;
    double lng;
    
    DatabaseHandler db;
    /**
     * Hashmap to load data from the Sqlite database
     **/
     HashMap<String,String> user;
    
    /*final Handler mHandler =new Handler();
    
    protected void miHilo(){
    	Thread t= new Thread(){
    		public void run(){
    			try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
    			mHandler.post(ejecutarAccion);
    		}
    	};
    	t.start();
    }
    
    final Runnable ejecutarAccion = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Toast.makeText(Activity_Ubicacion.this, "Hola", Toast.LENGTH_SHORT).show();
		}
	};*/
     
     //TextView ejecuciones=null;
     
     /*public void onDestroy()
     {
         super.onDestroy();
  
         try
         {
             // Finalizamos el servicio
             Log.i(getClass().getSimpleName(), "Finalizando el servicio desde la actividad...");
  
             // Cogemos el intent el servicio
             Intent servicio = new Intent(this, Activity_Ubicacion.class);
  
             // Lo ejecutamos
             if(stopService(servicio))
             {
                 this.notificar("Servicio finalizado correctamente");
             }
             else
             {
                 this.notificar("No se ha podido finalizar el servicio");
             }
  
             // Salimos
             this.finalize();
         }
         catch (Throwable e)
         {
             this.notificar(e.getMessage());
         }
     }
  
     private void notificar(String cadena)
     {
         // Notificamos con un toast
         Context contexto = getApplicationContext();
         CharSequence texto = cadena;
         int duracion = Toast.LENGTH_SHORT;
         Toast toast = Toast.makeText(contexto, texto, duracion);
         toast.show();
     } */
     

     /**
 	 * Receiving push messages
 	 * */
 	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
 		@Override
 		public void onReceive(Context context, Intent intent) {
 			String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
 			// Waking up mobile if it is sleeping
 			WakeLocker.acquire(getApplicationContext());
 			
 			/**
 			 * Take appropriate action on this message
 			 * depending upon your app requirement
 			 * For now i am just displaying it on the screen
 			 * */
 			
 			// Showing received message
 			//lblMessage.append(newMessage + "\n");			
 			Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
 			
 			// Releasing wake lock
 			WakeLocker.release();
 		}
 	};
 	
 	@Override
 	protected void onDestroy() {
 		try {
 			unregisterReceiver(mHandleMessageReceiver);
 			GCMRegistrar.onDestroy(this);
 		} catch (Exception e) {
 			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
 		}
 		super.onDestroy();
 	}
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    
    
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		cont=0;
		ruta=1;
		Log.v("OK","REINICIO");
	}
	
	private void bucarrutaproxima(){
		vectorList.clear();
        
		for (int z = 0; z < pointList.size(); z++) {
            LatLng punto = pointList.get(z);
            
            vectorList.add(distFrom(startLatLng.latitude,
                    startLatLng.longitude, punto.latitude,
                    punto.longitude)); 
            
        }
		int j=0;
        for (int i = 0; i < vectorList.size(); i++) {
          	if (vectorList.get(i) < vectorList.get(0)) {
          		
	            j=i;
	            
	            //Log.v("OK","Veces ha pasado");
          	}
        }
        
        if (pointList.size()>0){
        	LatLng punto2 = pointList.get(j);
            String urlTopass = makeURL( punto2.latitude,
                    punto2.longitude, startLatLng.latitude,
                    startLatLng.longitude);
            new connectAsyncTask(urlTopass).execute();
            
            ruta=0;
            
        } else{
        	Toast.makeText(Activity_Ubicacion.this,"Marque en un punto del mapa para localizar un taxi", Toast.LENGTH_SHORT).show();
        }
	}
	// Invoking background thread to store the touched location in Remove MySQL server
		private void sendToServer(LatLng latlng) {
			new SaveTask().execute(latlng);
		}

		
		// Background thread to save the location in remove MySQL server
		private class SaveTask extends AsyncTask<LatLng, Void, Void> {
			@Override
			protected Void doInBackground(LatLng... params) {
				String lat = Double.toString(params[0].latitude);
				String lng = Double.toString(params[0].longitude);
				String strUrl = "http://misitiodemostracion.site90.net/location_marker_mysql/save.php";					
				URL url = null;
				try {
					url = new URL(strUrl);

					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					connection.setRequestMethod("POST");
					connection.setDoOutput(true);
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
							connection.getOutputStream());

					outputStreamWriter.write("lat=" + lat + "&lng="+lng);				
					outputStreamWriter.flush();
					outputStreamWriter.close();
					
					InputStream iStream = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new
					InputStreamReader(iStream));
					
					StringBuffer sb = new StringBuffer();
					
					String line = "";
					
					while( (line = reader.readLine()) != null){
						sb.append(line);
					}

					reader.close();
					iStream.close();
								

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

		}
		
		// Background task to retrieve locations from remote mysql server
		private class RetrieveTask extends AsyncTask<Void, Void, String>{

			@Override
			protected String doInBackground(Void... params) {
				String strUrl = "http://misitiodemostracion.site90.net/location_marker_mysql/retrieve.php";				
				URL url = null;
				StringBuffer sb = new StringBuffer();
				try {
					url = new URL(strUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.connect();
					InputStream iStream = connection.getInputStream();				
					BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));			
					String line = "";				
					while( (line = reader.readLine()) != null){
						sb.append(line);
					}

					reader.close();
					iStream.close();							

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}		
				return sb.toString();
			}
			
			@Override
			protected void onPostExecute(String result) {			
				super.onPostExecute(result);
				new ParserTask().execute(result);
			}
			
		}
		
		// Background thread to parse the JSON data retrieved from MySQL server
		private class ParserTask extends AsyncTask<String, Void, List<HashMap<String, String>>>{
			@Override
			protected List<HashMap<String,String>> doInBackground(String... params) {
				MarkerJSONParser markerParser = new MarkerJSONParser();
				JSONObject json = null;
				try {
					json = new JSONObject(params[0]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				List<HashMap<String, String>> markersList = markerParser.parse(json);
				return markersList;
			}
			
			@Override
			protected void onPostExecute(List<HashMap<String, String>> result) {
				for(int i=0; i<result.size();i++){
					HashMap<String, String> marker = result.get(i);
					LatLng latlng = new LatLng(Double.parseDouble(marker.get("lat")), Double.parseDouble(marker.get("lng")));
					mostrarMarcador(latlng);
				}
			}
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_ubicacion);
		
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		// Getting Google Play availability status
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		
		// Showing status
	    if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

	        int requestCode = 10;
	        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
	        dialog.show();

	    } else { // Google Play Services are available        	

	        // Getting reference to the SupportMapFragment of activity_main.xml
	        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

	        // Getting GoogleMap object from the fragment
	        mapa = fm.getMap();
	        
	       try {
	    	   db = new DatabaseHandler(getApplicationContext());
	    	   user = new HashMap<String, String>();
		       user = db.getUserDetails();
	       } catch (Exception e) {
	    	   // TODO: handle exception
	    	   e.printStackTrace();
	       }
	         
	        
	        // Enabling MyLocation Layer of Google Map
	        mapa.setOnMyLocationChangeListener(new OnMyLocationChangeListener() {
				
				public void onMyLocationChange(Location arg0) {
					// TODO Auto-generated method stub
					
					try {
						
						if (mark != null) {
							mark.remove();
				        }
						
						startLatLng = new LatLng(arg0.getLatitude(),arg0.getLongitude());
					    
						mark = mapa.addMarker(new MarkerOptions().position(startLatLng).title( user.get("fname")+" "+user.get("lname")).icon(
				                BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
						
						mark.showInfoWindow();
						
						mark.isInfoWindowShown();
						
						mark.setFlat(true);
						
						lat=arg0.getLatitude();
						lng=arg0.getLongitude();
						

						
						if(cont<1){

							mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 15));
							cont++;
						}
						
						Log.v("OK",""+cont);
					} catch (Exception e) {
						Log.v("OK","ubicacion no encontrada");
						
					}
					
				}
			});
	        
			mapa.setOnMapClickListener(new OnMapClickListener() {
				public void onMapClick(LatLng point) {
//					Projection proj = mapa.getProjection();
//					Point coord = proj.toScreenLocation(point);
					
					/*Toast.makeText(
							Activity_Ubicacion.this, 
							"Click\n" + 
							"Lat: " + point.latitude + "\n" +
							"Lng: " + point.longitude + "\n" +
							"X: " + coord.x + " - Y: " + coord.y,
							Toast.LENGTH_SHORT).show();*/
					

					//sendToServer(startLatLng);
					// Adding the currently created marker position to the arraylist
			    	pointList.add(point);
					
					mostrarMarcador(point);
					
					Log.v("OK","Lat: " + point.latitude + "\n" + "Lng: " + point.longitude + "\n" );
					}
			});
			
			mapa.setOnMapLongClickListener(new OnMapLongClickListener() {
				public void onMapLongClick(LatLng point) {
//					Projection proj = mapa.getProjection();
//					Point coord = proj.toScreenLocation(point);
					
					/*Toast.makeText(
							Activity_Ubicacion.this, 
							"Click Largo\n" + 
							"Lat: " + point.latitude + "\n" +
							"Lng: " + point.longitude + "\n" +
							"X: " + coord.x + " - Y: " + coord.y,
							Toast.LENGTH_SHORT).show();*/
					
					try {
						
						bucarrutaproxima();
						
					} catch (Exception e) {
						// TODO: handle exception
						//Toast.makeText(Activity_Ubicacion.this,"SIZE LISTA: "+pointList.get(pointList.size()-1), Toast.LENGTH_LONG).show();
	        	    }
					
				}
			});
			
			mapa.setOnCameraChangeListener(new OnCameraChangeListener() {
				public void onCameraChange(CameraPosition position) {
					
					/*if (position!=null){
						try {
							mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 15));
						} catch (Exception e) {
							// TODO: handle exception
						}
					}*/
					/*Toast.makeText(
							Activity_Ubicacion.this, 
							"Cambio Cámara\n" + 
							"Lat: " + position.target.latitude + "\n" +
							"Lng: " + position.target.longitude + "\n" +
							"Zoom: " + position.zoom + "\n" +
							"Orientación: " + position.bearing + "\n" +
							"Ángulo: " + position.tilt,
							Toast.LENGTH_SHORT).show();*/
				}
			});
			
			mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
				public boolean onMarkerClick(Marker marker) {
					/*Toast.makeText(
							Activity_Ubicacion.this, 
							"Marcador pulsado:\n" + 
							marker.getTitle(),
							Toast.LENGTH_SHORT).show();*/
					return false;
				}
			});
			
			mapa.setMyLocationEnabled(true); // false to disable
			mapa.getUiSettings().setZoomControlsEnabled(true); // true to enable
			mapa.getUiSettings().setZoomGesturesEnabled(true);
			mapa.getUiSettings().setCompassEnabled(true);
			mapa.getUiSettings().setMyLocationButtonEnabled(true);
			mapa.getUiSettings().setScrollGesturesEnabled(true);
			mapa.getUiSettings().setTiltGesturesEnabled(true);
			mapa.getUiSettings().setAllGesturesEnabled(true);
			mapa.getUiSettings().setRotateGesturesEnabled(true);
			
	        
			context=Activity_Ubicacion.this;
			
	        // Restoring the markers on configuration changes
	        if(savedInstanceState!=null){
	        	if(savedInstanceState.containsKey("points") ){
	            	pointList = savedInstanceState.getParcelableArrayList("points");
	            	lat = savedInstanceState.getDouble("lat");
	            	lng = savedInstanceState.getDouble("lng");
	            	//ruta = savedInstanceState.getBoolean("ruta");
	            	if(pointList!=null){
	            		for(int i=0;i<pointList.size();i++){
	            			mostrarMarcador(pointList.get(i));
	            		}
	            		
	            		try {
	            			
            				startLatLng=new LatLng(lat, lng);
	            			
	            			if(ruta<1){
	            			
	            				bucarrutaproxima();

	            			}
						} catch (Exception e) {
							// TODO: handle exception
						}
	            		
	            	}
	        	}
	        }       
	        
			
		    /*try {
				if (marker != null) {
					marker.remove();
		        }
					
			    startLatLng2 = new LatLng(Gps.getLocation().getLatitude(),Gps.getLocation().getLongitude());

				mapa.moveCamera(CameraUpdateFactory.newLatLng(startLatLng2));
				mapa.animateCamera(CameraUpdateFactory.zoomTo(15));

				marker = mapa.addMarker(new MarkerOptions().position(startLatLng2).title("MI UBICACION"));
				
//				LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
////				Location location2 = service.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//				Criteria criteria = new Criteria();
//			    String provider = service.getBestProvider(criteria, false);
//			    Location location = service.getLastKnownLocation(provider);
//			    LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
//			    mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
//					Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
//					  intent.putExtra("enabled", true);
//					  sendBroadcast(intent);
					
//			    startLatLng = new LatLng(Gps.getLocation().getLatitude(),Gps.getLocation().getLongitude());
//			    mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 15));
				} catch (Exception e) {
					startLatLng2 = LIMA;
					Toast.makeText(Activity_Ubicacion.this, 
							"No se pudo rastrear su ubicación, ACTIVE SU GPS Y WIFI", 
							Toast.LENGTH_LONG).show();
					mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng2, 15));
//				    // TODO: handle exception
					marker = mapa.addMarker(new MarkerOptions().position(startLatLng2).title("MI UBICACION"));
					
				}*/
			}
		//miHilo();

        /*// Asociamos los controles
        //this.ejecuciones=(TextView)findViewById(R.id.textView);
 
        // Establecemos la actividad principal para el servicio
        TestServicio.ACTIVIDAD=this;
 
        // Iniciamos el servicio
        try
        {
            Log.i(getClass().getSimpleName(), "Iniciando servicio desde la actividad...");
 
            // Cogemos el intent el servicio
            Intent servicio = new Intent(this, TestServicio.class);
 
            // Lo ejecutamos
            if(startService(servicio)==null)
            {
                this.notificar("No se ha podido iniciar el servicio");
            }
            else
            {
                this.notificar("Servicio iniciado correctamente");
            }
        }
        catch(Exception e)
        {
            this.notificar(e.getMessage());
        }*/
	    
 		registerReceiver(mHandleMessageReceiver, new IntentFilter(
				DISPLAY_MESSAGE_ACTION));
 		
 	// Starting locations retrieve task
 		//new RetrieveTask().execute();
	}
	
	// A callback method, which is invoked on configuration is changed
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Adding the pointList arraylist to Bundle
		outState.putParcelableArrayList("points", pointList);
		outState.putDouble("lat", lat);
		outState.putDouble("lng", lng);
		//outState.putBoolean("ruta", ruta);
		// Saving the bundle
		super.onSaveInstanceState(outState);
	}
	
	public static double distFrom(double lat1, double lng1, double lat2, double lng2) {  
        //double earthRadius = 3958.75;//miles  
        double earthRadius = 6371;//kilometers  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));  
        double dist = earthRadius * c;  
  
        return dist;  
    }  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{	
		switch(item.getItemId())
		{
			case R.id.menu_vista:
				alternarVista();
				break;
			case R.id.menu_mover:
				//Centramos el mapa en España
				CameraUpdate camUpd1 = 
					CameraUpdateFactory.newLatLng(new LatLng(startLatLng.latitude,
    	                    startLatLng.longitude));
				mapa.moveCamera(camUpd1);
				break;
			case R.id.menu_animar:
				//Centramos el mapa en España y con nivel de zoom 5
				CameraUpdate camUpd2 = 
					CameraUpdateFactory.newLatLngZoom(new LatLng(startLatLng.latitude,
    	                    startLatLng.longitude), 5F);
				mapa.animateCamera(camUpd2);
				break;
			case R.id.menu_3d:
				LatLng madrid = new LatLng(startLatLng.latitude,
	                    startLatLng.longitude);
				CameraPosition camPos = new CameraPosition.Builder()
					    .target(madrid)   //Centramos el mapa en Madrid
					    .zoom(19)         //Establecemos el zoom en 19
					    .bearing(45)      //Establecemos la orientación con el noreste arriba
					    .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
					    .build();
				
				CameraUpdate camUpd3 = 
						CameraUpdateFactory.newCameraPosition(camPos);
				
				mapa.animateCamera(camUpd3);
				break;
			case R.id.menu_posicion:
				CameraPosition camPos2 = mapa.getCameraPosition();
				LatLng pos = camPos2.target;
				Toast.makeText(Activity_Ubicacion.this, 
						"Lat: " + pos.latitude + " - Lng: " + pos.longitude, 
						Toast.LENGTH_LONG).show();
				break;
			case R.id.menu_changepass:
				Intent chgpass = new Intent(getApplicationContext(), Activity_OlvideMiPassword.class);
				startActivity(chgpass);
				break;
			case R.id.menu_logout:
					UserFunctions logout = new UserFunctions();
	                logout.logoutUser(getApplicationContext());
	                Intent login = new Intent(getApplicationContext(), Activity_Login.class);
	                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(login);
	                finish();
				break;
			case R.id.menu_push:
                Intent web = new Intent(getApplicationContext(), ActivityWeb.class);
                web .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(web );
                //finish();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void alternarVista()
	{
		vista = (vista + 1) % 4;
		
		switch(vista)
		{
			case 0:
				mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				break;
			case 1:
				mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				break;
			case 2:
				mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
				break;
			case 3:
				mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
				break;
		}
	}
	
	private void mostrarMarcador(LatLng point)
	{
		mapa.addMarker(new MarkerOptions()
        .position(point)
        .title("Latitud: "+point.latitude+", Longitud: "+point.longitude).icon(BitmapDescriptorFactory.fromResource(R.drawable.taxistas)));

	}
	
//	private void mostrarLineas()
//	{
		//Dibujo con Lineas
		
//		PolylineOptions lineas = new PolylineOptions()
//	        .add(new LatLng(45.0, -12.0))
//	        .add(new LatLng(45.0, 5.0))
//	        .add(new LatLng(34.5, 5.0))
//	        .add(new LatLng(34.5, -12.0))
//	        .add(new LatLng(45.0, -12.0));
//
//		lineas.width(8);
//		lineas.color(Color.RED);
//
//		mapa.addPolyline(lineas);
		
		//Dibujo con polígonos
		
//		PolygonOptions rectangulo = new PolygonOptions()
//		              .add(new LatLng(45.0, -12.0),
//		            	   new LatLng(45.0, 5.0),
//		            	   new LatLng(34.5, 5.0),
//		            	   new LatLng(34.5, -12.0),
//		            	   new LatLng(45.0, -12.0));
//		
//		rectangulo.strokeWidth(8);
//		rectangulo.strokeColor(Color.RED);
//		
//		mapa.addPolygon(rectangulo);
//		
//	}

//    private void drawPolilyne(PolylineOptions options){
//    Polyline polyline = mapa.addPolyline(options);	
//    }
	
    private class connectAsyncTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog progressDialog;
        String url;

        connectAsyncTask(String urlPass) {
            url = urlPass;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Buscando Taxi Cercano, Espere por favor...");
            progressDialog.setIndeterminate(true);
            progressDialog.setProgress(100);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();
            if (result != null) {
                drawPath(result);
            }
        }
    }

    public String makeURL(double sourcelat, double sourcelog, double destlat,
            double destlog) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("http://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString.append(Double.toString(sourcelog));
        urlString.append("&destination=");// to
        urlString.append(Double.toString(destlat));
        urlString.append(",");
        urlString.append(Double.toString(destlog));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        return urlString.toString();
    }

    public class JSONParser {

        InputStream is = null;
        JSONObject jObj = null;
        String json = "";

        // constructor
        public JSONParser() {
        }

        public String getJSONFromUrl(String url) {

            // Making HTTP request
            try {
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                json = sb.toString();
                is.close();
            } catch (Exception e) {
                Log.e("Buffer Error", "Error converting result " + e.toString());
            }
            return json;

        }
    }

    public void drawPath(String result) {
        if (line != null) {
        	line.remove();
        }
        //mapa.addMarker(new MarkerOptions().position(endLatLng).icon(
        //        BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
//        mapa.addMarker(new MarkerOptions().position(startLatLng).icon(
//                BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
        try {
            // Tranform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes
                    .getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);

//            for (int z = 0; z < list.size() - 1; z++) {
//                LatLng src = list.get(z);
//                LatLng dest = list.get(z + 1);
//                line = mapa.addPolyline(new PolylineOptions()
//                        .add(new LatLng(src.latitude, src.longitude),
//                                new LatLng(dest.latitude, dest.longitude))
//                        .width(5).color(Color.BLUE).geodesic(true));
                PolylineOptions options = new PolylineOptions().width(5).color(Color.GREEN).geodesic(true);
                for (int z = 0; z < list.size(); z++) {
                    LatLng point = list.get(z);
                    options.add(point);
                }
                line = mapa.addPolyline(options);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}
