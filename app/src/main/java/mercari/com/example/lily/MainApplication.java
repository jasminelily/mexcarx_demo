package mercari.com.example.lily;


import android.app.Application;

public class MainApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();

	}

	@Override
	public void onTerminate() {

		super.onTerminate();
	}

	public void handleUncaughtException(Thread thread, Throwable e) {
		e.printStackTrace();

	}


}
