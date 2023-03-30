package com.example.myapplicationanalystic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplicationanalystic.Adapter.adapterCategory;
import com.example.myapplicationanalystic.model.catagoryy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity  implements adapterCategory.ItemClickListene {
    private FirebaseFirestore firebaseFirestore;
    ArrayList<catagoryy> items;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView rv;
    adapterCategory adapter;
    private FirebaseAnalytics mfirebaseAnalystic;
    Calendar clender = Calendar.getInstance();
    int houres = clender.get(Calendar.HOUR);
    int minutes = clender.get(Calendar.MINUTE);
    int second = clender.get(Calendar.SECOND);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rvRest);
        items = new ArrayList<catagoryy>();
        adapter = new adapterCategory(this, items, this);
        mfirebaseAnalystic = FirebaseAnalytics.getInstance(this);
        GetAllUserss();
        screenTrack("Main Activity");
    }

    private void GetAllUserss() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Category").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d("drn", "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                if (documentSnapshot.exists()) {
                                    String id = documentSnapshot.getId();
                                    String category1 = documentSnapshot.getString("categoryName");
                                    Log.e("nada", category1);

                                    catagoryy user = new catagoryy(category1, id);
                                    items.add(user);
                                    rv.setLayoutManager(layoutManager);
                                    rv.setHasFixedSize(true);
                                    rv.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    Log.e("LogDATA", items.toString());
                                }
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("LogDATA", "get failed with ");


            }
        });
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void onItemClick(int adapterPosition, String id) {
        btnEvent("id", "Catagory", "textview");

    }

    public void screenTrack(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, name);
        mfirebaseAnalystic.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }

    public void btnEvent(String id, String name, String contentType) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        mfirebaseAnalystic.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    protected void onPause() {
        super.onPause();
        int houres2 = clender.get(Calendar.HOUR);
        int minutes2 = clender.get(Calendar.MINUTE);
        int second2 = clender.get(Calendar.SECOND);
        int h = houres2 - houres;
        int m = minutes2 - minutes;
        int s = second2 - second;
        HashMap<String, Object> users = new HashMap<>();
        users.put("name", "ghydaa");
        users.put("houres", h);
        users.put("minutes", m);
        users.put("second", s);
        Log.e("hour", String.valueOf(h));
        FirebaseFirestore dp = FirebaseFirestore.getInstance();
        dp.collection("users").add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                                   @Override
                                                                   public void onSuccess(DocumentReference documentReference) {
                                                                       Log.e("TAG", "Data added successfully to database");
                                                                   }
                                                               }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG", "Failed to add database");

            }
        });

    }
}