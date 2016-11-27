package at.neonartworks.animelister;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by NEON on 27.11.2016.
 */

public class Fragment_credit extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View creitView = inflater.inflate(R.layout.fragment_credit, container, false);
        return creitView;
    }
}
