package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllUser extends AppCompatActivity {
    private List<User>  users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_user);
        Call<List<User>> getAllUserCall = ApiClient.getUserService().getAllUser();
        getAllUserCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                users = response.body();

                if(users != null){
                    final ListView lv = (ListView) findViewById(R.id.user_list);
                    lv.setAdapter(new UserAdapter(ViewAllUser.this, users));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                            ListItem user = (ListItem) lv.getItemAtPosition(position);
                            Toast.makeText(ViewAllUser.this, "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{

                    Toast.makeText(ViewAllUser.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

                }
                // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                Toast.makeText(ViewAllUser.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();

            }
        });
        //ArrayList userList = results;

    }

}
