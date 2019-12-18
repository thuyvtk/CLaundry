package thuyvtk.activity.laundry_customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.library.CartDTO;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;
import thuyvtk.activity.laundry_customer.model.ServiceTypeDTO;

public class ServiceTypeAdapter extends BaseAdapter {
    ArrayList<ServiceTypeDTO> listServiceType;
    Context context;
    ServiceAdapter serviceAdapter;
    SharePreferenceLib sharePreferenceLib;

    public ServiceTypeAdapter(ArrayList<ServiceTypeDTO> listServiceType, Context context) {
        this.listServiceType = listServiceType;
        this.context = context;
        sharePreferenceLib = new SharePreferenceLib(context);
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
        LinearLayout lnService = convertView.findViewById(R.id.lnService);
        ServiceTypeDTO dto = (ServiceTypeDTO) getItem(position);
        txtServiceName.setText(dto.getName());
        serviceAdapter = new ServiceAdapter(context,dto.getListService(),0);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        ArrayList<ServiceDTO> listService = dto.getListService();
        for (ServiceDTO item : listService) {
            View child = inflater.inflate(R.layout.service_item,null);
            child = addViewChild(child,item);
            lnService.addView(child);
        }
        return convertView;
    }
    ImageView imgService;
    TextView txtServiceName ;
    TextView txtDescription;
    TextView txtPrice;
    ImageButton imgBAdd;
    private View addViewChild(View child, final ServiceDTO serviceDTO){
        imgService = child.findViewById(R.id.imgService);
        txtServiceName = child.findViewById(R.id.txtServiceName);
        txtPrice = child.findViewById(R.id.txtPrice);
        imgBAdd = child.findViewById(R.id.imgBAdd);
        // set data
        txtServiceName.setText(serviceDTO.getDescription());
        double price = serviceDTO.getPrice();
        txtPrice.setText( price + " VND");
        if (serviceDTO.getImage() != null && !serviceDTO.getImage().equals("")) {
            Picasso.with(context).load(serviceDTO.getImage()).into(imgService);
        }

        imgBAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(serviceDTO);
            }
        });
        return child;
    }

    private void addToCart(ServiceDTO dto){
        CartDTO cartDTO = sharePreferenceLib.getShoppingCart();
        cartDTO.addStore(dto);
        sharePreferenceLib.saveShoppingCart(cartDTO);
    }
}
