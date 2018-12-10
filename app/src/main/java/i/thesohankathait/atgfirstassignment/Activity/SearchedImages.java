package i.thesohankathait.atgfirstassignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import java.util.List;

import i.thesohankathait.atgfirstassignment.Adapters.MyRecyclerViewAdapter;
import i.thesohankathait.atgfirstassignment.Interface.ApiInterface;
import i.thesohankathait.atgfirstassignment.Model.MyModel;
import i.thesohankathait.atgfirstassignment.Model.Photos;
import i.thesohankathait.atgfirstassignment.R;
import i.thesohankathait.atgfirstassignment.Retrofit.Retro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchedImages extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ApiInterface apiInterface;
    //    private MyModel myModel;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private List<MyModel> myModelList;
    private  String tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_images);

        Intent intent=getIntent();
        tag=intent.getStringExtra("TAG");
        recyclerView = findViewById(R.id.recyclerViewForSearcedImages);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface = Retro.getApiClient().create(ApiInterface.class);

        Call<MyModel> call = apiInterface.getSearchResult(tag);
        call.enqueue(new Callback<MyModel>() {
            @Override
            public void onResponse(Call<MyModel> call, Response<MyModel> response) {

                if (response.isSuccessful()) {
                    MyModel myModel=response.body();
                    Log.d("stat",myModel.getStat());
                    Photos myphotos=myModel.getPhotos();
                    myRecyclerViewAdapter=new MyRecyclerViewAdapter(myphotos.getPhoto(),getApplicationContext());
                    recyclerView.setAdapter(myRecyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<MyModel> call, Throwable t) {

                Log.d("satus",t.getMessage());
            }
        });
    }
}
