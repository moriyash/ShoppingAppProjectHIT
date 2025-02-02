package com.example.shoppingmanagerapp.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shoppingmanagerapp.Adapters.ProductAdapter;
import com.example.shoppingmanagerapp.Classes.Product;
import com.example.shoppingmanagerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    private TextView tvWelcome;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> productList;
    private Button btnSubmitProducts;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private void loadShoppingList() {
        if (mAuth.getCurrentUser() == null) return;

        String userId = mAuth.getCurrentUser().getUid();
        database.getReference("users").child(userId).child("shoppingList")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        productList.clear();

                        if (snapshot.exists()) {
                            for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                                Product product = itemSnapshot.getValue(Product.class);
                                productList.add(product);
                            }
                            adapter.notifyDataSetChanged();
                            Toast.makeText(ProductListActivity.this, "Shopping list loaded!", Toast.LENGTH_SHORT).show();
                        } else {
                            loadInitialProducts();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ProductListActivity.this, "Failed to load shopping list.", Toast.LENGTH_SHORT).show();
                    }
                });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        tvWelcome = findViewById(R.id.tv_welcome);
        recyclerView = findViewById(R.id.recycler_view);
        btnSubmitProducts = findViewById(R.id.btn_submit_products);

        String username = mAuth.getCurrentUser().getEmail();
        tvWelcome.setText("Welcome, " + username + "!");

        productList = new ArrayList<>();
        loadInitialProducts();

        adapter = new ProductAdapter(productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnSubmitProducts.setOnClickListener(view -> {
            if (mAuth.getCurrentUser() == null) {
                Toast.makeText(this, "User is not logged in!", Toast.LENGTH_SHORT).show();
                return;
            }

            String userId = mAuth.getCurrentUser().getUid();
            database.getReference("users").child(userId).child("shoppingList").setValue(productList)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Shopping list saved!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Failed to save list.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

    }

    private void loadInitialProducts() {
        productList.add(new Product("Apple", R.drawable.apple, 0));
        productList.add(new Product("Banana", R.drawable.banana, 0));
        productList.add(new Product("Orange", R.drawable.orange, 0));
        productList.add(new Product("Milk", R.drawable.milk, 0));
        productList.add(new Product("Bread", R.drawable.bread, 0));
        productList.add(new Product("Cheese", R.drawable.cheese, 0));
        productList.add(new Product("Eggs", R.drawable.eggs, 0));
        productList.add(new Product("Tomato", R.drawable.tomato, 0));
    }

}
