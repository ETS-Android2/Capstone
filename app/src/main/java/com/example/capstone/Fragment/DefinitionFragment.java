package com.example.capstone.Fragment;
import android.content.Context;
import android.os.Bundle;
<<<<<<< HEAD

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
=======
>>>>>>> 49d7749 (firebase methods)
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.EditText;

=======
>>>>>>> 49d7749 (firebase methods)
import com.example.capstone.Adapter.DefinitionFirebaseAdapter;
import com.example.capstone.Model.DefinitionInfo;
import com.example.capstone.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
<<<<<<< HEAD
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;


=======
import com.google.firebase.database.FirebaseDatabase;

>>>>>>> 49d7749 (firebase methods)
public class DefinitionFragment extends Fragment {
    private DefinitionFirebaseAdapter fadapter;
    private RecyclerView rvDefinition;
    private SearchView svSearchDefinition;
    FirebaseRecyclerOptions<DefinitionInfo> options;

<<<<<<< HEAD

=======
>>>>>>> 49d7749 (firebase methods)
    public DefinitionFragment() {

    }

<<<<<<< HEAD

=======
>>>>>>> 49d7749 (firebase methods)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_definition, container, false);
        rvDefinition = rootView.findViewById(R.id.rvDefinition);
        svSearchDefinition=rootView.findViewById(R.id.svSearchDefinition);


        rvDefinition.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        FirebaseRecyclerOptions<DefinitionInfo> options;

        options= new FirebaseRecyclerOptions.Builder<DefinitionInfo>()
                .setQuery(FirebaseDatabase.getInstance("https://capstoneproject-4b898-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference()
                        .child("Definition").orderByChild("DefinitionName"),DefinitionInfo.class)
                .build();

        fadapter = new DefinitionFirebaseAdapter(options);
        rvDefinition.setAdapter(fadapter);

        svSearchDefinition.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String search) {
                processSearch(search);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String search) {
                processSearch(search);
                return false;
            }
        });

        return rootView;
    }

    private void processSearch(String search) {
        options= new FirebaseRecyclerOptions.Builder<DefinitionInfo>()
                .setQuery(FirebaseDatabase.getInstance("https://capstoneproject-4b898-default-rtdb.asia-southeast1.firebasedatabase.app/")
                        .getReference().child("Definition").orderByKey()
                        .startAt(search.toLowerCase()).endAt(search.toLowerCase()+"\uf8ff"),DefinitionInfo.class).build();

        fadapter = new DefinitionFirebaseAdapter(options);
        fadapter.startListening();
        rvDefinition.setAdapter(fadapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        fadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        fadapter.stopListening();
    }



    //Prevent out of Bound error
    public class WrapContentLinearLayoutManager extends LinearLayoutManager {
        public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        //... constructor
        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {

            }
        }
    }
}
