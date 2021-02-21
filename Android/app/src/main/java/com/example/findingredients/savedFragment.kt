package com.example.findingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Use the [savedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class savedFragment: Fragment() {

    private val recipeList: MutableList<Recipe> = ArrayList()

    companion object {
        fun newInstance(): savedFragment {
            return savedFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView: View =
            inflater.inflate(R.layout.fragment_saved, container, false)

        // use activity-specific methods

        initRecyclerView(rootView)

        return rootView
    }

    fun initRecyclerView(root : View) {

        addDummyRecipes()
        addDummyRecipes()

        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        val recyclerViewAdapter = SavedRecipeRecyclerViewAdapter(recipeList as java.util.ArrayList<Recipe>?,
            activity)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    fun addDummyRecipes() {
        recipeList.add(
            Recipe(
                "Fruit Cake", arrayListOf(
                    Ingredient(0,"Baking Soda",100),
                    Ingredient(1,"Pie Crust",10),
                    Ingredient(2, "Flour", 20),
                    Ingredient(3,"Sugar",100),
                    Ingredient(4,"Strawberry",10),
                    Ingredient(5, "Blueberry", 20),
                    Ingredient(6,"Banana",100),
                    Ingredient(7,"Salt",10),
                    Ingredient(8, "Frosting", 20),
                ),
                "https://mydelices.net/wp-content/uploads/2020/02/18Reasons-Boozy-2.jpg",
                "https://tatyanaseverydayfood.com/recipe-items/summer-sangria-cake/"
                )
            )
        recipeList.add(
            Recipe(
                "Sushi", arrayListOf(
                    Ingredient(0,"Rice",100),
                    Ingredient(1,"Soy sauce",10),
                    Ingredient(2, "Vinegar", 20),
                    Ingredient(3,"Nori",100),
                    Ingredient(4,"Salmon",10),
                    Ingredient(5, "Salt", 20),
                    Ingredient(6,"Sugar",100),
                    Ingredient(7,"Mirin",10),
                    Ingredient(8, "Avocado", 20),
                ),
                "https://www.fifteenspatulas.com/wp-content/uploads/2016/06/Homemade-Sushi-2-640x959.jpg",
                "https://www.fifteenspatulas.com/homemade-sushi/"
            )
        )
        recipeList.add(
            Recipe(
                "Pho", arrayListOf(
                    Ingredient(0,"Beef Bone",100),
                    Ingredient(1,"Soy sauce",10),
                    Ingredient(2, "Vinegar", 20),
                    Ingredient(3,"Cardamom",100),
                    Ingredient(4,"Cinnamon",10),
                    Ingredient(5, "Salt", 20),
                    Ingredient(6,"Sugar",100),
                    Ingredient(7,"Mirin",10),
                    Ingredient(8, "Fish Sauce", 20),
                ),
                "https://www.recipetineats.com/wp-content/uploads/2019/04/Beef-Pho_6.jpg?resize=650,910",
                "https://www.recipetineats.com/vietnamese-pho-recipe/"
            )
        )
        recipeList.add(
            Recipe(
                "Doenjang Chigae", arrayListOf(
                    Ingredient(0,"Doenjang",100),
                    Ingredient(1,"Soy sauce",10),
                    Ingredient(2, "Anchovy", 20),
                    Ingredient(3,"Tofu",100),
                    Ingredient(4,"Squash",10),
                    Ingredient(5, "Salt", 20),
                    Ingredient(6,"Sugar",100),
                    Ingredient(7,"Scallion",10),
                    Ingredient(8, "Fish Sauce", 20),
                ),
                "https://mykoreankitchen.com/wp-content/uploads/2020/02/1.-Doenjang-Jjigae.jpg",
                "https://mykoreankitchen.com/doenjang-jjigae/"
            )
        )
    }
}