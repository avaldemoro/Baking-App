package co.asterv.ad_bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.asterv.ad_bakingapp.adapters.IngredientsAdapter;
import co.asterv.ad_bakingapp.adapters.StepsAdapter;
import co.asterv.ad_bakingapp.model.Recipe;
import co.asterv.ad_bakingapp.model.Step;
import co.asterv.ad_bakingapp.utils.Constant;
import co.asterv.ad_bakingapp.widget.UpdateWidgetService;

public class RecipeDetailFragment extends Fragment{
    private Recipe recipe;
    private Parcelable mListState;
    @BindView(R.id.ingredientsRecyclerView) RecyclerView mIngredientsRecyclerView;
    @BindView(R.id.stepsRecyclerView) RecyclerView mStepsRecyclerView;
    @BindView(R.id.recipeTitle) TextView tvRecipeTitle;
    @BindView (R.id.servingsAmountTextView) TextView tvRecipeServings;
    @BindView(R.id.ingredientsCardView) CardView cvIngredients;
    @BindView(R.id.addRecipeWidgetButton) Button addWidgetButton;
    private static RecyclerView.Adapter mIngredientsAdapter;
    private static RecyclerView.Adapter mStepsAdapter;
    private RecyclerView.LayoutManager mIngredientLayoutManager;
    private RecyclerView.LayoutManager mStepLayoutManager;
    RecipeDetailFragment.OnStepSelectedListener mCallback;

    public RecipeDetailFragment() { }

    public interface OnStepSelectedListener {
        void onStepSelected(List<Step> steps, int position);
    }

    /***
     * Thanks to https://stackoverflow.com/questions/28236390/recyclerview-store-restore-state-between-activities
     * for the code to store/restore RecyclerView state between activities
     * ***/
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        // Save list state for Steps
        mListState = mStepLayoutManager.onSaveInstanceState();
        state.putParcelable(Constant.STEP_LIST_STATE_KEY, mListState);
    }

    public void onRestoreInstanceState(Bundle outState) {
        // Retrieve list state and list/item positions
        if (outState != null) {
            mListState = outState.getParcelable (Constant.STEP_LIST_STATE_KEY);

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mListState != null) {
            mStepLayoutManager.onRestoreInstanceState(mListState);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RecipeDetailFragment.OnStepSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        recipe = getArguments().getParcelable (Constant.RECIPE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this, view);

        if(((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle (Constant.DETAILS_TITLE);
        }

        // Title & Servings
        tvRecipeServings.setText (String.valueOf (recipe.getServings ()));
        tvRecipeTitle.setText (recipe.getRecipeName ());


        // Widget Button
        addWidgetButton.setOnClickListener(view1 -> {
            UpdateWidgetService.startUpdateWidgetService (getContext (), recipe);
            Toast.makeText (getActivity (), "Added " + recipe.getRecipeName () + " to Widget.", Toast.LENGTH_SHORT).show ();
        });

        // Ingredients Recycler View
        setUpIngredientsRecyclerView ();

        // Steps Recycler View
        setUpStepsRecyclerView ();



        return view;
    }

    private void setUpIngredientsRecyclerView() {
        mIngredientLayoutManager = new LinearLayoutManager (getActivity ().getApplicationContext ());
        mIngredientsRecyclerView.setLayoutManager (mIngredientLayoutManager);

        mIngredientsAdapter = new IngredientsAdapter (recipe.getIngredients (), getContext ());
        mIngredientsRecyclerView.setAdapter (mIngredientsAdapter);
        mIngredientsRecyclerView.setNestedScrollingEnabled (false);


    }
    private void setUpStepsRecyclerView() {
        mStepLayoutManager = new LinearLayoutManager (getActivity ().getApplicationContext ());
        mStepsRecyclerView.setLayoutManager (mStepLayoutManager);

        mStepsAdapter = new StepsAdapter (recipe.getSteps (), getContext (), mCallback);
        mStepsRecyclerView.setAdapter (mStepsAdapter);
        mStepsRecyclerView.setNestedScrollingEnabled (false);
    }
}
