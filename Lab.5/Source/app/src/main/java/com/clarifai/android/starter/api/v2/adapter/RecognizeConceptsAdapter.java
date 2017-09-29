package com.clarifai.android.starter.api.v2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import clarifai2.dto.prediction.Concept;
import com.clarifai.android.starter.api.v2.R;
import com.clarifai.android.starter.api.v2.activity.shopping;

import java.util.ArrayList;
import java.util.List;

public class RecognizeConceptsAdapter extends RecyclerView.Adapter<RecognizeConceptsAdapter.Holder> {
Context c;
  public RecognizeConceptsAdapter(Context context)
  {
   c = context;
  }
  @NonNull public List<Concept> concepts = new ArrayList<>();

  public RecognizeConceptsAdapter setData(@NonNull List<Concept> concepts) {
    this.concepts = concepts;
    notifyDataSetChanged();
    return this;
  }

  @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_concept, parent, false),c,concepts);
  }

  @Override public void onBindViewHolder(Holder holder, int position) {
    final Concept concept = concepts.get(position);
    holder.label.setText(concept.name() != null ? concept.name() : concept.id());
    holder.probability.setText(String.valueOf(concept.value()));
  }

  @Override public int getItemCount() {
    return concepts.size();
  }

  static final class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

    Context c1;
    @BindView(R.id.label) TextView label;
    @BindView(R.id.probability) TextView probability;
    List<Concept> concepts1 = new ArrayList<>();
    public Holder(View root,Context context,List<Concept> cpts) {
      super(root);
      c1 = context;
      concepts1 = cpts;
      ButterKnife.bind(this, root);
      root.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      Intent intent = new Intent(c1,shopping.class);
      intent.putExtra("Object",concepts1.get(getAdapterPosition()).name());
      c1.startActivity(intent);
    }
  }
}
