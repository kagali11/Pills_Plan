package revolware.pillsplan.activities.Tutorial;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import revolware.pillsplan.R;

/**
 * Created by Lenovo on 24.9.2016.
 */

public class Image4Fm extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.img4);
        return iv;
    }
}