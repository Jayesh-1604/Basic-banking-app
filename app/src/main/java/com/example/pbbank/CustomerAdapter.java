package com.example.pbbank;


        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Locale;

public class CustomerAdapter extends BaseAdapter {

    // variable
    Context mContext;
    LayoutInflater inflater;
    List<Customer> CustomerList;
    ArrayList<Customer> arrayListRes;

    //Constructor
    public CustomerAdapter(Context context, List<Customer> CustomerList) {
        this.mContext = context;
        this.CustomerList = CustomerList;
        inflater = LayoutInflater.from(mContext);
        this.arrayListRes = new ArrayList<Customer>();
        this.arrayListRes.addAll(CustomerList);

    }

    public class ViewHolder {
        TextView UserName, UserLoc;
    }


    @Override
    public int getCount() {
        return CustomerList.size();
    }

    @Override
    public Object getItem(int i) {
        return CustomerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.sigleuser, null);
            holder.UserName = view.findViewById(R.id.txtUserName);
            holder.UserLoc = view.findViewById(R.id.txtLocation);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.UserName.setText(CustomerList.get(position).getbUserName());
        holder.UserLoc.setText(CustomerList.get(position).getbUserLocation());

        return view;
    }

}
