package com.example.bottomnavigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskAdapter extends SimpleAdapter {
    static DataClass dataClass = DataClass.get_instance();

    public TaskAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    TextView progressText;
    ProgressBar progressBar;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = super.getView(position, convertView, parent);

        // Find the button and the ID
        Button buy_button = root.findViewById(R.id.task_btn);
        TextView id_text = root.findViewById(R.id.task_id);
        ImageView check_box = root.findViewById(R.id.task_check);
        View background = root.findViewById(R.id.task_bg);
        progressBar = ((View) parent.getParent()).findViewById(R.id.taskCompleteBar);
        progressText = ((View) parent.getParent()).findViewById(R.id.taskCompleteText);
        String string_id = (String) id_text.getText();
        int id = Integer.parseInt(string_id);

        updateTaskBar();

        // Find the item that this view refers to
        Task task;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            task = dataClass.tasks.stream()
                .filter((Task i) -> i.id == id)
                .collect(Collectors.toList())
                .get(0);
        } else task = null;

        int task_idx = dataClass.tasks.indexOf(task);
        markCompleted(check_box, background, buy_button, dataClass.completedTasks.get(task_idx));

        // Set a listener that adds the item to the cart and says so
        buy_button.setOnClickListener((v)->{
            completeTask(check_box,task_idx,background,buy_button,task);
        });

        // Required for parity with base SimpleAdapter
        return root;
    }

    @SuppressLint("ResourceAsColor")
    private void markCompleted(ImageView checkBox, View bgView, Button btn, boolean completed) {
        //Changes the checkmarks icon to the filled in version
        checkBox.setImageResource(completed
                ?R.drawable.checkbox_on
                :R.drawable.checkbox_off);
        //Changes the background of the task
        bgView.setBackgroundColor(Color.parseColor(completed
                ?"#C7FCD9"
                :"#D9D9D9"));
        //Changes the button to be unclickable/greyed-out
        btn.setBackgroundColor(Color.parseColor(completed
                ?"#808080"
                :"#FFA53A"));
        btn.setClickable(!completed);
    }

    private void updateTaskBar() {
        progressText.setText(
                "Tasks - " + dataClass.tasksCompleted
                        + "/" +dataClass.tasks.size()+" Completed");
        progressBar.setMax(dataClass.tasks.size());
        //Animates the progress bar going up if the android sdk version supports it
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(dataClass.tasksCompleted,true);
        }
        else{
            progressBar.setProgress(dataClass.tasksCompleted);
        }
    }

    private void completeTask(ImageView checkBox, int taskNum, View bgView, Button btn, Task task){
        //Checks if the task is not already done
        if (!dataClass.completedTasks.get(taskNum)){
            dataClass.completedTasks.set(taskNum, true);
            //Adds tokens to the user's count
            dataClass.NumTokens.setValue(dataClass.NumTokens.getValue()+task.tokens);

            dataClass.tasksCompleted++;
            updateTaskBar();

            markCompleted(checkBox, bgView, btn, true);

            //Checks if all of the tasks are done
            boolean bonusTokens = true;
            for (boolean taskDone:
                    dataClass.completedTasks) {
                if (!taskDone){
                    bonusTokens = false;
                }
            }
            //Adds some bonus tokens to the user if all of their tasks are completed.
            if (bonusTokens){
                dataClass.NumTokens.setValue(dataClass.NumTokens.getValue()+100);
                Toast.makeText(bgView.getContext(), "Here's 100 bonus tokens for completing all of the tasks!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
