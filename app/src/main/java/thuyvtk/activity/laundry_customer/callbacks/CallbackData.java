package thuyvtk.activity.laundry_customer.callbacks;

public interface CallbackData<T> {
    void onSuccess(T t);

    void onFail(String message);
}
