package co.asterv.ad_bakingapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.asterv.ad_bakingapp.R;
import co.asterv.ad_bakingapp.RecipeDetailFragment;
import co.asterv.ad_bakingapp.RecipeInstructionFragment;
import co.asterv.ad_bakingapp.model.Step;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private Context context;
    private static List<Step> steps;
    RecipeDetailFragment.OnStepSelectedListener listener;

    public StepsAdapter(List<Step> steps, Context context, RecipeDetailFragment.OnStepSelectedListener listener) {
        this.context = context;
        this.steps = steps;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from (parent.getContext ())
                .inflate (R.layout.step_list_item, parent, false);
        ViewHolder vh = new ViewHolder (v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.ViewHolder holder, int position) {
        String shortDesc = steps.get(position).getStepShortDescription ();

        holder.stepShortDescTV.setText (shortDesc);

        Bundle bundle = new Bundle();
        RecipeInstructionFragment fragment = new RecipeInstructionFragment ();
        fragment.setArguments (bundle);

        holder.itemView.setOnClickListener ((View v) -> {
            if (null != listener) {
                listener.onStepSelected (steps, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (steps == null || steps.size() == 0) {
            return -1;
        }
        return steps.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shortDescTextView) TextView stepShortDescTV;

        public ViewHolder(View itemView) {
            super (itemView);
            itemView.setClickable(true);
            ButterKnife.bind(this, itemView);

        }
    }
}
