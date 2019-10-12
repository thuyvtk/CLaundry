package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import thuyvtk.activity.laundry_customer.R;

public class StoreDetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ImageView imgOpen, btnShoppingCart;
    TextView txtOpen, txtStoreName, txtStoreAddress, txtStoreRate, timeDelivery, dateDelivery;
    LinearLayout ln_timeDelivery, ln_dateDelivery;
    ListView lvServiceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        defineView();
        setTime();
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
        timeDelivery = findViewById(R.id.timeDelivery);
        dateDelivery = findViewById(R.id.dateDelivery);
        ln_timeDelivery = findViewById(R.id.ln_timeDelivery);
        ln_dateDelivery = findViewById(R.id.ln_dateDelivery);
        btnShoppingCart = findViewById(R.id.btnShoppingCart);
        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo add shopping cart dialog here
            }
        });
    }

    private void setTime() {
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this, 2019, 10, 12);
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        timeDelivery.setText(timeFormat.format(date));
        Date nextDate = date;
        nextDate.setDate(date.getDay() + 1);
        dateDelivery.setText(dateFormat.format(nextDate));
        ln_timeDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(StoreDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        timeDelivery.setText(time);
                    }
                }, 0, 0, true);
                timePickerDialog.setTitle("Select Delivery Time");
                timePickerDialog.show();
            }
        });

        ln_dateDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        dateDelivery.setText(date);
    }

    //todo add serviceAdapter
}
