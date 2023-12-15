package quizmanager.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static RetrofitSingleton instance = null;
    public static final String BASE_URL = "http://localhost:8080/";

    private QuizService quizService;

    public static RetrofitSingleton getInstance() {
        if (instance == null) {
            instance = new RetrofitSingleton();
        }

        return instance;
    }

    // TODO
    //  - chyba tu dodać parsowanie jsona?
    private RetrofitSingleton() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.quizService = retrofit.create(QuizService.class);

        // future services go here
    }

    public QuizService getQuizService() {
        return this.quizService;
    }

}
