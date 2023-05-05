package com.example.bottomnavigation;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
        ProgressBar progressBar = root.findViewById(R.id.taskCompleteBar);
        progressText.setText("Tasks - " + dataClass.tasksCompleted.toString() + "/4 Completed");
        progressBar.setProgress(dataClass.tasksCompleted);

        // We can set up button onClickListeners the same way as before, the only difference
        // is we need to use root.finViewById() since we're in a Fragment, not an Activity
        Button buttonTask1 = root.findViewById(R.id.buttonTask1);
        buttonTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeTask(root.findViewById(R.id.checkTask1),1, root.findViewById(R.id.bgTask1));
            }
        });
        Button buttonTask2 = root.findViewById(R.id.buttonTask2);
        buttonTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeTask(root.findViewById(R.id.checkTask2),2, root.findViewById(R.id.bgTask2));
            }
        });
        Button buttonTask3 = root.findViewById(R.id.buttonTask3);
        buttonTask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeTask(root.findViewById(R.id.checkTask3),3, root.findViewById(R.id.bgTask3));
            }
        });
        Button buttonTask4 = root.findViewById(R.id.buttonTask4);
        buttonTask4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeTask(root.findViewById(R.id.checkTask4),4, root.findViewById(R.id.bgTask4));
            }
        });
    }
    private void completeTask(ImageView checkBox, int taskNum, View bgView ){
        if (!dataClass.completedTasks.get(taskNum - 1)){
            dataClass.completedTasks.set(taskNum - 1, true);
            dataClass.NumTokens += 20;
            System.out.println(dataClass.NumTokens);
            ProgressBar progressBar = root.findViewById(R.id.taskCompleteBar);
            checkBox.setImageResource(android.R.drawable.checkbox_on_background);
            bgView.setBackgroundColor(0xFFC7FCD9);
            dataClass.tasksCompleted++;
            progressText.setText("Tasks - " + dataClass.tasksCompleted.toString() + "/4 Completed");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBar.setProgress(dataClass.tasksCompleted,true);
            }
            else{
                progressBar.setProgress(dataClass.tasksCompleted);
            }
            boolean bonusTokens = true;
            for (boolean taskDone:
                 dataClass.completedTasks) {
                if (!taskDone){
                    bonusTokens = false;
                }
            }
            if (bonusTokens){
                dataClass.NumTokens += 50;
                System.out.println(dataClass.NumTokens);
            }
        }
    }
}