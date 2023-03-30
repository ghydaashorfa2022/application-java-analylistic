package com.example.myapplicationanalystic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationanalystic.Adapter.adapterNots;
import com.example.myapplicationanalystic.model.nots;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.temporal.TemporalQueries;
import java.util.ArrayList;

public class DescriptionActivity2 extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    ArrayList<nots> itemsdescription;
    TextView textView;
    TextView textView1;
    private FirebaseAnalytics mfirebaseAnalystic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description2);
        String id = getIntent().getStringExtra("id");
        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView1);
        itemsdescription= new ArrayList<nots>();
        Toast.makeText(this, "nada : " + id, Toast.LENGTH_SHORT).show();
        mfirebaseAnalystic=FirebaseAnalytics.getInstance(this);
        GetAllDescription();
        screenTrack("Description");
    }
    private void GetAllDescription(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String id = getIntent().getStringExtra("id");
        db.collection("Note").whereEqualTo(FieldPath.documentId() , id).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()) {
                    Log.d("drn", "onSuccess: LIST EMPTY");
                    Log.d("nada1212", "onSuccess: not found id  :");
                    Toast.makeText(getApplicationContext(), "empty ", Toast.LENGTH_SHORT).show();

                    return;
                } else {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if (documentSnapshot.exists()) {
                            String id = documentSnapshot.getId();
                            String itemsHeader = documentSnapshot.getString("noteHeader");
                            String itembody = documentSnapshot.getString("noteBody");
                            Log.e("nadaHeader", itemsHeader);
                            Toast.makeText(getApplicationContext(), itemsHeader, Toast.LENGTH_SHORT).show();
                              textView.setText(itembody);
                              textView1.setText(itemsHeader);


                        }
                    }
                }
            }



}).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("nada FAULI", "get failed with ");
                Toast.makeText(getApplicationContext(), "FAILD", Toast.LENGTH_SHORT).show();



            }
        });


    }
    public void screenTrack(String name){
        Bundle bundle=new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,name);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS,name);
        mfirebaseAnalystic.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,bundle);
    }
}
