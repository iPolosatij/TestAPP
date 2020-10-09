package space.digitallab.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import space.digitallab.testapp.SQLiteData.DBHelper;
import space.digitallab.testapp.SQLiteData.DBinterface;
import space.digitallab.testapp.retrofitData.DataCall;
import space.digitallab.testapp.retrofitData.Datum;
import space.digitallab.testapp.retrofitData.DatumAdapter;
import space.digitallab.testapp.retrofitData.Repo;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Datum> datum;
    ArrayList<Datum> datumArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.item_list);

        if(isOnline()) {
            DBinterface dBinterface = new DBinterface(new DBHelper(this));
            dBinterface.dbClear();

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    DataCall client = retrofit.create(DataCall.class);
    Call<Repo> call = client.messages();

    call.enqueue(new Callback<Repo>() {
        @Override
        public void onResponse(Call<Repo> call, Response<Repo> response) {
            datum = response.body().getData();
            listView.setAdapter(new DatumAdapter(MainActivity.this, datum));
        }

        @Override
        public void onFailure(Call<Repo> call, Throwable t) {
            Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
        }
    });
        }else{

            DBinterface dBinterface = new DBinterface(new DBHelper(this));
            dBinterface.dataRead(datumArrayList);
            listView.setAdapter(new DatumAdapter(MainActivity.this, datumArrayList));
            datum = datumArrayList;
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    public void onClick(View v) {

        String text = ((TextView)v).getText().toString();
        Datum m = datum.get(0);


        for(int i = 0; i < datum.size(); i++){
            if(text.equals(datum.get(i).getFirstName() + " " + datum.get(i).getLastName())){
                m = datum.get(i);
            }
        }
                Intent intent = new Intent(MainActivity.this, UserCardAct.class);
                intent.putExtra("email", m.getEmail());
                intent.putExtra("picture", m.getAvatar());
                startActivity(intent);
    }


    public void about(View v) {

        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }
}
