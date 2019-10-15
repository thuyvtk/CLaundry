package thuyvtk.activity.laundry_customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import thuyvtk.activity.laundry_customer.R;
import thuyvtk.activity.laundry_customer.model.OrderDTO;

public class OrderServiceAdapter extends RecyclerView.Adapter<OrderServiceAdapter.OrderServiceViewHolder> {
    private List<OrderDTO> listOrder;
    private Context context;

    public OrderServiceAdapter(Context context, List<OrderDTO> listOrder) {
        this.listOrder = listOrder;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_sub, parent, false);
        return new OrderServiceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderServiceViewHolder holder, int position) {
        OrderDTO orderDTO = listOrder.get(position);
        holder.txt_status.setText(orderDTO.getStatus());
        holder.txt_orderId.setText(orderDTO.getOrderId());
        // toDo get store profile
//        Picasso.with(context).load(orderDTO.getCustomerId()).into(holder.service_img);
        String item_price = orderDTO.getListOrderServices().size() + " items - " + orderDTO.getTotalPrice() + " VND";
        holder.txt_item_price.setText(item_price);

    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public static class OrderServiceViewHolder extends RecyclerView.ViewHolder {
        CardView cv_order;
        TextView txt_status, txt_orderId, txt_storeName, txt_item_price;
        ImageView img_storeProfile;

        public OrderServiceViewHolder(View serviceView) {
            super(serviceView);
            cv_order = serviceView.findViewById(R.id.cv_order);
            txt_status = serviceView.findViewById(R.id.txt_status);
            txt_orderId = serviceView.findViewById(R.id.txt_orderId);
            img_storeProfile = serviceView.findViewById(R.id.img_storeProfile);
            txt_storeName = serviceView.findViewById(R.id.txt_storeName);
            txt_item_price = serviceView.findViewById(R.id.txt_item_price);
        }
    }
}
