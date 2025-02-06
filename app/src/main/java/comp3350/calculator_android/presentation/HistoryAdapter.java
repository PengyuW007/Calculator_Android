package comp3350.calculator_android.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<String> historyList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    // Constructor to initialize the history list
    public HistoryAdapter(List<String> historyList, OnItemClickListener listener) {
        this.historyList = historyList;
        this.listener = listener;
    }

    // ViewHolder class to hold the views
    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView historyItemText;

        public HistoryViewHolder(View view) {
            super(view);
            historyItemText = view.findViewById(android.R.id.text1);
        }
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the list
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new HistoryViewHolder(itemView); // Return the correct ViewHolder
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        String historyItem = historyList.get(position);
        // Bind the data to the ViewHolder
        holder.historyItemText.setText(historyList.get(position));
        holder.itemView.setOnClickListener(v -> listener.onItemClick(historyItem));
    }

    @Override
    public int getItemCount() {
        // Return the size of the history list
        return historyList.size();
    }
}
