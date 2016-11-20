package edu.uco.houselannister.saveasingle.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.adapters.ImageGridAdapter;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

/**
 * Created by ThinkPad on 11/5/2016.
 */
public class PrivateAlbumFragment extends Fragment {
    Button addPicture, selectPicture, rotatePicture;
    private View FragmentView;
    private ImageView imageView;
    private GridView gridView;
    private Bitmap rotatedBitmap;
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageGridAdapter objImageAdapter;
    private ArrayList<String> imageStringList = new ArrayList<>();
    private ArrayList<Bitmap> imageList = new ArrayList<>();
    private Model appModel;
    int mDegree = 0;

    public PrivateAlbumFragment() {

    }

    public static PrivateAlbumFragment newInstance() {
        return new PrivateAlbumFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        if (appModel.getCurrentUser().getInteractions().getMyPrivatePhotos() != null) {
          //  Toast.makeText(getActivity(), "NOT NULL",                    Toast.LENGTH_LONG).show();
            imageStringList = appModel.getCurrentUser().getInteractions().getMyPrivatePhotos();
            for (String s : appModel.getCurrentUser().getInteractions().getMyPrivatePhotos()) {
                imageList.add(StringToBitMap(s));
            }
        } else {
       //     Toast.makeText(getActivity(), "NULL",                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentView = inflater.inflate(R.layout.fragment_private_album, container, false);
        imageView = (ImageView) FragmentView.findViewById(R.id.imgView);
        gridView = (GridView) FragmentView.findViewById(R.id.gridview);
        rotatePicture = (Button) FragmentView.findViewById(R.id.rotate_picture);
        addPicture = (Button) FragmentView.findViewById(R.id.add_picture);
        selectPicture = (Button) FragmentView.findViewById(R.id.select_picture);
        rotatePicture.setVisibility(View.GONE);
        addPicture.setVisibility(View.GONE);
        imageView.setVisibility(View.GONE);

        objImageAdapter = new ImageGridAdapter(getActivity(), imageList);
        gridView.setAdapter(objImageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                 new AlertDialog.Builder(getActivity())
                        .setTitle(getResources().getString(R.string.warning))
                        .setMessage(getResources().getString(R.string.do_you_want_delete_the_picture))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                imageStringList.remove(i);
                                imageList.remove(i);
                                objImageAdapter.notifyDataSetChanged();
                                appModel.getCurrentUser().getInteractions().setMyPrivatePhotos(imageStringList);
                                appModel.saveUser(appModel.getCurrentUser());
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
            }
        });

        rotatePicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDegree = mDegree + 90;
                rotatedBitmap = rotateImage(rotatedBitmap, mDegree);
                imageView.setImageBitmap(rotatedBitmap);
            }
        });

        addPicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                rotatePicture.setVisibility(View.GONE);
                addPicture.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);


                String stringPic = BitMapToString(rotatedBitmap);
                imageStringList.add(stringPic);
                appModel.getCurrentUser().getInteractions().setMyPrivatePhotos(imageStringList);

                //Save userData
                appModel.saveUser(appModel.getCurrentUser());

                imageList.add(rotatedBitmap);
                objImageAdapter = new ImageGridAdapter(getActivity(), imageList);
                gridView.setAdapter(objImageAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                        Toast.makeText(getActivity(), "Picture: " + i + " " + l, Toast.LENGTH_SHORT).show();

                        new AlertDialog.Builder(getActivity())
                                .setTitle(getResources().getString(R.string.warning))
                                .setMessage(getResources().getString(R.string.do_you_want_delete_the_picture))
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        imageStringList.remove(i);
                                        imageList.remove(i);
                                        objImageAdapter.notifyDataSetChanged();
                                        appModel.saveUser(appModel.getCurrentUser());
                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                    }
                                })
                                .show();
                    }
                });

            }
        });

        selectPicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);

            }
        });

        return FragmentView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            rotatePicture.setVisibility(View.VISIBLE);
            addPicture.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);

            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getContext().getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
            Bitmap resizedImage = decodeFile(yourSelectedImage);
            rotatedBitmap = resizedImage;
            imageView.setImageBitmap(resizedImage);

        } else {
            Toast.makeText(getActivity(), "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }
    }

    public Bitmap rotateImage(Bitmap src, float degree) {

        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
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

    private Bitmap decodeFile(Bitmap bitmap) {
        int viewHeight = 640;
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();

// Calculate image's size by maintain the image's aspect ratio
        if (height > viewHeight) {
            float percente = (float) (height / 100);
            float scale = (float) (viewHeight / percente);
            width *= (scale / 100);
            height *= (scale / 100);
        }

// Resizing image
        Bitmap sizingBmp = Bitmap.createScaledBitmap(bitmap, (int) width, (int) height, true);

        return sizingBmp;
    }
}

