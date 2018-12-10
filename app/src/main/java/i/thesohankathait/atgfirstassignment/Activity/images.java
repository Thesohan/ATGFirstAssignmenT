package i.thesohankathait.atgfirstassignment.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import i.thesohankathait.atgfirstassignment.Adapters.MyRecyclerViewAdapter;
import i.thesohankathait.atgfirstassignment.Interface.ApiInterface;
import i.thesohankathait.atgfirstassignment.Model.MyModel;
import i.thesohankathait.atgfirstassignment.Model.Photo;
import i.thesohankathait.atgfirstassignment.Model.Photos;
import i.thesohankathait.atgfirstassignment.R;
import i.thesohankathait.atgfirstassignment.Retrofit.Retro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class images extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ApiInterface apiInterface;
    private EditText searchEditText;
//    private MyModel myModel;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private List<MyModel> myModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        bottomNavigationView=findViewById(R.id.navigation);
        searchEditText=findViewById(R.id.searchEditText);
        recyclerView=findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface=Retro.getApiClient().create(ApiInterface.class);
        Call<MyModel> call=apiInterface.getMyModel();
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

                Log.i("fail","this test has been failed"+t.getMessage());
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // handle desired action here
                // One possibility of action is to replace the contents above the nav bar
                // return true if you want the item to be displayed as the selected item

                switch (item.getItemId()) {
                    case R.id.search_button:

                        if (!searchEditText.getText().toString().trim().equals("")) {
                            Intent intent = new Intent(images.this, SearchedImages.class);
                            intent.putExtra("TAG", searchEditText.getText().toString());
                            startActivity(intent);
                        } else {
                            searchEditText.setError("Enter something!");
                        }

                        break;
                }

                return true;

            }
        });


    }


}
