package revolware.pillsplan.activities.Tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import revolware.pillsplan.R;

/**
 * Created by Lenovo on 25.9.2016.
 */

public class Image5Fm extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.img5);
        return iv;
    }

}
