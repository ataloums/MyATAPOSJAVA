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
import info.atalou.apps.myatapos.database.entity.CustomerGroupEntity;

public class CustomerGroupAdapter extends RecyclerView.Adapter<CustomerGroupAdapter.ViewHolder> {

    private final List<CustomerGroupEntity> mNotes;
    private final Context mContext;
    private OnCustomerGroupListener onCustomerGroupListener;



    public CustomerGroupAdapter(List<CustomerGroupEntity> mNotes, Context mContext, OnCustomerGroupListener onCustomerGroupListener) {
        this.mNotes = mNotes;
        this.mContext = mContext;
        this.onCustomerGroupListener = onCustomerGroupListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_client_group, parent,false);
        return new ViewHolder(view,onCustomerGroupListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  CustomerGroupEntity note = mNotes.get(position);
        holder.mName.setText(note.getName());
        holder.mValue.setText(""+note.getValue());

    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mName, mValue;

        OnCustomerGroupListener onCustomerGroupListener;


        ViewHolder(@NonNull View itemView, OnCustomerGroupListener onCustomerGroupListener) {
            super(itemView);

            mName = itemView.findViewById(R.id.name);
            mValue = itemView.findViewById(R.id.value);
            this.onCustomerGroupListener = onCustomerGroupListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCustomerGroupListener.onCustomerGroupClick(getAdapterPosition());
        }
    }

    public interface OnCustomerGroupListener{
        void onCustomerGroupClick(int position);
    }
}
