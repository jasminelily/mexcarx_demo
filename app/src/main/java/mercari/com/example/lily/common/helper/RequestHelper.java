package mercari.com.example.lily.common.helper;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mercari.com.example.lily.common.constant.Constant;
import mercari.com.example.lily.common.constant.EnumConstant.Request_Status;
import mercari.com.example.lily.common.listener.CCDataListener;
import mercari.com.example.lily.common.listener.CCDataListener.CCDataJsonListListener;
import mercari.com.example.lily.common.listener.CCDataListener.CCDataSingleListener;

/**
 * Created by lily on 4/17/18.
 */

public class RequestHelper {
    private final static Handler mHandler = new Handler(Looper.getMainLooper());
    private final static String CODE_TYPE = "UTF-8";

    public static void requestJson(final String urlString, final CCDataJsonListListener afterListener) {

        downLoad(urlString, new CCDataSingleListener() {

            @Override
            public void onRequstDataComplete(Request_Status status, Object data) {

                try {

                    if (Request_Status.Failure == status) {
                        requestError(afterListener);

                        return;
                    }

                    final JSONArray json = new JSONArray(new JSONTokener((String) data));
                    mHandler.post(new Runnable() {
                        public void run() {
                            afterListener.onRequstDataComplete(Request_Status.Success, json);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    requestError(afterListener);
                }

            }
        });

    }

    private static void downLoad(final String urlString, final CCDataSingleListener complate) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                InputStream inputStream = null;
                HttpURLConnection urlConnection = null;
                try {

                    //connect
                    URL url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    // Read content
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, CODE_TYPE));
                    StringBuilder sBuilder = new StringBuilder();

                    String line = null;
                    while ((line = bReader.readLine()) != null) {
                        sBuilder.append(line);
                    }

                    inputStream.close();
                    final String result = sBuilder.toString();

                    mHandler.post(new Runnable() {
                        public void run() {
                            complate.onRequstDataComplete(Request_Status.Success, result);
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();

                    requestError(complate);
                } finally {
                    // clean up
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    private static void requestError(final CCDataListener afterListener) {
        if (afterListener == null) {
            return;
        }

        mHandler.post(new Runnable() {
            public void run() {
                if (afterListener instanceof CCDataJsonListListener) {
                    ((CCDataJsonListListener) afterListener).onRequstDataComplete(Request_Status.Failure, new JSONArray());
                } else if (afterListener instanceof CCDataSingleListener) {
                    ((CCDataSingleListener) afterListener).onRequstDataComplete(Request_Status.Failure, Constant.STRING_EMPTY);
                }
            }
        });
    }
}
