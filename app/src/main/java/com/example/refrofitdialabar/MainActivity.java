package com.example.refrofitdialabar;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.refrofitdialabar.Api_interfaces.JsonPlaceHolderApi;
import com.example.refrofitdialabar.Models.register;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textViewResult=findViewById(R.id.textview);

        createPost();

    }


    void createPost(){
        register reg = new register("manisha","manisha@gmail.com","m02101993");

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.2.58:8000/")

                .addConverterFactory(GsonConverterFactory.create())

                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<register> call= jsonPlaceHolderApi.createPost(reg);

        call.enqueue(new Callback<register>() {
            @Override
            public void onResponse(Call<register> call, Response<register> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: "+ response.code());
                    return;
                }
                register postResponse = response.body();

                String content="";
              //  content+="Code: "+ response.code()+"\n";
                content+="Name: "+postResponse.getName()+"\n";
                content+="Email: "+postResponse.getEmail()+"\n";
                content+="Password: "+postResponse.getPassword()+"\n";


                textViewResult.append(content);





            }

            @Override
            public void onFailure(Call<register> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
    public void getPosts(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.2.58:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<register>> call=jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<register>>() {
            @Override
            public void onResponse(Call<List<register>> call, Response<List<register>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code:"+response.code());
                    return;
                }
                List<register> posts=response.body();
                for (register post: posts){
                    String content="";

                    content += "Name: " +post.getName() +"\n";
                    content += "Email: " +post.getEmail() +"\n";
                    content += "Password: " +post.getPassword() +"\n";

                    textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<register>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }
}