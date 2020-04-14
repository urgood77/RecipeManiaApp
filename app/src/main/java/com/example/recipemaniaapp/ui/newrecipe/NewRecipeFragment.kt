package com.example.recipemaniaapp.ui.newrecipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.recipemaniaapp.R
import kotlinx.android.synthetic.main.fragment_new_recipe.*

/**
 * A simple [Fragment] subclass.
 */
class NewRecipeFragment : Fragment(), View.OnClickListener {

    companion object {

        val EXTRA_NAME = "extra_name"
        val EXTRA_INFO = "extra_info"
        val EXTRA_INGREDIENT = "extra_ingredient"
        val EXTRA_STEP = "extra_step"
        val EXTRA_CATEGORY = "extra_category"
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnInformation: Button = view.findViewById(R.id.add_information_btn)
        val btnIngredient: Button = view.findViewById(R.id.add_ingredient_btn)
        val btnStep: Button = view.findViewById(R.id.add_steps_btn)
        val btnPhoto: Button = view.findViewById(R.id.add_photos_btn)
        val btnAddRecipe: Button = view.findViewById(R.id.add_new_recipe_btn)

        btnInformation.setOnClickListener(this)
        btnIngredient.setOnClickListener(this)
        btnStep.setOnClickListener(this)
        btnPhoto.setOnClickListener(this)
        btnAddRecipe.setOnClickListener(this)

        checker()

    }



    override fun onClick(v: View) {

        if (v.id== R.id.add_information_btn || v.id == R.id.add_photos_btn || v.id == R.id.add_ingredient_btn
            || v.id == R.id.add_ingredient_btn || v.id == R.id.add_steps_btn
        ) {
            val mAddFormFragment = AddFormFragment()

            val mBundle = Bundle()
            when (v.id) {
                R.id.add_photos_btn -> {
                    mBundle.putString(AddFormFragment.EXTRA_TITLE, "Add Information")
                    mBundle.putString(
                        AddFormFragment.EXTRA_FORM_DESC,
                        "Add a photo to your recipe. Recipe's photo makes your food look more interesting and appealing."
                    )
                    mBundle.putString(AddFormFragment.EXTRA_BUTTON, "Save Photo")
                }

                R.id.add_information_btn -> {

                    mBundle.putString(AddFormFragment.EXTRA_TITLE, "Add Information")
                    mBundle.putString(
                        AddFormFragment.EXTRA_FORM_DESC,
                        "Add food's information such as food description, " +
                                "nutrition fact, serving time, etc."
                    )
                    mBundle.putString(AddFormFragment.EXTRA_WRITE, "Write Information")
                    mBundle.putString(AddFormFragment.EXTRA_BUTTON, "Save Information")

                }

                R.id.add_ingredient_btn -> {
                    mBundle.putString(AddFormFragment.EXTRA_TITLE, "Add Ingredient")
                    mBundle.putString(
                        AddFormFragment.EXTRA_FORM_DESC,
                        "Add the ingredient needed for the food recipe."
                    )
                    mBundle.putString(AddFormFragment.EXTRA_WRITE, "Write Ingredient")
                    mBundle.putString(AddFormFragment.EXTRA_BUTTON, "Save Ingredient")


                }

                R.id.add_steps_btn -> {
                    mBundle.putString(AddFormFragment.EXTRA_TITLE, "Add Steps")
                    mBundle.putString(
                        AddFormFragment.EXTRA_FORM_DESC,
                        "Add dan describe food's preparation step. "
                    )
                    mBundle.putString(AddFormFragment.EXTRA_WRITE, "Write Steps")
                    mBundle.putString(AddFormFragment.EXTRA_BUTTON, "Save Steps")

                }
            }
            mBundle.putString(AddFormFragment.EXTRA_NAME, edt_recipe_name.text.toString())
            mBundle.putString(AddFormFragment.EXTRA_INFO, arguments?.getString(EXTRA_INFO))
            mBundle.putString(AddFormFragment.EXTRA_INGREDIENT, arguments?.getString(EXTRA_INGREDIENT))
            mBundle.putString(AddFormFragment.EXTRA_STEP, arguments?.getString(EXTRA_STEP))
            mBundle.putString(AddFormFragment.EXTRA_CATEGORY, category_spinner.selectedItemId.toString())

            val fm = activity!!.supportFragmentManager
            fm
                .beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(
                    R.id.frame_container,
                    mAddFormFragment,
                    AddFormFragment::class.java.simpleName
                )
//                .addToBackStack(null)
                .addToBackStack("Form")
                .commit()

            mAddFormFragment.arguments = mBundle
        } else if (v.id== R.id.add_new_recipe_btn) {
            val valid = validator()
            if(valid) {
                Toast.makeText(activity,"Save to Database.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checker() {
        val strName =  arguments?.getString(EXTRA_NAME)
        val strInfo = arguments?.getString(EXTRA_INFO)
        val strIngredient = arguments?.getString(EXTRA_INGREDIENT)
        val strStep = arguments?.getString(EXTRA_STEP)
        val strCategory = arguments?.getString(EXTRA_CATEGORY)

        if(strInfo != null) {
            tv_information.setText("Information (Saved)")
            add_information_btn.setText("Edit Information")
        }
        if(strIngredient != null) {
            tv_ingredient.setText("Ingredient (Saved)")
            add_ingredient_btn.setText("Edit Ingredient")
        }
        if(strStep != null) {
            tv_how_to_cook.setText("How to Cook (Saved)")
            add_steps_btn.setText("Edit Steps")
        }

        if(strCategory != null) {
            val category = strCategory.toInt()
            category_spinner.setSelection(category)
        }

        if(strName != null) edt_recipe_name.setText(strName)

    }

    private fun validator() : Boolean{
        var valid: Boolean = true
        val strName =  arguments?.getString(EXTRA_NAME)
        val strInfo = arguments?.getString(EXTRA_INFO)
        val strIngredient = arguments?.getString(EXTRA_INGREDIENT)
        val strStep = arguments?.getString(EXTRA_STEP)
        val strCategory = arguments?.getString(EXTRA_CATEGORY)

        if(strInfo == null || strIngredient == null || strStep == null) {
            valid = false
        } else {
            val recipeName = edt_recipe_name.text.toString().trim()
            if(recipeName.isEmpty()) {
                edt_recipe_name.error = "Recipe's name cannot be empty."
                valid = false
            }

            if(category_spinner.selectedItemId.toInt() == 0)
                valid = false
        }

        if(valid == false) {
            Toast.makeText(activity,"Your Recipe is not complete.", Toast.LENGTH_SHORT).show()
        }

        return valid

    }

}
