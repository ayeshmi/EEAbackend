package com.example.eeaassignment.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.auth0.android.jwt.JWT;
import com.example.eeaassignment.Login2;

public class AuthenticationHandler {
    public static String validate(Context context, String userRole){

        SharedPreferences sharedPreferences = context.getSharedPreferences("auth_details",Context.MODE_PRIVATE);

        String token = sharedPreferences.getString("token",null);
        Log.d("myTag", "This is my messagetoken"+token);
        String role = sharedPreferences.getString("role",null);

        Log.i("user_role",userRole);
        if(token != null)Log.i("token",token);
        if(role != null)Log.i("role",role);

        if(token != null){

            JWT jwtToken = new JWT(token);
            boolean isExpired = jwtToken.isExpired(10);

            if(isExpired){

                AuthenticationHandler.logout(context);
                return "Token expired";
            }
            else if(userRole.equals("all")){

                return role;
            }

        }

        return null;

    }

    public static void logout(Context context){

//        Remove token and role from shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("auth_details", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", null);
        editor.putString("role", null);
        editor.apply();

        Intent intent = new Intent(context, Login2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity)context).finish();

    }

}
