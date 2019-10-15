package thuyvtk.activity.laundry_customer.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    ImageView imgReceipt;

    private void defineView() {
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        txtCustomerName = findViewById(R.id.txtCustomerName);
        lvCartItem = findViewById(R.id.lvCartItem);
        imgReceipt = findViewById(R.id.imgReceipt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        defineView();
        sharePreferenceLib = new SharePreferenceLib(this);
        CartDTO dto = sharePreferenceLib.getShoppingCart();
        if (dto != null) {
            ArrayList<ServiceDTO> listService = new ArrayList<>(dto.getListStore().values());
            ServiceAdapter serviceAdapter = new ServiceAdapter(this, listService, 1, this);
            lvCartItem.setAdapter(serviceAdapter);
        }
        txtCustomerName.setText(dto.getCustomer().getCustomerName());
        txtTotalPrice.setText(dto.getTotalPrice()+"");
        imgReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReceiptDetailActivity.class);
                startActivityForResult(intent,113);
            }
        });
    }

    @Override
    public void onRemoveFromCart(double totalPrice) {
        txtTotalPrice.setText(totalPrice + "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 113 && resultCode ==115){
            this.finish();
        }
    }
}
