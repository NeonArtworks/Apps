package at.neonartworks.animelister;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by NEON on 27.11.2016.
 */

public class Fragment_save  extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View saveView = inflater.inflate(R.layout.fragment_main, container, false);
        return saveView;
    }
}
