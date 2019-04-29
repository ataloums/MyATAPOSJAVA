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
import info.atalou.apps.myatapos.database.entity.ClientEntity;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {

    private final List<ClientEntity> mClients;
    private final Context mContext;
    private OnClientListener onClientListener;



    public ClientAdapter(List<ClientEntity> mClients, Context mContext, OnClientListener onClientListener) {
        this.mClients = mClients;
        this.mContext = mContext;
        this.onClientListener = onClientListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_client, parent,false);
        return new ViewHolder(view,onClientListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  ClientEntity note = mClients.get(position);
        holder.mName.setText(note.getName());
        holder.mEmail.setText(note.getEmail());
        holder.mPhone.setText(note.getPhone());

    }

    @Override
    public int getItemCount() {
        return mClients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mName;
        TextView mEmail;
        TextView mPhone;
        OnClientListener onClientListener;


        ViewHolder(@NonNull View itemView, OnClientListener onClientListener) {
            super(itemView);

            mName = itemView.findViewById(R.id.name);
            mEmail = itemView.findViewById(R.id.email);
            mPhone = itemView.findViewById(R.id.phone);
            this.onClientListener = onClientListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClientListener.onClientClick(getAdapterPosition());
        }
    }

    public interface OnClientListener{
        void onClientClick(int position);
    }
}