package com.example.myapplicationanalystic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplicationanalystic.Adapter.adapterCategory;
import com.example.myapplicationanalystic.Adapter.adapterNots;
import com.example.myapplicationanalystic.model.catagoryy;
import com.example.myapplicationanalystic.model.nots;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;

public class NotsActivity2 extends AppCompatActivity implements  adapterNots.ItemClickListener2  {
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAnalytics mfirebaseAnalystic;
    ArrayList<nots> itemsnots;
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    RecyclerView rvnots;
    adapterNots adapter;
    Calendar clender=Calendar.getInstance();
    int houres=clender.get(Calendar.HOUR);
    int minutes=clender.get(Calendar.MINUTE);
    int second=clender.get(Calendar.SECOND);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nots2);
        rvnots = findViewById(R.id.rvnots);
        itemsnots = new ArrayList<nots>();
         adapter = new adapterNots(this,itemsnots,this);
        mfirebaseAnalystic=FirebaseAnalytics.getInstance(this);
         GetAllUserss();
        screenTrack("Note");
    }
    private void GetAllUserss() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String id = getIntent().getStringExtra("id");
        Log.e("nadaid" ,id ) ;
        db.collection("Note").whereEqualTo("category_id",id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Log.d("drn", "onSuccess: LIST EMPTY");
                    Log.d("nada1212", "onSuccess: not found id  :");

                    return;
                } else {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if (documentSnapshot.exists()) {
                            String id = documentSnapshot.getId();
                            String itemsHeader = documentSnapshot.getString("noteHeader");
                            String itembody=documentSnapshot.getString("noteBody");
                            String image=documentSnapshot.getString("image");
                           // Log.e("nada" , itemsHeader) ;
                             nots note= new nots(itemsHeader,itembody,id,image);
                             itemsnots.add(note);




                            rvnots.setLayoutManager(layoutManager);
                            rvnots.setHasFixedSize(true);
                            rvnots.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            Log.e("LogDATA", itemsnots.toString());
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
    public void onItemClick2(int position, String id) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this , DescriptionActivity2.class) ;
        intent.putExtra("id"  , id );
        startActivity(intent);
        btnEvent("id","Note","textview");

    }
    public void screenTrack(String name){
        Bundle bundle=new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,name);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS,name);
        mfirebaseAnalystic.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,bundle);
    }
    public void btnEvent(String id,String name,String contentType){
        Bundle bundle=new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,id);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,contentType);
        mfirebaseAnalystic.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle);
    }
}