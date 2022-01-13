package eu.trackyourtasks.trackyourtasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// @Mohammed Sabar
public class NotesListViewAdapter extends RecyclerView.Adapter<NotesListViewAdapter.ViewHolder> {

    private List<String> mTitle;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    NotesListViewAdapter(Context context, List<String> title) {
        this.mInflater = LayoutInflater.from(context);
        this.mTitle = title;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.notes_list_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = mTitle.get(position);
        holder.titleTxt.setText(title);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTxt;

        ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.notesListViewRowTitleTxt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mTitle.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}