package edu.uco.houselannister.saveasingle.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.service.DatabaseHelper;

public class Fragment_gallery extends Fragment implements View.OnClickListener {

    ImageView imageView;
    Bitmap SelectedImage_fromGalery;
    Drawable draw_selectedImage_fromGalery;
    Gallery gallery;

    ArrayList<Bitmap> imageIDs;
    Button BTN_loadFromGallery;
    Button BTN_RefreshList;

    ProgressDialog progressDialog;
    public static final int PIC_REQUEST = 1337;
    public static final int SELECTED_PICTURE = 1;

    File directory;
    File input;
    File output;
    String User_login ;
    View v;


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
        v = inflater.inflate(R.layout.fragment_fragment_gallery, container, false);
        Button b = (Button) v.findViewById(R.id.loadCamera);
        b.setOnClickListener(this);


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

        // Note that Gallery view is deprecated in Android 4.1---
        gallery = (Gallery) v.findViewById(R.id.gallery1);
        gallery.setAdapter(new ImageAdapter(this.getContext()));

        progressDialog.dismiss();
        imageView = (ImageView) v.findViewById(R.id.imageView);


        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getContext(), "pic" + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();
                // display the images selected
           //     imageView = (ImageView) v.findViewById(R.id.imageView);
                imageView.setBackground(null);
                imageView.setImageBitmap(imageIDs.get(position));
            }
        });

        BTN_loadFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECTED_PICTURE);
            }
        });

        return v ;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
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
        gallery.setAdapter(new ImageAdapter(this.getContext()));
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

    // changed this
    @Override
    public void onClick(View v) {
        String nameofImage = "capture_" + System.currentTimeMillis() + ".jpeg";
        output = new File(directory, nameofImage);

        fileExist_not(output);

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
        startActivityForResult(i, PIC_REQUEST);

    }



    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int itemBackground;

        public ImageAdapter(Context c) {
            context = c;
            // sets a grey background; wraps around the images
            TypedArray a = c.obtainStyledAttributes(R.styleable.MyGallery);
            itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            a.recycle();
        }

        // returns the number of images
        public int getCount() {
            return imageIDs.size();
        }

        // returns the ID of an item
        public Object getItem(int position) {
            return position;
        }

        // returns the ID of an item
        public long getItemId(int position) {
            return position;
        }

        // returns an ImageView view
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageBitmap(imageIDs.get(position));
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
            imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("result " + resultCode, "activity okay " + requestCode);

        if (requestCode == SELECTED_PICTURE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContext().getContentResolver().query(uri, projection, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(projection[0]);

            String filePath = cursor.getString(columnIndex);
            cursor.close();

            SelectedImage_fromGalery = BitmapFactory.decodeFile(filePath);

//            imageView.setImageBitmap(SelectedImage_fromGalery);
            draw_selectedImage_fromGalery = new BitmapDrawable(SelectedImage_fromGalery);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setImageBitmap(null);
                imageView.setBackground(draw_selectedImage_fromGalery);
            }
            String filename = filePath.substring(filePath.lastIndexOf("/") + 1);

            //--- save image to internal memory
            //-----upload and save
            Log.d("save :: " , "file");
            try {
                saveToInternalStorage(SelectedImage_fromGalery, filename);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

//                    File input_path =  new File(filePath);
//                    this.input =  input_path;
//                    someFunction(filename);
//
//                    Log.d("input_path" + input_path ,"output_path" + this.output);
//
//
//                    try {
//                        copyFile_n(output,input);
//                    } catch (IOException e) {
//                        Log.d("a gy error " ,"_"+e);
//                        e.printStackTrace();
//                    }
////                    copyImageToMemory(output,input);
//
//                    Log.d("copyImageToMemory"  ,"DONE");

        }

        if (requestCode == PIC_REQUEST && resultCode == Activity.RESULT_OK) {

            Log.d("CallCamera", output.getAbsolutePath());

            Bitmap myBitmap = BitmapFactory.decodeFile(output.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }

    }

    private void saveToInternalStorage(Bitmap bitmapImage, String filename) throws FileNotFoundException {

        insertIntoDatabase(User_login, filename, false);

        output = new File(directory, filename);

        fileExist_not(output);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(output);
            // Use the compress method on the BitMap object to write image to the OutputStream

            // bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90 , fos);

            Log.d("Writing output", "SAVE FILE");
        } catch (Exception e) {
            Log.d("Writing output", "Error NOT SAVE");
            e.printStackTrace();
        } finally {
            try {
                Log.d("Writing output", "Error closing");
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RefreshList();
    }


    //-----------------or can use this as well
    private void copyFile_n(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }


    ///---------------------------
    ///---------------------------
    //-----------------or can use this as well

    private void copyImageToMemory(File input, File outFile) {
        try {

            BufferedOutputStream os = new BufferedOutputStream(
                    new FileOutputStream(outFile));

            BufferedInputStream is = new BufferedInputStream(
                    new FileInputStream(input));

            copy(is, os);

        } catch (FileNotFoundException e) {
            Log.e("TAG", "FileNotFoundException");
        }
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

    void checkLogin_forDATABSE(String loginID, String e_password) {
        SQLiteDatabase theDB = DatabaseHelper.getInstance(this.getContext().getApplicationContext()).getWritableDatabase();
//        db.execSQL("CREATE TABLE Logins (id TEXT,password TEXT);");

        String theSQL = "Select id AS _id, password AS _pass" + " from Logins where id = " +  "123";

        Cursor results = theDB.rawQuery(theSQL, null);

        String id = null;
        String password = null;

        boolean flag = false;

        if (results.moveToNext()) {
            flag = true;
            id = results.getString(0);
            password = results.getString(1);
            LoginUserID = id;

            Log.d("TAG", "ID: " + id + " password: " + password);
        }
        results.close();
        if (flag) {
            Toast.makeText(getContext(), id + " Login exist",
                    Toast.LENGTH_SHORT).show();

        } else {
            //  suppose  LoginUserID = 0 ; then login not verified
            LoginUserID = "0";
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
