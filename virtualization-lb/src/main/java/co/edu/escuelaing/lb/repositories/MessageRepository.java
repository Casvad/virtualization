package co.edu.escuelaing.lb.repositories;

import co.edu.escuelaing.lb.models.Message;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface MessageRepository {

    @GET("messages")
    Call<List<Message>> getMessages();

    @POST("messages")
    Call<Message> createMessage(@Body Message message);
}
