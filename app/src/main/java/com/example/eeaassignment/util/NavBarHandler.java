package com.example.eeaassignment.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eeaassignment.AdminHomePage;
import com.example.eeaassignment.PharmacistHomepage;
import com.example.eeaassignment.ViewCancelOrders;
import com.example.eeaassignment.ViewCart;
import com.example.eeaassignment.ContactUs;
import com.example.eeaassignment.R;
import com.example.eeaassignment.UserHomePage;
import com.example.eeaassignment.ViewAllContactUs;
import com.example.eeaassignment.ViewAllItems;
import com.example.eeaassignment.ViewAllPharmacists;
import com.example.eeaassignment.ViewAllUser;
import com.example.eeaassignment.ViewCompletedOrders;
import com.example.eeaassignment.ViewContactUsPerUser;
import com.example.eeaassignment.ViewOrderDetails;
import com.example.eeaassignment.ViewPendingOrders;
import com.example.eeaassignment.ViewProfileDetails;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NavBarHandler {

    public static void handleHeaderAndMenu(NavigationView navigationView,String username,String email, String image,String role){
        View hView =  navigationView.inflateHeaderView(R.layout.nav_header);
        ImageView imgvw = (ImageView)hView.findViewById(R.id.profile);
        Bitmap bimage=null;
        InputStream in= null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL("http://192.168.1.3:8080/api/auth/video/"+image);
            bimage  = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            imgvw.setImageBitmap(bimage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView emailText = (TextView)hView.findViewById(R.id.email);
        TextView usernameText = (TextView)hView.findViewById(R.id.username);
        emailText.setText(email);
        usernameText.setText(username);

        navigationView.getMenu().clear();
        if(role.equals("ROLE_PHARMACIST")){
            navigationView.inflateMenu(R.menu.pharmacist_menu);
        }else if(role.equals("ROLE_ADMIN")){
            navigationView.inflateMenu(R.menu.admin_menu);
        }else {
            navigationView.inflateMenu(R.menu.user_menu);
        }

    }


    public static void navBarHandler( MenuItem item, Context context){
        String pageName=null;

        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(context, AdminHomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                break;

            case R.id.contactUs:
                Intent intent1 = new Intent(context, ContactUs.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent1);

                break;

            case R.id.aboutUs:

                Intent intent2 = new Intent(context, ViewAllItems.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent2);
                break;

            case R.id.manage_item:

                Intent intent3 = new Intent(context, ViewAllItems.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent3);
                break;

            case R.id.manage_user:

                Intent intent4 = new Intent(context, ViewAllUser.class);
                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent4);
                break;

            case R.id.manage_pharmacist:

                Intent intent5 = new Intent(context, ViewAllPharmacists.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent5);
                break;

            case R.id.manage_orders:

                Intent intent6= new Intent(context, ViewAllContactUs.class);
                intent6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent6);
                break;

            case R.id.manage_contactUs:

                Intent intent7 = new Intent(context, ViewAllContactUs.class);
                intent7.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent7);
                break;

            case R.id.profile:

                Intent intent8 = new Intent(context, ViewProfileDetails.class);
                intent8.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent8);
                break;

            case R.id.logout:

                AuthenticationHandler.logout(context);
                break;


        }

    }

    public static void navBarHandlerUser( MenuItem item, Context context){
        String pageName=null;

        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(context, UserHomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                break;

            case R.id.contactUs:
                Intent intent1 = new Intent(context, ContactUs.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent1);

                break;

            case R.id.aboutUs:

                Intent intent2 = new Intent(context, ViewAllItems.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent2);
                break;

            case R.id.view_item:

                Intent intent3 = new Intent(context, UserHomePage.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent3);
                break;

            case R.id.view_cart:

                Intent intent4 = new Intent(context, ViewCart.class);
                intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent4);
                break;

            case R.id.view_contactus:

                Intent intent5 = new Intent(context, ViewContactUsPerUser.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent5);
                break;


            case R.id.profile:

                Intent intent8 = new Intent(context, ViewProfileDetails.class);
                intent8.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent8);
                break;

            case R.id.logout:

                AuthenticationHandler.logout(context);
                break;
            case R.id.view_cancel:

                Intent intent9 = new Intent(context, ViewCancelOrders.class);
                intent9.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent9);
                break;
            case R.id.view_completed:

                Intent intent10 = new Intent(context, ViewCompletedOrders.class);
                intent10.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent10);
                break;

            case R.id.view_pending:

                Intent intent11= new Intent(context, ViewPendingOrders.class);
                intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent11);
                break;

            case R.id.view_orders:

                Intent intent12= new Intent(context, ViewOrderDetails.class);
                intent12.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent12);
                break;
        }

    }

    public static void navBarHandlerPharmacist( MenuItem item, Context context){
        String pageName=null;

        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(context, PharmacistHomepage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                break;

            case R.id.contactUs:
                Intent intent1 = new Intent(context, ContactUs.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent1);

                break;

            case R.id.aboutUs:

                Intent intent2 = new Intent(context, ViewAllItems.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent2);
                break;

            case R.id.view_item:

                Intent intent3 = new Intent(context, UserHomePage.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent3);
                break;



            case R.id.view_contactus:

                Intent intent5 = new Intent(context, ViewContactUsPerUser.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent5);
                break;


            case R.id.profile:

                Intent intent8 = new Intent(context, ViewProfileDetails.class);
                intent8.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent8);
                break;

            case R.id.logout:

                AuthenticationHandler.logout(context);
                break;
            case R.id.manage_item:

                Intent intent9 = new Intent(context, ViewAllItems.class);
                intent9.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent9);
                break;
            case R.id.manage_user:

                Intent intent10 = new Intent(context, ViewAllUser.class);
                intent10.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent10);
                break;

            case R.id.manage_contactUs:

                Intent intent11= new Intent(context, ViewAllContactUs.class);
                intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent11);
                break;

        }

    }
}
