package info.atalou.apps.myatapos.ui.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.CategoryEntity;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final List<CategoryEntity> mNotes;
    private final Context mContext;
    private OnCategoryListener onCategoryListener;



    public CategoryAdapter(List<CategoryEntity> mNotes, Context mContext, OnCategoryListener onCategoryListener) {
        this.mNotes = mNotes;
        this.mContext = mContext;
        this.onCategoryListener = onCategoryListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_category, parent,false);
        return new ViewHolder(view,onCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  CategoryEntity note = mNotes.get(position);
        holder.mName.setText(note.getName());
        holder.mCode.setText(note.getCode());
        holder.mImage.setBackground(new ColorDrawable(note.getColor()));

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mName;
        TextView mCode;
        ImageView mImage;
        OnCategoryListener onCategoryListener;


        ViewHolder(@NonNull View itemView, OnCategoryListener onCategoryListener) {
            super(itemView);

            mName = itemView.findViewById(R.id.name);
            mCode = itemView.findViewById(R.id.code);
            mImage = itemView.findViewById(R.id.image);
            this.onCategoryListener = onCategoryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCategoryListener.onCategoryClick(getAdapterPosition());
        }
    }

    public interface OnCategoryListener{
        void onCategoryClick(int position);
    }
}
