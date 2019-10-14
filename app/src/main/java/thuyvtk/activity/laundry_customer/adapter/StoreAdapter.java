package thuyvtk.activity.laundry_customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.model.StoreDTO;

public class StoreAdapter extends BaseAdapter {

    Context context;
    ArrayList<StoreDTO> listStore;

    public StoreAdapter(Context context, ArrayList<StoreDTO> listStore) {
        this.context = context;
        this.listStore = listStore;
    }

    @Override
    public int getCount() {
        return listStore.size();
    }

    @Override
    public Object getItem(int position) {
        return listStore.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.store_item,null);
        StoreDTO dto = (StoreDTO) getItem(position);
        ImageView imgStore = convertView.findViewById(R.id.imgStore);
        TextView txtStoreName =  convertView.findViewById(R.id.txtStoreName);
        TextView txtStoreAddress =  convertView.findViewById(R.id.txtStoreAddress);
        TextView txtStoreRate =  convertView.findViewById(R.id.txtStoreRate);
        Picasso.with(context).load(dto.getImage()).into(imgStore);
        txtStoreName.setText(dto.getName());
        txtStoreAddress.setText(dto.getAddress());
        String rate = "Rate: ";
        rate += dto.getRate();
        txtStoreRate.setText(rate);
        return convertView;
    }
}
