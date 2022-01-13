package eu.trackyourtasks.trackyourtasks;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import eu.trackyourtasks.trackyourtasks.R;

import java.util.List;

// @Mateusz Ostapko

public class ProjectsListViewAdapter extends RecyclerView.Adapter<ProjectsListViewAdapter.ViewHolder> {

    private List<String> mTitle;
    private List<String> mTime;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    ProjectsListViewAdapter(Context context, List<String> title, List<String> time) {
        this.mInflater = LayoutInflater.from(context);
        this.mTitle = title;
        this.mTime = time;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.project_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = mTitle.get(position);
        String time = mTime.get(position);
        holder.timeTxt.setText(time);
        holder.titleTxt.setText(title);
    }

    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView titleTxt;
        TextView timeTxt;

        ViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.projectsListViewRowTitleTxt);
            timeTxt = itemView.findViewById(R.id.projectsListViewRowTimeTxt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    String getItem(int id) {
        return mTitle.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}