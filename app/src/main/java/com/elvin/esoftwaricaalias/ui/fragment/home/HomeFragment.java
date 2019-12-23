package com.elvin.esoftwaricaalias.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elvin.esoftwaricaalias.R;
import com.elvin.esoftwaricaalias.core.InAppDataHolder;
import com.elvin.esoftwaricaalias.ui.adapter.StudentAdapter;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView rvStudents = root.findViewById(R.id.rvStudents);

        StudentAdapter studentAdapter = new StudentAdapter(getContext(), InAppDataHolder.students, new StudentAdapter.OnUpdateListener() {
            @Override
            public void onUpdate() {
                Fragment fragment = null;
                if (getFragmentManager() != null) {
                    fragment = getFragmentManager().findFragmentById(R.id.nav_host_fragment);
                    final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.detach(fragment).attach(fragment).commit();
                }
            }
        });
        rvStudents.setAdapter(studentAdapter);
        rvStudents.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }
}