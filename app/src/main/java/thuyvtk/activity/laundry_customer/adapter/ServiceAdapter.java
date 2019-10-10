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

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;

public class ServiceAdapter extends BaseAdapter {
    Context context;
    ArrayList<ServiceDTO> listService;

    public ServiceAdapter(Context context, ArrayList<ServiceDTO> listService) {
        this.context = context;
        this.listService = listService;
    }

    @Override
    public int getCount() {
        return listService.size();
    }

    @Override
    public Object getItem(int position) {
        return listService.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.service_item,null);
        ImageView imgService = view.findViewById(R.id.imgService);
        TextView txtServiceName = view.findViewById(R.id.txtServiceName);
        TextView txtDescription = view.findViewById(R.id.txtDescription);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        ServiceDTO dto = (ServiceDTO) getItem(position);
        if(!dto.getImage().equals("")){
            Picasso.with(context).load(dto.getImage()).into(imgService);
        }
        txtServiceName.setText(dto.getName());
        txtDescription.setText(dto.getDescription());
        txtPrice.setText(dto.getPrice()+"");
        return view;
    }
}
