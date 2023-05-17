package com.example.bottomnavigation;

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

    View root;
    TextView progressText;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        root = super.getView(position, convertView, parent);

        // Find the button and the ID
        Button buy_button = root.findViewById(R.id.product_buy_button);
        TextView id_text = root.findViewById(R.id.product_id);
        progressText = root.findViewById(R.id.taskCompleteText);
        String string_id = (String) id_text.getText();
        int id = Integer.valueOf(string_id);

        // Find the item that this view refers to
        Task task;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            task = dataClass.tasks.stream()
                .filter((Task i) -> i.id == id)
                .collect(Collectors.toList())
                .get(0);
        } else task = null;

        // Set a listener that adds the item to the cart and says so
        buy_button.setOnClickListener((v)->{
            completeTask(null,task.id,null,null);
        });

        // Required for parity with base SimpleAdapter
        return root;
    }
    private void completeTask(ImageView checkBox, int taskNum, View bgView, Button btn){
        //Checks if the task is not already done
        if (!dataClass.completedTasks.get(taskNum - 1)){
            dataClass.completedTasks.set(taskNum - 1, true);
            //Adds tokens to the user's count
            dataClass.NumTokens += 20;
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
                dataClass.NumTokens += 50;
                System.out.println(dataClass.NumTokens);
            }
        }
    }
}
