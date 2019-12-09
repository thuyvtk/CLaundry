package thuyvtk.activity.laundry_customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.library.CartDTO;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;
import thuyvtk.activity.laundry_customer.view.StoreAdapterView;

public class ServiceAdapter extends BaseAdapter {
    Context context;
    ArrayList<ServiceDTO> listService;
    ArrayList<ServiceDTO> originalList;
    SharePreferenceLib sharePreferenceLib;
    int screenNumber;
    StoreAdapterView view;

    // screen: 0:receipt, 1: cartActivity 2: order detail
    public ServiceAdapter(Context context, ArrayList<ServiceDTO> listService, int screenNumber) {
        this.context = context;
        this.listService = listService;
        this.originalList = listService;
        this.screenNumber = screenNumber;
    }

    public ServiceAdapter(Context context, ArrayList<ServiceDTO> listService, int screenNumber, StoreAdapterView view) {
        this.context = context;
        this.listService = listService;
        this.screenNumber = screenNumber;
        this.view = view;
        this.originalList = listService;
        sharePreferenceLib = new SharePreferenceLib(context);
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
        view = LayoutInflater.from(context).inflate(R.layout.service_item, null);
        ImageView imgService = view.findViewById(R.id.imgService);
        TextView txtServiceName = view.findViewById(R.id.txtServiceName);
        TextView txtPrice = view.findViewById(R.id.txtPrice);
        ImageButton imgBAdd = view.findViewById(R.id.imgBAdd);
        TextView txtQuantity = view.findViewById(R.id.txtQuantity);
        final ServiceDTO dto = (ServiceDTO) getItem(position);
        if (dto.getImage() != null && !dto.getImage().equals("")) {
            Picasso.with(context).load(dto.getImage()).into(imgService);
        }
        txtServiceName.setText(dto.getDescription());
        txtPrice.setText(dto.getPrice() + "");
        if (dto != null) {
            txtQuantity.setText("X" + dto.getQuantity() + "");
        }
        if (screenNumber == 0) {
            imgBAdd.setVisibility(View.GONE);
        } else if (screenNumber == 1) {
            imgBAdd.setImageResource(R.drawable.ic_remove_circle_red_24dp);
            imgBAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFromCart(dto);
                    reLoadListService(dto);
                }
            });
        }else if(screenNumber == 2){
           imgBAdd.setVisibility(View.GONE);
        }
        return view;
    }

    private void removeFromCart(ServiceDTO dto) {
        CartDTO cartDTO = sharePreferenceLib.getShoppingCart();
        cartDTO.removeStore(dto);
        sharePreferenceLib.saveShoppingCart(cartDTO);
        view.onRemoveFromCart(cartDTO.getTotalPrice());
    }

    private void reLoadListService(ServiceDTO dto) {
        for (int i = 0; i < originalList.size(); i++) {
            ServiceDTO serviceDTO = originalList.get(i);
            if (serviceDTO.getId().equals(dto.getId())) {
                originalList.remove(serviceDTO);
            }
        }
        listService = originalList;
        notifyDataSetChanged();
    }
}
