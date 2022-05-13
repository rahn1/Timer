package com.example.fragmentexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fragmentexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager fm = getSupportFragmentManager();
        CountdownTimerFragment fragment = CountdownTimerFragment.newInstance(5);

        fm.beginTransaction()
                .add(R.id.fragmentContainerView, fragment)
                .commit();
        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (fragment.getState()) {
                    case NOT_STARTED:
                        fragment.start();
                        binding.start.setText("Running...");
                        break;
                    case COMPLETE:
                        fragment.resetTimer();
                        fragment.refreshTimerText();
                        binding.start.setText("Start");
                        break;
                }
                binding.timerDone.setVisibility(View.INVISIBLE);
            }
        });
        fm.setFragmentResultListener(CountdownTimerFragment.TIMER_DONE, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                binding.timerDone.setVisibility(View.VISIBLE);
                binding.start.setText("Reset");
            }
        });
    }
}