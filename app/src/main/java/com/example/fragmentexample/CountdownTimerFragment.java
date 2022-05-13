package com.example.fragmentexample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentexample.databinding.FragmentCountdownTimerBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountdownTimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountdownTimerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public static final String TIMER_TIME = "timer_time";
    public static final String TIMER_DONE = "timer_done";

    // TODO: Rename and change types of parameters
    private int mTime;
    private String mParam2;
    private FragmentCountdownTimerBinding mBinding;
    private CountDownTimer mCountdownTimer;
    private int counter;
    private FragmentState state = FragmentState.NOT_STARTED;

    public enum FragmentState {
        NOT_STARTED,
        RUNNING,
        COMPLETE
    }

    public CountdownTimerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CountdownTimerFragment newInstance(int time) {
        CountdownTimerFragment fragment = new CountdownTimerFragment();
        Bundle args = new Bundle();
        args.putInt(TIMER_TIME, time);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        counter = 0;
        super.onCreate(savedInstanceState);
        resetTimer();

    }

    public void resetTimer() {
        if (getArguments() != null) {
            mTime = getArguments().getInt(TIMER_TIME);
        } else {
            mTime = 5;
        }
        mCountdownTimer = new CountDownTimer(mTime * 1000, 1000) {
            @Override
            public void onTick(long l) {
                incrementCounter();
            }

            @Override
            public void onFinish() {
                incrementCounter();
                getParentFragmentManager().setFragmentResult(TIMER_DONE, null);
                counter = 0;
                state = FragmentState.COMPLETE;
            }
        };
        state = FragmentState.NOT_STARTED;
    }

    // Method to make sure timer lingers at 5 and ends when it hits 0
    public void incrementCounter() {
        counter++;
        mBinding.timerTime.setText(String.valueOf(6 - counter));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCountdownTimerBinding.inflate(getLayoutInflater());
        refreshTimerText();
        return mBinding.getRoot();
    }

    public void refreshTimerText() {
        mBinding.timerTime.setText(String.valueOf(mTime));
    }

    public void start() {
        mCountdownTimer.start();
        state = FragmentState.RUNNING;
    }

    public FragmentState getState() {
        return state;
    }

}