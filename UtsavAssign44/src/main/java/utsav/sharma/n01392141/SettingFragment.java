package utsav.sharma.n01392141;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utsav.sharma.n01392141.ui.home.HomeFrag;
import utsav.sharma.n01392141.ui.home.HomeViewModel;
import utsav.sharma.n01392141.ui.slideshow.SlideshowViewModel;


public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    View view;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Fragment fragment = (Fragment) getFragmentManager().findFragmentById(R.id.nav_setting);
        button = view.findViewById(R.id.utsav_btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundResource(R.color.red);
            }
        });

        button= view.findViewById(R.id.utsav_btn2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundResource(R.color.purple);
            }
        });

        button= view.findViewById(R.id.utsav_btn3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setBackgroundResource(R.color.gray);
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    public void goRed(){
        view.setBackgroundResource(R.color.red);
    }
    public void goPurple(){
        view.setBackgroundResource(R.color.purple);
    }

    public void goGray(){
        view.setBackgroundResource(R.color.gray);
    }
}