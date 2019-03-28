package de.openknowledge.todo.domain;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.openknowledge.todo.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpClientFactory {

    /**
     * creates http client depending on build variant
     * @return http client
     */
    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if(BuildConfig.useMockService) {
                    Log.d(HttpClientFactory.class.getSimpleName(), "retrieve answer from mock web service");
                    ResponseBody body = ResponseBody.create(MediaType.get("application/json"), ANSWER);
                    return new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).message(ANSWER).code(200).body(body).build();
                } else {
                    Log.d(HttpClientFactory.class.getSimpleName(), "retrieve answer from real web service");
                    Request originalRequest = chain.request();
                    return chain.proceed(originalRequest);
                }
            }
        }).build();
    }

    private static final String ANSWER = createMockAnswer();


    // helper method to create "remote" web service answer
    private static String createMockAnswer() {

        List<Todo> todoList = new ArrayList<>();

        Todo todoOne = new Todo(UUID.randomUUID().toString(), "Go shopping", "Buy a new phone", "Looking for a new mobile phone and some accessories.", TodoType.SHOPPING);
        Todo todoTwo = new Todo(UUID.randomUUID().toString(), "Mother", "Call my mother", "It' s mothers birthday. So better do not forget to call her (+49 171 555 555)!", TodoType.CALL);
        Todo todoThree = new Todo(UUID.randomUUID().toString(), "Make Sports", "Hansefit", "A cardio training a day keeps the doctor away.", TodoType.SPORT);
        Todo todoFour = new Todo(UUID.randomUUID().toString(), "Remote ToDO", "I am from the web", "Synchronised from Okhttp and Retrofit", TodoType.PLAY);

        todoList.add(todoOne);
        todoList.add(todoTwo);
        todoList.add(todoThree);
        todoList.add(todoFour);

        Gson gson = new GsonBuilder().create();
        return gson.toJson(todoList);
    }

}
