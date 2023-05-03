package com.example.bottomnavigation;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.FragmentTwoBinding;

public class FragmentTwo extends Fragment {

    private @NonNull FragmentTwoBinding binding;
    private View root;
    private DataClass dataClass;
    private TextView progressText;


    // This function is called when this fragment's view is first created
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTwoBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        progressText = root.findViewById(R.id.taskCompleteText);


        setupScreen();
        return root;
    }

    // This function is called when this fragment's view is destroyed
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        root = null;
    }

    void setupScreen() {
        this.dataClass = DataClass.get_instance();
        System.out.println(root.toString());
        // Since we're now in a Fragment and not an Activity, we can't just call findViewById()
        // directly. Instead, need to call root.findViewById() to to the connect XML views.
        if (progressText == null){
            System.out.println(root.findViewById(R.id.taskCompleteText));
        }
        ProgressBar progressBar = root.findViewById(R.id.taskCompleteBar);
        progressText.setText("Tasks - " + dataClass.tasksCompleted.toString() + "/4 Completed");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(dataClass.tasksCompleted, true);
        }else{
            progressBar.setProgress(dataClass.tasksCompleted);
        }

        // We can set up button onClickListeners the same way as before, the only difference
        // is we need to use root.finViewById() since we're in a Fragment, not an Activity
        Button button = root.findViewById(R.id.taskCompleteBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressBar progressBar = root.findViewById(R.id.taskCompleteBar);
                Context context = getActivity();

                Integer tasksCompleted = dataClass.tasksCompleted;
                if (tasksCompleted < 4){
                    tasksCompleted ++;
                }
                progressText.setText("Tasks - " + tasksCompleted.toString() + "/4 Completed");
                progressBar.setProgress(tasksCompleted);
                dataClass.tasksCompleted = tasksCompleted;


            }
        });
    }
}