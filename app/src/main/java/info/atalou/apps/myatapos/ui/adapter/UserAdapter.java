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
import info.atalou.apps.myatapos.database.entity.UserEntity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final List<UserEntity> mUsers;
    private final Context mContext;
    private OnUserListener onUserListener;



    public UserAdapter(List<UserEntity> mUsers, Context mContext, OnUserListener onUserListener) {
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.onUserListener = onUserListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_user, parent,false);
        return new ViewHolder(view,onUserListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  UserEntity note = mUsers.get(position);
        holder.mName.setText(note.getName());
        holder.mRole.setText(String.valueOf(note.getRole()));
        holder.mUsername.setText(note.getUsername());

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mName;
        TextView mUsername;
        TextView mRole;
        OnUserListener onUserListener;


        ViewHolder(@NonNull View itemView, OnUserListener onUserListener) {
            super(itemView);

            mName = itemView.findViewById(R.id.name);
            mUsername = itemView.findViewById(R.id.username);
            mRole = itemView.findViewById(R.id.role);
            this.onUserListener = onUserListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onUserListener.onUserClick(getAdapterPosition());
        }
    }

    public interface OnUserListener{
        void onUserClick(int position);
    }
}