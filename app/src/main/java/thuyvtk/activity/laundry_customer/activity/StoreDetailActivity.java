package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.adapter.ServiceTypeAdapter;
import thuyvtk.activity.laundry_customer.model.ServiceTypeDTO;
import thuyvtk.activity.laundry_customer.model.StoreDTO;
import thuyvtk.activity.laundry_customer.presenter.StorePresenter;
import thuyvtk.activity.laundry_customer.view.StoreView;

public class StoreDetailActivity extends AppCompatActivity implements StoreView {
    ImageView imgOpen, btnShoppingCart;
    TextView txtOpen, txtStoreName, txtStoreAddress, txtStoreRate;
    ListView lvServiceType;
    StorePresenter presenter;
    LinearLayout ln_store_waiting;
    ServiceTypeAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        defineView();
        Intent intent = getIntent();
        String storeId = intent.getStringExtra("storeId");
        if(!storeId.equals("")){
            presenter = new StorePresenter(this);
            presenter.getStoreById(storeId);
            ln_store_waiting.setVisibility(View.VISIBLE);
        }
    }

    public void clickToClose(View view) {
        this.finish();
    }

    private void defineView() {
        imgOpen = findViewById(R.id.ic_Open);
        txtOpen = findViewById(R.id.txtOpen);
        txtStoreName = findViewById(R.id.txtStoreName);
        txtStoreAddress = findViewById(R.id.txtStoreAddress);
        txtStoreRate = findViewById(R.id.txtStoreRate);
        lvServiceType = findViewById(R.id.lvServiceType);
        ln_store_waiting = findViewById(R.id.ln_store_waiting);
        btnShoppingCart = findViewById(R.id.btnShoppingCart);
        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo add shopping cart dialog here
            }
        });
    }

    @Override
    public void returnAllStore(ArrayList<StoreDTO> listStore) {

    }

    @Override
    public void loadStoreFail(String message) {

    }

    @Override
    public void searchStoreByName(ArrayList<StoreDTO> listStore) {

    }

    @Override
    public void searchStoreByNameFail(String message) {

    }

    @Override
    public void returnStoreById(StoreDTO storeDTO) {
        fillData(storeDTO);
        ln_store_waiting.setVisibility(View.GONE);
    }

    private void fillData(StoreDTO storeDTO) {
        txtStoreName.setText(storeDTO.getName());
        txtStoreAddress.setText(storeDTO.getAddress());
        txtStoreRate.setText(storeDTO.getRate()+"");
        List<ServiceTypeDTO> serviceTypeDTOS = storeDTO.getListServiceType();
        adapter = new ServiceTypeAdapter((ArrayList<ServiceTypeDTO>) serviceTypeDTOS,getApplicationContext());
        lvServiceType.setAdapter(adapter);
    }


    //todo add serviceAdapter
}
