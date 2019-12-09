package thuyvtk.activity.laundry_customer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.adapter.ServiceAdapter;
import thuyvtk.activity.laundry_customer.library.SharePreferenceLib;
import thuyvtk.activity.laundry_customer.model.OrderOngoingDTO;
import thuyvtk.activity.laundry_customer.model.OrderServiceDTO;
import thuyvtk.activity.laundry_customer.model.ServiceDTO;

public class DetailOrderHistoryActivity extends AppCompatActivity {

    OrderOngoingDTO orderOngoingDTO;
    TextView txtStoreName, txtCustomerReceipt, txtPhone, txtAddress, timeTake, timeDelivery, txtTotalReceipt;
    SharePreferenceLib sharePreferenceLib;
    ListView lvReceipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_history);
        defineView();
        getOrder();
    }

    private void defineView() {
        txtStoreName = findViewById(R.id.txtStoreName);
        txtCustomerReceipt = findViewById(R.id.txtCustomerReceipt);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        timeTake = findViewById(R.id.timeTake);
        timeDelivery = findViewById(R.id.timeDelivery);
        txtTotalReceipt = findViewById(R.id.txtTotalReceipt);
        lvReceipt = findViewById(R.id.lvReceipt);
        sharePreferenceLib = new SharePreferenceLib(this);
    }

    private void getOrder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
        Intent intent = getIntent();
        orderOngoingDTO = (OrderOngoingDTO) intent.getSerializableExtra("ORDERDTO");
        txtStoreName.setText(orderOngoingDTO.getListOrderServices().get(0).getServiceDTO().getStore().getName());
        String name = sharePreferenceLib.getUser().getCustomerName();
        String phone = sharePreferenceLib.getUser().getPhone();
        txtCustomerReceipt.setText(name);
        txtPhone.setText(" - " + phone);
        if (orderOngoingDTO.getAddress() != null) {
            txtAddress.setText(orderOngoingDTO.getAddress());
        }
        String timeTakes = orderOngoingDTO.getTakeTime();
        String timeDeliverys = orderOngoingDTO.getDeliveryTime();
        try {
            if (timeTakes!= null && !timeTake.equals("")){
                Date take = sdf.parse(timeTakes);
                timeTake.setText(sdf2.format(take));
            }
            if (timeDeliverys!= null && !timeDeliverys.equals("")){
                Date deliver = sdf.parse(timeDeliverys);
                timeDelivery.setText(sdf2.format(deliver));
            }

        } catch (ParseException e) {

        }
        float total = 0;
        ArrayList<ServiceDTO> listService = new ArrayList<>();
        for (OrderServiceDTO item : orderOngoingDTO.getListOrderServices()) {
            ServiceDTO dto = item.getServiceDTO();
            total += dto.getPrice();
            boolean flag = false;
            for (int i = 0; i < listService.size(); i++) {
                ServiceDTO temp = listService.get(i);
                if (temp.getId().equals(dto.getId())) {
                    flag = true;
                    int quantity = temp.getQuantity();
                    listService.get(i).setQuantity(quantity + dto.getQuantity());
                }
            }
            if (!flag) {
                listService.add(dto);
            }
        }
        txtTotalReceipt.setText(total + " VND");
        ServiceAdapter adapter = new ServiceAdapter(this, listService, 2);
        lvReceipt.setAdapter(adapter);
    }

    public void clickToBack(View view) {
        this.finish();
    }
}
