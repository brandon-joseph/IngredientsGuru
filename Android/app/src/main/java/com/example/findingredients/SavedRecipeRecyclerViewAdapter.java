package com.example.findingredients;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class SavedRecipeRecyclerViewAdapter extends
        RecyclerView.Adapter<SavedRecipeRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Recipe> recipeList = new ArrayList<>();
    private Context context;

    public SavedRecipeRecyclerViewAdapter(ArrayList<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_recipe_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),recipeActivity.class);
                int pos = (int) view.getTag();
                Recipe recipe = recipeList.get(pos);
                intent.putExtra("title", recipe.getName());
                intent.putExtra("url", recipe.getUrl());
                intent.putExtra("imgUrl", recipe.getImageUrl());
                intent.putParcelableArrayListExtra("ingredients", recipe.getIngredients());

                startActivity(context,intent,null);
                notifyDataSetChanged(); // Make sure to call this everytime the data changes
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.recipeName.setText(recipe.getName());
        Glide.with(context).load(recipe.getImageUrl()).into(holder.img);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView recipeName;
        ConstraintLayout parentLayout;
        TextView url;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            recipeName = itemView.findViewById(R.id.ingredientAmount);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
