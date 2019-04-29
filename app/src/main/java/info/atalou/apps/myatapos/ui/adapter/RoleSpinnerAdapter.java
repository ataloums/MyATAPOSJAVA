package info.atalou.apps.myatapos.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import info.atalou.apps.myatapos.R;
import info.atalou.apps.myatapos.database.entity.RoleEntity;

public class RoleSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

    private LayoutInflater mInflater;
    private List<RoleEntity> mItems;

    public RoleSpinnerAdapter(Context context, List<RoleEntity> items) {
        mInflater = LayoutInflater.from(context);
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getId();
    }

    // This is for the default ("idle") state of the spinner.
    // You can use a custom layout or use the default one.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.row_role_spinner, parent, false);
        }
        RoleEntity item = (RoleEntity) getItem(position);
        TextView textView = view.findViewById(R.id.role_name);
        textView.setText(item.getName());
        return view;
    }

    // Drop down item view as stated in the method name.
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.row_role_spinner, parent, false);
        }
        RoleEntity item = (RoleEntity) getItem(position);
        TextView textView = view.findViewById(R.id.role_name);
        textView.setText(item.getName());
        return view;
    }

}

