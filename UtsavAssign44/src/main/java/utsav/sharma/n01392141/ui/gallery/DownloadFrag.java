package utsav.sharma.n01392141.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.Arrays;

import utsav.sharma.n01392141.CustomAdapter;
import utsav.sharma.n01392141.R;

public class DownloadFrag extends Fragment {

    private GalleryViewModel galleryViewModel;
    Spinner nameSpinner;
    ArrayList<String> nameList;
    Spinner sp1;
    View view;
    Context context;
    CustomAdapter adapter;
    String[] names = {"Flower","Nature","Sky"};
    int[] images = {R.drawable.flower,R.drawable.nature,R.drawable.sky};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_download, container, false);


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    sp1= view.findViewById(R.id.utsav_spinner);
    adapter = new CustomAdapter(getActivity(),names, images);
        nameList = new ArrayList<>();
        nameList.addAll(Arrays.asList(getResources().getStringArray(R.array.names)));

    sp1.setAdapter(adapter);
    sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });


    }

    }


