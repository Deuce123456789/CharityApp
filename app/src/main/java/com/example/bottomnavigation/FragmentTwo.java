package com.example.bottomnavigation;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigation.databinding.FragmentTwoBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FragmentTwo extends Fragment {

    private @NonNull FragmentTwoBinding binding;
    private View root;
    private DataClass dataClass;
    private TextView progressText;
    private ArrayList<HashMap<String,?>> tasks;
    private TaskAdapter taskAdapter;


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

    // Generates a single product to be passed into the adapter and rendered.
    HashMap<String, Object> genProduct(String name, Integer tokens, String description, int id) {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("tokens", tokens);
            put("description", description);
            put("id", id);
        }};
    }

    // Generates a single product to be passed into the adapter and rendered.
    ArrayList<HashMap<String,?>> genProducts(List<Task> tsks) {
        return new ArrayList<HashMap<String,?>>() {{
            for (Task tsk : tsks) {
                add(genProduct(tsk.name, tsk.tokens, tsk.description, tsk.id));
            }
        }};
    }

    // Initializes the list of tasks and the progress bar
    void setupScreen() {
        //Find the instance of data class
        this.dataClass = DataClass.get_instance();
        ProgressBar progressBar = root.findViewById(R.id.taskCompleteBar);

        GridView taskView = root.findViewById(R.id.task_scroll);
        tasks = genProducts(dataClass.tasks);
        taskAdapter = new TaskAdapter(
                root.getContext(), tasks,
                R.layout.task,
                new String[]{"name", "tokens", "description", "id"},
                new int[]{R.id.task_name, R.id.task_tokens, R.id.task_description, R.id.task_id}
        );
        taskView.setAdapter(taskAdapter);


        //Set the text and progress bar's progress on the screen when the fragment is created
        progressText.setText("Tasks - " + dataClass.tasksCompleted.toString() + "/4 Completed");
        progressBar.setProgress(dataClass.tasksCompleted);

    }

    //Called when a task is completed

}