package edu.uco.houselannister.saveasingle.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Photo;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;
import edu.uco.houselannister.saveasingle.service.DatabaseHelper;

public class Fragment_gallery extends Fragment {

    ImageView imageView;

    ArrayList<Bitmap> imageIDs;
    Button BTN_loadFromGallery;

    ProgressDialog progressDialog;

    File directory;
    File input;
    File output;
    String User_login ;
    View v;
    Fragment instance;

    public Fragment_gallery() {

    }
    public static Fragment_gallery newInstance(){
        return new Fragment_gallery();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //changed here
        instance = this;
        v = inflater.inflate(R.layout.fragment_fragment_gallery, container, false);
        Button b = (Button) v.findViewById(R.id.loadCamera);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

//        userLogin user =userLogin.getInstance();
//        User_login = user.getUserEmail();

        progressDialog = new ProgressDialog(this.getContext());

        //-----------------------
        //change the user ID / Login ID then you will the change in your uploaded photo gallery

        User_login = "6689";

        progressDialog.show();
        progressDialog.setMessage("Processing...");


        BTN_loadFromGallery = (Button) v.findViewById(R.id.loadFromGallery);
//        BTN_RefreshList = (Button) findViewById(R.id.Refresh);

        FixDirAT_start();
        imageIDs = new ArrayList<>();
        try {
            loadImages();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        progressDialog.dismiss();
        imageView = (ImageView) v.findViewById(R.id.imageView);

        BTN_loadFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(i, SELECTED_PICTURE);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose Image"), 0);
            }
        });

        Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        ArrayList<Photo> ug = appModel.getCurrentUser().getPhotos();
        ArrayList<Photo> emptyList = new ArrayList<Photo>();
        for (Photo ph:ug) {
            if (ph.getPhoto() == null) {
                emptyList.add(ph);
            }
        }
        for (Photo ph:emptyList) {
            ug.remove(ph);
        }
        for (Photo ph : ug) {
            appendPhoto(ph);
        }
        return v ;
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //----------------
    //----------------
    //----------------
    //--------


    public void fileExist_not(File filepath) {
        if (filepath.exists()) {
            filepath.delete();
        } else
            filepath.getParentFile().mkdirs();
    }

    public void RefreshList() throws FileNotFoundException {
        progressDialog.show();
        progressDialog.setMessage("Processing...");
        imageIDs = new ArrayList<>();
        loadImages();
        progressDialog.dismiss();
    }

    protected void loadImages() throws FileNotFoundException {

        Log.d("Files", "Path: " + directory);

        if (directory.exists()) {
            ArrayList allImages = loadAllfilename();
            for (int i = 0; i < allImages.size(); i++) {
                output = new File(directory, allImages.get(i).toString());
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(output));
                this.imageIDs.add(b);
            }
        }
    }

    public ArrayList<String> loadAllfilename() {
        ArrayList<String> imagesName = new ArrayList<>();
        SQLiteDatabase theDB = DatabaseHelper.getInstance(this.getContext().getApplicationContext()).getWritableDatabase();
//        db.execSQL("CREATE TABLE Logins (id TEXT,password TEXT);");
        String theSQL = "Select id AS _id, nameOFImages AS _pass" + " from ListofImages where id = " + User_login;
        Cursor results = theDB.rawQuery(theSQL, null);

        while (results.moveToNext()) {
            imagesName.add(results.getString(1));
            Log.d("TAG", "results.getString(1) : " + results.getString(1));
        }
        results.close();
        return imagesName;
    }

    public void FixDirAT_start() {
        ContextWrapper cw = new ContextWrapper(this.getContext().getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap thumbnail=null;
        Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        ArrayList<Photo> ug = appModel.getCurrentUser().getPhotos();
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                if (data != null) {
                    try {
                        thumbnail = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), data.getData());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (requestCode == 1) {
                thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (thumbnail != null) {
                thumbnail = this.getResizedBitmap(thumbnail, 300);
                Photo ph = new Photo();
                ph.setPhoto(thumbnail);
                ug.add(ph);
                appendPhoto(ph);
                appModel.saveUser(appModel.getCurrentUser());
            }
        }
    }

    private void appendPhoto(Photo photo) {
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.llGallery);
        Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        ArrayList<Photo> ug = appModel.getCurrentUser().getPhotos();
        ImageView iv = new ImageView(this.getContext());
        iv.setImageBitmap(photo.getPhoto());
        iv.setPadding(8, 8, 8, 8);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        layout.addView(iv);
        iv.getLayoutParams().height = 400;
        iv.requestLayout();
        final ImageView fiv = iv;
        final LinearLayout flayout = layout;
        final Photo fth = photo;
        final ArrayList<Photo> fug = ug;
        final Model fm = appModel;
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(instance.getContext(), v, Gravity.RIGHT);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_remove:
                                flayout.removeView(fiv);
                                fug.remove(fth);
                                fm.saveUser(fm.getCurrentUser());
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.menu_gallery);
                popupMenu.show();
                return false;
            }
        });
    }

    private void copy(InputStream is, OutputStream os) {
        final byte[] buf = new byte[1024];
        int numBytes;
        try {
            while (-1 != (numBytes = is.read(buf))) {
                os.write(buf, 0, numBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                Log.e("TAG", "IOException");

            }
        }
    }

    String LoginUserID;
    static final String LoginID = "id";
    static final String password = "password";
    static final String TABLE_login = "Logins";

    static final String LoginID_ForImages = "id";
    static final String nameOFImages = "nameOFImages";
    static final String TABLE_ListofImages = "ListofImages";

    public void insertIntoDatabase(String id, String PassOR_Nameofimage, boolean flag) {
        SQLiteDatabase theDB = DatabaseHelper.getInstance(this.getContext()).getWritableDatabase();
        ContentValues cv = new ContentValues();
//if flag is true the insert in logins lables
        if (flag) {
            cv.put(LoginID, id);
            cv.put(password, PassOR_Nameofimage);
            theDB.insert(TABLE_login, LoginID, cv);
        } else {
            cv.put(LoginID_ForImages, id);
            cv.put(nameOFImages, PassOR_Nameofimage);
            theDB.insert(TABLE_ListofImages, LoginID, cv);
        }
    }
}
