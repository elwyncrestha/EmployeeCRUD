package com.elvin.employeecrud.ui.fragment.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.elvin.employeecrud.R;
import com.elvin.employeecrud.core.AppConstant;

public class AboutUsFragment extends Fragment {

    private WebView wvAboutUs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);
        this.wvAboutUs = root.findViewById(R.id.wvAboutUs);
        wvAboutUs.loadUrl(AppConstant.WEBSITE);
        return root;
    }
}