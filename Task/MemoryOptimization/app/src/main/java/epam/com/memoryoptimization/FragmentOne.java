package epam.com.memoryoptimization;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mike on 19.10.2014.
 */
public class FragmentOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        IconManager iconManager = new IconManager(getActivity());
        Bitmap icon = iconManager.getIcon(SocialType.LINKED_IN);
        View view = getView();
        if (view == null) {
            return;
        }
        TextView viewById = (TextView) view.findViewById(R.id.section_label);
        viewById.setText("Click Me");
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "No action implemented", Toast.LENGTH_SHORT).show(); // move to @string
            }
        });
        ImageView iconView = (ImageView) view.findViewById(R.id.section_icon);
        iconView.setImageBitmap(icon);
    }
}
