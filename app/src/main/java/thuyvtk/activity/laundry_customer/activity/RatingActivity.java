package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.model.OrderDetailDTO;
import thuyvtk.activity.laundry_customer.model.StoreBS;
import thuyvtk.activity.laundry_customer.presenter.OrderPresenter;
import thuyvtk.activity.laundry_customer.view.OrderView;

public class RatingActivity extends AppCompatActivity implements OrderView {
    RatingBar rbRate;
    Button btnSubmitRate;
    OrderPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        presenter = new OrderPresenter(this);
        getDialogView();
    }

    private void getDialogView() {
        rbRate = findViewById(R.id.rbRate);
        btnSubmitRate = findViewById(R.id.btnSubmitRate);
        btnSubmitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rate = rbRate.getRating();
                String id = (String) rbRate.getTag();
                presenter.ratingStore(id,rate);
            }
        });
    }

    @Override
    public void loadOrderHistory(List<OrderDetailDTO> orderList) {

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void createOrderSuccess() {

    }

    @Override
    public void returnListOrder(List<OrderDetailDTO> listOrderDetail) {

    }

    @Override
    public void rateSuccess(String message) {
        Toast.makeText(this, "Rating success", Toast.LENGTH_SHORT).show();
    }
}
