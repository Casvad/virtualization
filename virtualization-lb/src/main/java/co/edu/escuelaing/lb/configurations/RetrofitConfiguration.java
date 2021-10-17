package co.edu.escuelaing.lb.configurations;

import co.edu.escuelaing.lb.repositories.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RetrofitConfiguration {

    private static final List<Retrofit> retrofitClients = getRetrofitClients();

    private static final List<MessageRepository> messageRepositories =
            retrofitClients.stream().map(retrofit -> retrofit.create(MessageRepository.class)).collect(Collectors.toList());

    public static List<MessageRepository> getMessageRepositoryList(){
        return messageRepositories;
    }

    private static List<Retrofit> getRetrofitClients(){

        return Arrays.stream(System.getenv("API_URLS").split(","))
                .map(url ->  new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(
                        new ObjectMapper().registerModule(
                                new JavaTimeModule()
                        )
                ))
                .build()).collect(Collectors.toList());
    }
}
