package online.earnmoney.com.makemoney_earnonline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import online.earnmoney.com.makemoney_earnonline.HomeFragment.BitcoinPriceReceiver;


/**
 * Created by Abhishek on 24/11/2017.
 */

public class NetworkUtils {

    public static final String ZEBPAY_URL = "https://www.zebapi.com/api/v1/market/ticker/btc/inr";
    public static final String BITCOIN_ZEBPAY = "zebpay_coin";
    public static final String BITCOIN_UNOCOIN = "uno_coin";

    public static void getUnoCoinRates(final Context context) {

        String url = "https://www.unocoin.com/trade?all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {


        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void getZebPayRates(final Context context) {

        final Bundle bundle = new Bundle();
        String url = ZEBPAY_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String buy = jsonObject.optString(ConstantUtils.BUY_ZEBPAY);
                            String sell = jsonObject.optString(ConstantUtils.SELL_ZEBPAY);
                            String market = jsonObject.optString(ConstantUtils.MARKET_ZEBPAY);
                            String currency = jsonObject.optString(ConstantUtils.CURRENCY_ZEBPAY);
                            String vol = jsonObject.optString(ConstantUtils.VOLUME_ZEBPAY);

                            bundle.putString(ConstantUtils.BUY_ZEBPAY, buy);
                            bundle.putString(ConstantUtils.CURRENCY_ZEBPAY, currency);
                            bundle.putString(ConstantUtils.MARKET_ZEBPAY, market);
                            bundle.putString(ConstantUtils.SELL_ZEBPAY, sell);
                            bundle.putString(ConstantUtils.VOLUME_ZEBPAY, vol);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, BitcoinPriceReceiver.class);
                        intent.setAction(BITCOIN_ZEBPAY);
                        intent.putExtras(bundle);
                        context.sendBroadcast(intent);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {


        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}

