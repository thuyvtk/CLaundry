package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import thuyvtk.activity.laundry_customer.R;

public class StoreDetailActivity extends AppCompatActivity {
ImageView imgOpen;
TextView txtOpen, txtStoreName, txtStoreAddress, txtStoreRate;
ListView lvServiceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        defineView();
    }

    public void clickToClose(View view) {
        this.finish();
    }

    private void defineView(){
        imgOpen = findViewById(R.id.ic_Open);
        txtOpen = findViewById(R.id.txtOpen);
        txtStoreName = findViewById(R.id.txtStoreName);
        txtStoreAddress = findViewById(R.id.txtStoreAddress);
        txtStoreRate = findViewById(R.id.txtStoreRate);
        lvServiceType = findViewById(R.id.lvServiceType);
    }
}
