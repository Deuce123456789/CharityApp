package com.example.bottomnavigation;

import android.graphics.Color;
import android.graphics.PorterDuff;
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
        //Find the text, sets the variable to the text
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
        //Find the instance of data class
        this.dataClass = DataClass.get_instance();
        ProgressBar progressBar = root.findViewById(R.id.taskCompleteBar);

        //Set the text and progress bar's progress on the screen when the fragment is created
        progressText.setText("Tasks - " + dataClass.tasksCompleted.toString() + "/4 Completed");
        progressBar.setProgress(dataClass.tasksCompleted);

        //Sets up the screen every time the fragment gets switched to
        if (dataClass.completedTasks.get(0)) {
            ImageView checkBox = root.findViewById(R.id.checkTask1);
            checkBox.setImageResource(android.R.drawable.checkbox_on_background);
            root.findViewById(R.id.bgTask1).setBackgroundColor(0xFFC7FCD9);
            root.findViewById(R.id.buttonTask1).setBackgroundColor(Color.parseColor("#808080"));
            root.findViewById(R.id.buttonTask1).setClickable(false);
        }
        if (dataClass.completedTasks.get(1)) {
            ImageView checkBox = root.findViewById(R.id.checkTask2);
            checkBox.setImageResource(android.R.drawable.checkbox_on_background);
            root.findViewById(R.id.bgTask2).setBackgroundColor(0xFFC7FCD9);
            root.findViewById(R.id.buttonTask2).setBackgroundColor(Color.parseColor("#808080"));
            root.findViewById(R.id.buttonTask2).setClickable(false);
        }
        if (dataClass.completedTasks.get(2)) {
            ImageView checkBox = root.findViewById(R.id.checkTask3);
            checkBox.setImageResource(android.R.drawable.checkbox_on_background);
            root.findViewById(R.id.bgTask3).setBackgroundColor(0xFFC7FCD9);
            root.findViewById(R.id.buttonTask3).setBackgroundColor(Color.parseColor("#808080"));
            root.findViewById(R.id.buttonTask3).setClickable(false);
        }
        if (dataClass.completedTasks.get(3)) {
            ImageView checkBox = root.findViewById(R.id.checkTask4);
            checkBox.setImageResource(android.R.drawable.checkbox_on_background);
            root.findViewById(R.id.bgTask4).setBackgroundColor(0xFFC7FCD9);
            root.findViewById(R.id.buttonTask4).setBackgroundColor(Color.parseColor("#808080"));
            root.findViewById(R.id.buttonTask4).setClickable(false);
        }



        // Add all of the button listeners to call the completeTask function
        Button buttonTask1 = root.findViewById(R.id.buttonTask1);
        buttonTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeTask(root.findViewById(R.id.checkTask1),1, root.findViewById(R.id.bgTask1), root.findViewById(R.id.buttonTask1));
            }
        });
        Button buttonTask2 = root.findViewById(R.id.buttonTask2);
        buttonTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeTask(root.findViewById(R.id.checkTask2),2, root.findViewById(R.id.bgTask2), root.findViewById(R.id.buttonTask2));
            }
        });
        Button buttonTask3 = root.findViewById(R.id.buttonTask3);
        buttonTask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeTask(root.findViewById(R.id.checkTask3),3, root.findViewById(R.id.bgTask3), root.findViewById(R.id.buttonTask3));
            }
        });
        Button buttonTask4 = root.findViewById(R.id.buttonTask4);
        buttonTask4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completeTask(root.findViewById(R.id.checkTask4),4, root.findViewById(R.id.bgTask4), root.findViewById(R.id.buttonTask4));
            }
        });
    }

    //Called when a task is completed
    private void completeTask(ImageView checkBox, int taskNum, View bgView, Button btn){
        //Checks if the task is not already done
        if (!dataClass.completedTasks.get(taskNum - 1)){
            dataClass.completedTasks.set(taskNum - 1, true);
            //Adds tokens to the user's count
            dataClass.NumTokens.setValue(dataClass.NumTokens.getValue()+20);
            System.out.println(dataClass.NumTokens);
            ProgressBar progressBar = root.findViewById(R.id.taskCompleteBar);
            //Changes the checkmarks icon to the filled in version
            checkBox.setImageResource(android.R.drawable.checkbox_on_background);
            //Changes the background of the task
            bgView.setBackgroundColor(0xFFC7FCD9);
            dataClass.tasksCompleted++;
            progressText.setText("Tasks - " + dataClass.tasksCompleted.toString() + "/4 Completed");

            btn.setBackgroundColor(Color.parseColor("#808080"));
            btn.setClickable(false);

            //Animates the progress bar going up if the android sdk version supports it
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                progressBar.setProgress(dataClass.tasksCompleted,true);
            }
            else{
                progressBar.setProgress(dataClass.tasksCompleted);
            }
            //Checks if all of the tasks are done
            boolean bonusTokens = true;
            for (boolean taskDone:
                 dataClass.completedTasks) {
                if (!taskDone){
                    bonusTokens = false;
                }
            }
            //Adds 50 bonus tokens to the user if all of their tasks are completed.
            if (bonusTokens){
                dataClass.NumTokens.setValue(dataClass.NumTokens.getValue()+50);
                System.out.println(dataClass.NumTokens);
            }
        }
    }
}