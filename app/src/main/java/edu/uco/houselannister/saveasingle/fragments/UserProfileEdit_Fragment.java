package edu.uco.houselannister.saveasingle.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.Photo;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class UserProfileEdit_Fragment extends Fragment {

    View v;
    private int gender = 0;
    private int smoking = 0;
    private int bodyType = 0;
    private int marriedStatus = 0;
    private int education = 0;
    private int income = 0;
    private ImageView imgPhoto;
    private TextView fullname;
    private TextView displayName;
    private TextView age;
    private TextView height;
    private TextView location;
    //private TextView education;
    private TextView religion;
    private TextView ethnicity;
    private TextView work;
    //private TextView income;
    private TextView children;
    private TextView story;
    private TextView perfectMatch;
    private Fragment instance;
    private Switch swGalleryPrivate;
    private Switch swProfilePrivate;
    boolean isChecked;

    public UserProfileEdit_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_user_profile_edit_, container, false);
        Button save = (Button) v.findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
                Photo ph = new Photo();
                ph.setPhoto(((BitmapDrawable)imgPhoto.getDrawable()).getBitmap());
                appModel.getCurrentUser().setProfilePhoto(ph);
                appModel.getCurrentUser().setFullName(fullname.getText().toString());
                appModel.getCurrentUser().setName(displayName.getText().toString());
                appModel.getCurrentUser().setAge(Integer.parseInt(age.getText().toString()));
                appModel.getCurrentUser().setHeight(Double.parseDouble(height.getText().toString()));
                appModel.getCurrentUser().setPosition(location.getText().toString());
                //appModel.getCurrentUser().setEducation(education.getText().toString());
                appModel.getCurrentUser().setReligion(religion.getText().toString());
                appModel.getCurrentUser().setEthnicity(ethnicity.getText().toString());
                appModel.getCurrentUser().setWork(work.getText().toString());
                //appModel.getCurrentUser().setIncome(Double.parseDouble(income.getText().toString()));
                appModel.getCurrentUser().setChildren(Integer.parseInt(children.getText().toString()));
                appModel.getCurrentUser().setStory(story.getText().toString());
                appModel.getCurrentUser().setPerfectMatch(perfectMatch.getText().toString());
                appModel.getCurrentUser().setGender(gender);
                appModel.getCurrentUser().setSmoking(smoking);
                appModel.getCurrentUser().setBodyType(bodyType);
                appModel.getCurrentUser().setEducation(education);
                appModel.getCurrentUser().setIncome(income);
                appModel.getCurrentUser().setMarriedStatus(marriedStatus);
                appModel.getCurrentUser().setGalleryPrivate(swGalleryPrivate.isChecked());
                appModel.getCurrentUser().setProfilePrivate(swProfilePrivate.isChecked());
                appModel.saveUser(appModel.getCurrentUser());
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
            }
        });


        Model appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        imgPhoto = (ImageView)v.findViewById(R.id.imgPhoto);
        if (appModel.getCurrentUser().getProfilePhoto().getPhoto() != null) {
            imgPhoto.setImageBitmap(appModel.getCurrentUser().getProfilePhoto().getPhoto());
        }
        imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(instance.getContext(), v, Gravity.RIGHT);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.item_load:
                                intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Choose Image"), 0);
                                return true;
                            case R.id.item_take:
                                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 1);
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.inflate(R.menu.menu_profile_photo);
                popupMenu.show();
            }
        });
        fullname = (TextView)v.findViewById(R.id.txtFullName);
        fullname.setText(appModel.getCurrentUser().getFullName());
        displayName = (TextView)v.findViewById(R.id.txtDisplayName);
        displayName.setText(appModel.getCurrentUser().getName());
        age = (TextView)v.findViewById(R.id.txtAge);
        age.setText(String.valueOf(appModel.getCurrentUser().getAge()));
        height = (TextView)v.findViewById(R.id.txtHeight);
        height.setText(String.valueOf(appModel.getCurrentUser().getHeight()));
        location = (TextView)v.findViewById(R.id.txtLocation);
        location.setText(appModel.getCurrentUser().getPosition());
        //education = (TextView)v.findViewById(R.id.txtEducation);
        //education.setText(appModel.getCurrentUser().getEducation());
        religion = (TextView)v.findViewById(R.id.txtReligion);
        religion.setText(appModel.getCurrentUser().getReligion());
        ethnicity = (TextView)v.findViewById(R.id.txtEthnicity);
        ethnicity.setText(appModel.getCurrentUser().getEthnicity());
        work = (TextView)v.findViewById(R.id.txtWork);
        work.setText(appModel.getCurrentUser().getWork());
        //income = (TextView)v.findViewById(R.id.txtIncome);
        //income.setText(String.valueOf(appModel.getCurrentUser().getIncome()));
        children = (TextView)v.findViewById(R.id.txtChildren);
        children.setText(String.valueOf(appModel.getCurrentUser().getChildren()));
        story = (TextView)v.findViewById(R.id.txtStory);
        story.setText(appModel.getCurrentUser().getStory());
        perfectMatch = (TextView)v.findViewById(R.id.txtPerfectMatch);
        perfectMatch.setText(appModel.getCurrentUser().getPerfectMatch());

        swGalleryPrivate = (Switch)v.findViewById(R.id.swGalleryPrivate);
        swGalleryPrivate.setChecked(appModel.getCurrentUser().getGalleryPrivate());


        swProfilePrivate = (Switch)v.findViewById(R.id.swProfilePrivate);
        swProfilePrivate.setChecked(appModel.getCurrentUser().getProfilePrivate());
