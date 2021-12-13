package com.example.eeaassignment;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

public class NavBarHandler {
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

                Intent intent8 = new Intent(context, ContactUs.class);
                intent8.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent8);
                break;

            case R.id.logout:

                Intent intent9 = new Intent(context, ContactUs.class);
                intent9.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent9);
                break;


        }

    }
}
