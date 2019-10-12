package thuyvtk.activity.laundry_customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.model.ServiceTypeDTO;

public class ServiceTypeAdapter extends BaseAdapter {
    ArrayList<ServiceTypeDTO> listServiceType;
    Context context;
    ServiceAdapter serviceAdapter;

    public ServiceTypeAdapter(ArrayList<ServiceTypeDTO> listServiceType, Context context) {
        this.listServiceType = listServiceType;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listServiceType.size();
    }

    @Override
    public Object getItem(int position) {
        return listServiceType.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.service_type_item,null);
        TextView txtServiceName = convertView.findViewById(R.id.txtServiceTypeName);
        ListView lvService = convertView.findViewById(R.id.lvService);
        ServiceTypeDTO dto = (ServiceTypeDTO) getItem(position);
        txtServiceName.setText(dto.getName());
        serviceAdapter = new ServiceAdapter(context,dto.getListService(),0);
        lvService.setAdapter(serviceAdapter);
        return convertView;
    }
}
