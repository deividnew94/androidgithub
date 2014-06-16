package com.pe.sise.trackingtaxi.util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class Gps {
	
	public static Location currentBestLocation;
	private static LocationManager lm;
	private static boolean gps_enabled = false;
	private static boolean network_enabled = false;
	public static int TEN_SECONDS = 10000;
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
	public static String mensaje;
	private static final int TWO_MINUTES = 1000 * 60 * 2;
	// flag for GPS status
	private static boolean canGetLocation = false;
	private static LocationResult locationResult;


	public static boolean isCanGetLocation() {
		return canGetLocation;
	}


	public static void setCanGetLocation(boolean canGetLocation) {
		Gps.canGetLocation = canGetLocation;
	}

	public static void setLocationResult(LocationResult poResult){
		locationResult = poResult;
	}	

	/**
	 * Inicia el location listener y obtiene la mejor ubicacion que no pase de
	 * los 2 minutos de antiguedad
	 * 
	 * @param context
	 * @return retorna true si se logra registrar por lo menos un proveedor, de lo contrario, falso
	 */
	public static boolean startListening(Context context) {
		// I use LocationResult callback class to pass location value from
		// MyLocation to user code.
		if (lm == null)
			lm = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
		System.out.println(lm.getAllProviders().toString());

		// exceptions will be thrown if provider is not permitted.
		try {
			gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}
		try {
			network_enabled = lm
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
		}

		// don't start listeners if no provider is enabled
		if (!gps_enabled && !network_enabled){
			setCanGetLocation(false);
			return false;
		}
		if (gps_enabled) {
			setCanGetLocation(true);
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, TEN_SECONDS,
					MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
			System.out.println("GPS Registered");
			mensaje = "GPS Registered";
		
			
		}
		if (network_enabled) {
			setCanGetLocation(true);
			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
					TEN_SECONDS, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
			System.out.println("Network Registered");
			mensaje = "Network Registered";
		}
		

		return true;
	}
	
	/**
	 * Inicia el location listener y obtiene la mejor ubicacion que no pase de
	 * los 2 minutos de antiguedad
	 * @param context
	 * @param poLocationResult Callback para cuando se ha encontrado una coordenada valida.
	 * @return retorna true si se logra registrar por lo menos un proveedor, de lo contrario, falso
	 */
	public static boolean startListening(Context context, LocationResult poLocationResult){
		
		setLocationResult(poLocationResult);
		
		return Gps.startListening(context);		
	}

	/**
	 * @return retorna la mejor ubicacion obtenida dentro de los 2 minutos o la ultima ubicacion conocida.
	 */
	public static Location getLocation() {
		Location gps, net;
		
		if (currentBestLocation == null) {
			gps = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			net = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (gps_enabled & network_enabled) {
				if (isBetterLocation(gps, net)) 
				{
					currentBestLocation = gps;
					mensaje = "GPS Registered";
				} else 
				{
					currentBestLocation = net;
					mensaje = "Network Registered";
				}
			} else if (gps_enabled) {
				mensaje = "GPS Registered";
				currentBestLocation = gps;
			} else if (network_enabled) {
				mensaje = "Network Registered";
				currentBestLocation = net;
			}
		}
		return currentBestLocation;
	}

	public static String getMensaje()
	{
		return mensaje;
	}
	
	/**
	 * Detiene los listeners
	 */
	public static void stopListening() {
		try {
			lm.removeUpdates(locationListener);
			locationResult = null;
		} catch (NullPointerException e) {
			
		}
	}

	/**
	 * @return un string que indica el estado del GPS y red
	 */
	public static String getLocationState() {
		StringBuilder s = new StringBuilder("network: ");
		if (network_enabled) {
			s.append("ON");
		} else {
			s.append("OFF");
		}
		s.append("\ngps: ");
		if (gps_enabled) {
			s.append("ON");
		} else {
			s.append("OFF");
		}
		return s.toString();
	}

	public static LocationListener locationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onLocationChanged(Location location) {
			Log.d("Location", "Location Received: " + location.getLatitude() + "; " + location.getLongitude());
			if (isBetterLocation(location, currentBestLocation)) {
				currentBestLocation = location;
			}
			if (locationResult != null){
				locationResult.gotLocation(getLocation());
			}
		}
	};
	
	/**
	 * Metodo que me muestra un alert para configurar el GPS
	 * en caso Ã©ste, estÃ© desactivado.
	 */
	public static void showSettingsAlert(final Context pContext, int tipo) {
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(pContext);

		alertDialog.setCancelable(false);
		
		switch (tipo) {
		case 0:
			// Setting Dialog Title
			alertDialog.setTitle("Configuración del GPS");

			// Setting Dialog Message
			alertDialog
					.setMessage("GPS esta desactivado, ¿Desea ir al menú de configuración?");

			// On pressing Settings button
			alertDialog.setPositiveButton("Configuración",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							pContext.startActivity(intent);
						}
					});
			break;
		case 1:
			// Setting Dialog Title
			alertDialog.setTitle("Configuración del GPS y Internet");

			// Setting Dialog Message
			alertDialog
					.setMessage("GPS esta desactivado y Internet tambien, ¿Desea ir a sus menús de configuración?");

			// On pressing Settings button
			alertDialog.setPositiveButton("Configuración GPS",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							pContext.startActivity(intent);
						}
					});
			
			// On pressing Settings button
			alertDialog.setNeutralButton("Configuración WIFI",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Settings.ACTION_WIFI_SETTINGS);
							pContext.startActivity(intent);
						}
					});
			// On pressing Settings button
			alertDialog.setNegativeButton("Configuración Datos",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Settings.ACTION_DATA_ROAMING_SETTINGS);
							pContext.startActivity(intent);
						}
					});
			break;
		case 2:
			// Setting Dialog Title
			alertDialog.setTitle("Configuración del Internet");

			// Setting Dialog Message
			alertDialog
					.setMessage("Internet esta desactivado, ¿Desea ir a su menú de configuración?");

			// On pressing Settings button
			alertDialog.setNeutralButton("Configuración WIFI",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Settings.ACTION_WIFI_SETTINGS);
							pContext.startActivity(intent);
						}
					});
			// On pressing Settings button
			alertDialog.setNegativeButton("Configuración Datos",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Settings.ACTION_DATA_ROAMING_SETTINGS);
							pContext.startActivity(intent);
						}
					});
			break;
		default:
			break;
		}
		
		

		// on pressing cancel button
//		alertDialog.setNegativeButton("Cancelar",
//				new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.cancel();
//					}
//				});

		// Showing Alert Message
		alertDialog.show();
	}	

	

	/**
	 * Determines whether one Location reading is better than the current
	 * Location fix
	 * 
	 * @param location
	 *            The new Location that you want to evaluate
	 * @param currentBestLocation
	 *            The current Location fix, to which you want to compare the new
	 *            one
	 */
	protected static boolean isBetterLocation(Location location,
			Location currentBestLocation) {
		if (currentBestLocation == null) {
			// A new location is always better than no location
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use
		// the new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be
			// worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation
				.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(),
				currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and
		// accuracy
		if (isMoreAccurate) {
			return true;
		} else if (isNewer && !isLessAccurate) {
			return true;
		} else if (isNewer && !isSignificantlyLessAccurate
				&& isFromSameProvider) {
			return true;
		}
		return false;
	}

	/** Checks whether two providers are the same */
	private static boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}

	public static abstract class LocationResult {
		public abstract void gotLocation(Location location);
	}
}
