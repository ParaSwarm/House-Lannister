package edu.uco.houselannister.saveasingle.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.adapters.ImageGridAdapter;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class DisplayPrivateAlbumFragment extends Fragment {
    private View FragmentView;
    private Model appModel;
    private TextView title;
    private GridView gridView;
    private ArrayList<Bitmap> imageArrayList = new ArrayList<>();
    private ImageGridAdapter objImageAdapter;
    private static User accessedUser;
    private boolean show = false;

    public DisplayPrivateAlbumFragment() {
    }

    public static DisplayPrivateAlbumFragment newInstance(User user) {
        accessedUser = user;
        return new DisplayPrivateAlbumFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentView = inflater.inflate(R.layout.fragment_display_private_album, container, false);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        title = (TextView) FragmentView.findViewById(R.id.title);
        gridView = (GridView) FragmentView.findViewById(R.id.gridview);
        title.setText(accessedUser.getName() + "'s Private photos");

        if (accessedUser.getInteractions().getPrivatePhotoAccess() == null) {
            title.setText(accessedUser.getName() + " didn't share private photos with you");
        } else if (accessedUser.getInteractions().getPrivatePhotoAccess().contains(appModel.getCurrentUser())) {
            if (accessedUser.getInteractions().getMyPrivatePhotos() != null) {
                for (String s : accessedUser.getInteractions().getMyPrivatePhotos()) {
                    imageArrayList.add(StringToBitMap(s));
                }
            } else {
                title.setText(accessedUser.getName() + " doesn't have any private photos");
            }
        } else {
            title.setText(accessedUser.getName() + " didn't share private photos with you");
        }


        objImageAdapter = new ImageGridAdapter(getActivity(), imageArrayList);
        gridView.setAdapter(objImageAdapter);

        return FragmentView;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}