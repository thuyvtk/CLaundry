package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.adapter.ServiceAdapter;
import thuyvtk.activity.laundry_customer.library.CartDTO;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;
import thuyvtk.activity.laundry_customer.view.StoreAdapterView;

public class ShoppingCartActivity extends AppCompatActivity implements StoreAdapterView {
    ListView lvCartItem;
    TextView txtTotalPrice, txtCustomerName;
    SharePreferenceLib sharePreferenceLib;

    private void defineView() {
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        lvCartItem = findViewById(R.id.lvCartItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        defineView();
        sharePreferenceLib = new SharePreferenceLib(this);
        CartDTO dto = sharePreferenceLib.getShoppingCart();
        if (dto != null) {
            ArrayList<ServiceDTO> listService = (ArrayList<ServiceDTO>) dto.getListStore().values();
            ServiceAdapter serviceAdapter = new ServiceAdapter(this, listService, 1, this);
            lvCartItem.setAdapter(serviceAdapter);
        }
        txtCustomerName.setText(dto.getCustomer().getCustomerName());
        txtTotalPrice.setText(dto.getTotalPrice()+"");
    }

    @Override
    public void onRemoveFromCart(double totalPrice) {
        txtTotalPrice.setText(totalPrice + "");
    }
}