/*
        //set the switch to ON
        swProfilePrivate.setChecked(false);

                if(isChecked){
                    //swProfilePrivate.setText("Switch is currently ON");
                    Toast.makeText(getActivity(),"yes", Toast.LENGTH_LONG).show();
                }else{
                    //swProfilePrivate.setText("Switch is currently OFF");
                    Toast.makeText(getActivity(),"no", Toast.LENGTH_LONG).show();
                }
*/
        gender = appModel.getCurrentUser().getGender();
        final RadioButton rbMale = (RadioButton)v.findViewById(R.id.rbMale);
        final RadioButton rbFemale = (RadioButton)v.findViewById(R.id.rbFemale);
        if (gender == 0) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }
        rbMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbFemale.setChecked(false);
                gender = 0;
            }
        });
        rbFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbMale.setChecked(false);
                gender = 1;
            }
        });




        smoking = appModel.getCurrentUser().getSmoking();
        Spinner spnSmoking = (Spinner)v.findViewById(R.id.spnSmoking);
        ArrayAdapter<String> adSmoking = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item, User.SmokingValues);
        adSmoking.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSmoking.setAdapter(adSmoking);
        spnSmoking.setSelection(smoking);
        spnSmoking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                smoking = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        education = appModel.getCurrentUser().getEducation();
        Spinner spnEducation = (Spinner)v.findViewById(R.id.spnEducation);
        ArrayAdapter<String> adEducation = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item, User.EducationValues);
        adEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEducation.setAdapter(adEducation);
        spnEducation.setSelection(education);
        spnEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                education = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        income = appModel.getCurrentUser().getIncome();
        Spinner spnIncome = (Spinner)v.findViewById(R.id.spnIncome);
        ArrayAdapter<String> adIncome = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item, User.IncomeValues);
        adIncome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIncome.setAdapter(adIncome);
        spnIncome.setSelection(income);
        spnIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                income = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bodyType = appModel.getCurrentUser().getBodyType();
        Spinner spnBodyType = (Spinner)v.findViewById(R.id.spnBodyType);
        ArrayAdapter<String> adBodyType = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item, User.BodyTypeValues);
        adBodyType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBodyType.setAdapter(adBodyType);
        spnBodyType.setSelection(bodyType);
        spnBodyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bodyType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        marriedStatus = appModel.getCurrentUser().getMarriedStatus();
        Spinner spnMarriedStatus = (Spinner)v.findViewById(R.id.spnMarriedStatus);
        ArrayAdapter<String> adMarriedStatus = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item, User.MarriedStatusValues);
        adMarriedStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMarriedStatus.setAdapter(adMarriedStatus);
        spnMarriedStatus.setSelection(marriedStatus);
        spnMarriedStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                marriedStatus = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap thumbnail=null;
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
                imgPhoto.setImageBitmap(thumbnail);
            }
        }
    }

}
