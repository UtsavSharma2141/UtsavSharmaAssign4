package utsav.sharma.n01392141.ui.slideshow;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import utsav.sharma.n01392141.R;
import utsav.sharma.n01392141.UtsavActivity;

public class WebServiceFrag extends Fragment {

    private TextView txtDisplayWeather;
    Button button;
    EditText txtZIP;
    View view;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_webservice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        txtDisplayWeather = view.findViewById(R.id.txtDisplayWeather);
        new ReadJSONFeedTask().execute(
                "http://extjs.org.cn/extjs/examples/grid/survey.html");
        txtZIP= view.findViewById(R.id.utsav_zip_text);

        button= view.findViewById(R.id.utsav_webservice_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtZIP.getText().toString().trim().equalsIgnoreCase("")) {
                    txtZIP.setError("This field can not be blank.Please Enter 5 Digit ZIP Code");

                }

                if(((txtZIP.getText().toString().length() == 0) || txtZIP.getText().toString().length() ==1 || txtZIP.getText().toString().length() ==2
                || txtZIP.getText().toString().length() ==3 ||  txtZIP.getText().toString().length() ==4) ){

                  createAlertDialog();

                }

               else  {

                    getWeather();


                }
            }
        });

    }

    private void createAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.error);
        builder.setTitle(getResources().getString(R.string.Warning_msg));
        builder.setMessage(getResources().getString(R.string.dialogMsg));
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
    }

    public void getWeather()
    {


        String zip = txtZIP.getText().toString();

        String url = "https://api.openweathermap.org/data/2.5/weather?";
        url+="zip="+zip;
        url+="&&units=metric&appid=f8bdf1b6771866d52c93f17b99c03b05";
        Log.d("URL",url);
        new ReadJSONFeedTask().execute(url);


    }


    public String readJSONFeed(String address) {
        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        };
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream content = new BufferedInputStream(
                    urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return stringBuilder.toString();
    }
    private class ReadJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }
        protected void onPostExecute(String result) {
            try {

                JSONObject weatherJson = new JSONObject(result);
                JSONArray dataArray1= weatherJson.getJSONArray("weather");
                String strResults="Weather\n";
                for (int i = 0; i < dataArray1.length(); i++) {
                    JSONObject jsonObject = dataArray1.getJSONObject(i);
                    strResults +="\nDescription: "+jsonObject.getString("description");
                }
                //
                JSONObject dataObject= weatherJson.getJSONObject("main");
                strResults +="\nTemperature: "+dataObject.getString("temp");
                strResults +="\nHumidity: "+dataObject.getString("humidity");

                //
                JSONObject dataObject2= weatherJson.getJSONObject("coord");
                strResults +="\nLongitude: "+dataObject2.getString("lon");
                strResults +="\nlatitude: "+dataObject2.getString("lat");
                //


                JSONObject dataObject3= weatherJson.getJSONObject("sys");
                strResults +="\nCountry: "+dataObject3.getString("country");


                txtDisplayWeather.setText(strResults);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}