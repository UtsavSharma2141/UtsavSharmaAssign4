package utsav.sharma.n01392141.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utsav.sharma.n01392141.R;

public class HomeFrag extends Fragment {

    private HomeViewModel homeViewModel;
    TextView textViewDate;
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        Calendar calendar = Calendar.getInstance();
        String currentdate = DateFormat.getDateInstance().format(calendar.getTime());
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        String currentdate = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());
        textViewDate = view.findViewById(R.id.utsavhometv3);
        textViewDate.setText(currentdate);
        textViewDate = view.findViewById(R.id.utsavhometv4);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String datetime = simpleDateFormat.format((calendar.getTime()));
        textViewDate.setText(datetime);
        super.onViewCreated(view, savedInstanceState);
    }

    public void whiteColour(View v) {
        view.setBackgroundResource(R.color.white);

    }
    public void purpleColour(View v) {
        view.setBackgroundResource(R.color.purple_200);

    }
    public void tealColour(View v) {
        view.setBackgroundResource(R.color.teal_200);

    }
}
