package info.atalou.apps.myatapos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.TaxEntity;

public class TaxAdapter extends RecyclerView.Adapter<TaxAdapter.ViewHolder> {

    private final List<TaxEntity> mNotes;
    private final Context mContext;
    private OnTaxListener onTaxListener;



    public TaxAdapter(List<TaxEntity> mNotes, Context mContext, OnTaxListener onTaxListener) {
        this.mNotes = mNotes;
        this.mContext = mContext;
        this.onTaxListener = onTaxListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_tax, parent,false);
        return new ViewHolder(view,onTaxListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  TaxEntity note = mNotes.get(position);
        holder.mName.setText(note.getName());
        holder.mCode.setText(note.getCode());
        holder.mValue.setText(""+note.getValue());

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mName,mCode, mValue;

        OnTaxListener onTaxListener;


        ViewHolder(@NonNull View itemView, OnTaxListener onTaxListener) {
            super(itemView);

            mName = itemView.findViewById(R.id.name);
            mCode = itemView.findViewById(R.id.code);
            mValue = itemView.findViewById(R.id.value);
            this.onTaxListener = onTaxListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTaxListener.onTaxClick(getAdapterPosition());
        }
    }

    public interface OnTaxListener{
        void onTaxClick(int position);
    }
}
