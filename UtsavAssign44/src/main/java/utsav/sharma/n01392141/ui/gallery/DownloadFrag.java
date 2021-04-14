package utsav.sharma.n01392141.ui.gallery;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import utsav.sharma.n01392141.CustomAdapter;
import utsav.sharma.n01392141.R;

public class DownloadFrag extends Fragment {

    private GalleryViewModel galleryViewModel;
    private int STORAGE_PERMISSION_CODE = 1;
    Spinner nameSpinner;
    ArrayList<String> nameList;
    Button button;
    ImageView imageView;
    Spinner sp1;
    View view;
    Context context;
    CustomAdapter adapter;
    String[] names = {"Flower", "Nature", "Sky"};
    String image_url = "https://wallpapercave.com/wp/s3X06zT.jpg";
    int[] images = {R.drawable.flower, R.drawable.nature, R.drawable.sky};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_download, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        sp1 = view.findViewById(R.id.utsav_spinner);
        adapter = new CustomAdapter(getActivity(), names, images);
        nameList = new ArrayList<>();
        nameList.addAll(Arrays.asList(getResources().getStringArray(R.array.names)));

        sp1.setAdapter(adapter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String name = nameList.get(position);

                switch (name) {
                    case "Flower":
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadTask downloadTask=new DownloadTask();
                                downloadTask.execute("https://hgtvhome.sndimg.com/content/dam/images/hgtv/stock/2018/3/2/shutterstock_anemone-134595248.jpg.rend.hgtvcom.966.644.suffix/1519931799331.jpeg");
                            }

                        });
                        break;

                    case "Nature":
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadTask downloadTask=new DownloadTask();
                                downloadTask.execute("https://wallpapercave.com/wp/s3X06zT.jpg") ;
                            }
                        });
                        break;

                    case "Sky":
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadTask downloadTask=new DownloadTask();
                                downloadTask.execute("https://media.istockphoto.com/photos/sunset-sky-background-picture-id921951792?k=6&m=921951792&s=170667a&w=0&h=0BRzpwx-ecGtqDErJut7rJId4K0JzTWEpgP3YYL_-sM=");
                            }
                        });
                        break;

                    default:


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageView = view.findViewById(R.id.utsav_download_img);

        button = view.findViewById(R.id.utsav_download_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }



        public class DownloadTask extends AsyncTask<String, Integer, String> {

            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Download in progress...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMax(100);
                progressDialog.setProgress(0);
                progressDialog.show();

            }

            @Override
            protected String doInBackground(String... params) {
                String path = params[0];
                int file_length;

                try {
                    URL url = new URL(path);
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();
                    file_length = urlConnection.getContentLength();

                    File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myfolder");
                    if (!new_folder.exists()) {
                        if (new_folder.mkdir()) {

                        } else {

                        }
                    } else {

                    }

                    File output_file = new File(new_folder, "downloaded_image.jpg");
                    OutputStream outputStream = new FileOutputStream(output_file);

                    InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                    byte[] data = new byte[1024];
                    int total = 0;
                    int count;
                    while ((count = inputStream.read(data)) != -1) {
                        total += count;

                        outputStream.write(data, 0, count);
                        int progress = 100 * total / file_length;
                        publishProgress(progress);

                    }
                    inputStream.close();
                    outputStream.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "Download complete.";
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                progressDialog.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(String result) {
                progressDialog.hide();
                Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
                File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myfolder");
                File output_file = new File(folder, "downloaded_image.jpg");
                String path = output_file.toString();
                imageView.setImageDrawable(Drawable.createFromPath(path));
            }
        }



    }

