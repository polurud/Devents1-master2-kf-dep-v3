package com.dartmouth.kd.devents;


import android.app.Activity;
import android.app.AlertDialog;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.Calendar;


public class DialogFragment extends android.app.DialogFragment {

    public static final int DIALOG_ID_ERROR = -1;
    public static final int DIALOG_PHOTO = 1;
    public static final int DIALOG_ID_MANUAL_INPUT_TITLE = 2;
    public static final int DIALOG_ID_MANUAL_INPUT_DATE = 3;
    public static final int DIALOG_ID_MANUAL_INPUT_START = 4;
    public static final int DIALOG_ID_MANUAL_INPUT_END = 5;
    public static final int DIALOG_ID_MANUAL_INPUT_LOCATION = 6;
    public static final int DIALOG_ID_MANUAL_INPUT_DESCRIPTION = 7;
    public static final int DIALOG_ID_MANUAL_INPUT_URL = 8;
    public static final int DIALOG_ID_MANUAL_INPUT_FOOD = 10;
    public static final int DIALOG_ID_MANUAL_INPUT_EVENT_TYPE = 11;
    public static final int DIALOG_ID_MANUAL_INPUT_PROGRAM_TYPE = 12;
    public static final int DIALOG_ID_MANUAL_INPUT_YEAR = 13;
    public static final int DIALOG_ID_MANUAL_INPUT_MAJOR = 14;
    public static final int DIALOG_ID_MANUAL_INPUT_GREEK_SOCIETY = 15;
    public static final int DIALOG_ID_MANUAL_INPUT_GENDER = 16;
    Context mContext;
    Intent myIntent;
    private static final String DIALOG_KEY = "dialog_id";

    public static DialogFragment newInstance(int dialog_id) {
        DialogFragment f1 = new DialogFragment();
        Bundle args = new Bundle();
        args.putInt(DIALOG_KEY, dialog_id);
        f1.setArguments(args);
        return f1;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int dialog_id = getArguments().getInt(DIALOG_KEY);
        final Activity parent = getActivity();
        final EditText textEntryView;
        //final Spinner spin;
        final Calendar now;
        int hour, minute, year, month, day;


        switch (dialog_id) {
            case DIALOG_PHOTO:

                AlertDialog.Builder builder = new AlertDialog.Builder(parent);
                builder.setTitle(R.string.ui_profile_pic_Gallerychoose);

                builder.setItems(R.array.ui_profile_photo_selection,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {


                                // ((UserProfile) parent).onPhotoPickerItemSelected(item);
                            }
                        });
                return builder.create();

            case DIALOG_ID_MANUAL_INPUT_TITLE:

                textEntryView = new EditText(parent);
                textEntryView.setInputType(InputType.TYPE_CLASS_TEXT);
                textEntryView.setHint(R.string.ui_manual_input_title_hint);
                textEntryView.setLines(4);
                return new AlertDialog.Builder(parent)
                        .setTitle(R.string.ui_manual_input_title)
                        .setView(textEntryView)
                        .setPositiveButton(R.string.ui_button_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        ((CreateCampusEvent) parent).onTitleSet(textEntryView.getText()
                                                .toString());

                                    }
                                })
                        .setNegativeButton(R.string.ui_button_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        textEntryView.setText("");
                                    }
                                }).create();

            case DIALOG_ID_MANUAL_INPUT_DATE:

                now = Calendar.getInstance();
                year = now.get(Calendar.YEAR);
                month = now.get(Calendar.MONTH);
                day = now.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(parent,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                ((CreateCampusEvent) parent).onDateSet(
                                        year, monthOfYear, dayOfMonth);
                            }
                        }, year, month, day);

            case DIALOG_ID_MANUAL_INPUT_START:
                now = Calendar.getInstance();
                hour = now.get(Calendar.HOUR_OF_DAY);
                minute = now.get(Calendar.MINUTE);

                return new TimePickerDialog(parent,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                ((CreateCampusEvent) parent).onStartSet(
                                        hourOfDay, minute);
                            }
                        }, hour, minute, false);

            case DIALOG_ID_MANUAL_INPUT_END:
                now = Calendar.getInstance();
                hour = now.get(Calendar.HOUR_OF_DAY);
                minute = now.get(Calendar.MINUTE);

                return new TimePickerDialog(parent,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                ((CreateCampusEvent) parent).onEndSet(
                                        hourOfDay, minute);
                            }
                        }, hour, minute, false);


            case DIALOG_ID_MANUAL_INPUT_LOCATION:

                textEntryView = new EditText(parent);
                textEntryView.setInputType(InputType.TYPE_CLASS_TEXT);
                textEntryView.setHint(R.string.ui_manual_input_location_hint);
                textEntryView.setLines(4);
                return new AlertDialog.Builder(parent)
                        .setTitle(R.string.ui_manual_input_location)
                        .setView(textEntryView)
                        .setPositiveButton(R.string.ui_button_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        ((CreateCampusEvent) parent).onLocationSet(textEntryView.getText()
                                                .toString());

                                    }
                                })
                        .setNegativeButton(R.string.ui_button_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        textEntryView.setText("");
                                    }
                                }).create();


            case DIALOG_ID_MANUAL_INPUT_DESCRIPTION:

                textEntryView = new EditText(parent);
                textEntryView.setInputType(InputType.TYPE_CLASS_TEXT);
                textEntryView.setHint(R.string.ui_manual_input_description_hint);
                textEntryView.setLines(4);
                return new AlertDialog.Builder(parent)
                        .setTitle(R.string.ui_manual_input_description)
                        .setView(textEntryView)
                        .setPositiveButton(R.string.ui_button_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        ((CreateCampusEvent) parent).onDescriptionSet(textEntryView.getText()
                                                .toString());

                                    }
                                })
                        .setNegativeButton(R.string.ui_button_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        textEntryView.setText("");
                                    }
                                }).create();

            case DIALOG_ID_MANUAL_INPUT_URL:

                textEntryView = new EditText(parent);
                textEntryView.setInputType(InputType.TYPE_CLASS_TEXT);
                textEntryView.setHint(R.string.ui_manual_input_url_hint);
                textEntryView.setLines(4);
                return new AlertDialog.Builder(parent)
                        .setTitle(R.string.ui_manual_input_url)
                        .setView(textEntryView)
                        .setPositiveButton(R.string.ui_button_ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        ((CreateCampusEvent) parent).onUrlSet(textEntryView.getText()
                                                .toString());

                                    }
                                })
                        .setNegativeButton(R.string.ui_button_cancel,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        textEntryView.setText("");
                                    }
                                }).create();

            //NEED TO DO THIS FOR ALL FILTERS
            case DIALOG_ID_MANUAL_INPUT_MAJOR:

                AlertDialog.Builder builder2 = new AlertDialog.Builder(parent);
                builder2.setTitle("Applicable Major");

                builder2.setItems(R.array.Major,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                //String major = R.array.Major
                                //dialog.
                                String major = getMajorfromInt(item);
                                ((CreateCampusEvent) parent).onMajorSet(major);
                                // ((UserProfile) parent).onPhotoPickerItemSelected(item);
                            }
                        });

            case DIALOG_ID_MANUAL_INPUT_YEAR:

                AlertDialog.Builder builder3 = new AlertDialog.Builder(parent);
                builder3.setTitle("Applicable Major");

                builder3.setItems(R.array.class_year,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                //String major = R.array.Major
                                //dialog.
                                String major = getMajorfromInt(item);
                                ((CreateCampusEvent) parent).onMajorSet(major);
                                // ((UserProfile) parent).onPhotoPickerItemSelected(item);
                            }
                        });
                return builder3.create();

            case DIALOG_ID_MANUAL_INPUT_EVENT_TYPE:

                AlertDialog.Builder builder4 = new AlertDialog.Builder(parent);
                builder4.setTitle("Applicable Major");

                builder4.setItems(R.array.event_types,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                //String major = R.array.Major
                                //dialog.
                                String major = getMajorfromInt(item);
                                ((CreateCampusEvent) parent).onMajorSet(major);
                                // ((UserProfile) parent).onPhotoPickerItemSelected(item);
                            }
                        });
                return builder4.create();

            case DIALOG_ID_MANUAL_INPUT_PROGRAM_TYPE:

                AlertDialog.Builder builder5 = new AlertDialog.Builder(parent);
                builder5.setTitle("Applicable Program Type");

                builder5.setItems(R.array.program,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                //String major = R.array.Major
                                //dialog.
                                String major = getMajorfromInt(item);
                                ((CreateCampusEvent) parent).onMajorSet(major);
                                // ((UserProfile) parent).onPhotoPickerItemSelected(item);
                            }
                        });
                return builder5.create();

            case DIALOG_ID_MANUAL_INPUT_GREEK_SOCIETY:

                AlertDialog.Builder builder6 = new AlertDialog.Builder(parent);
                builder6.setTitle("Greek Affliation");

                builder6.setItems(R.array.Greek,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                //String major = R.array.Major
                                //dialog.
                                String major = getMajorfromInt(item);
                                ((CreateCampusEvent) parent).onMajorSet(major);
                                // ((UserProfile) parent).onPhotoPickerItemSelected(item);
                            }
                        });
                return builder6.create();

            case DIALOG_ID_MANUAL_INPUT_GENDER:

                AlertDialog.Builder builder7 = new AlertDialog.Builder(parent);
                builder7.setTitle("Applicable Program Type");

                builder7.setItems(R.array.program,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                //String major = R.array.Major
                                //dialog.
                                String major = getMajorfromInt(item);
                                ((CreateCampusEvent) parent).onMajorSet(major);
                                // ((UserProfile) parent).onPhotoPickerItemSelected(item);
                            }
                        });
                return builder7.create();
          /*AlertDialog.Builder builder2 = new AlertDialog.Builder(parent);
          //spin = new Spinner(parent);
          Spinner spin = new Spinner();

          ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                  android.R.layout.simple_spinner_item, R.array.Major);

          dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spin.setAdapter(dataAdapter);

          builder2.setTitle("Applicable Major")
                  .setView(spin)
                  .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                      }
                  })
                  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                      public void onClick(DialogInterface dialog, int id) {
                          // User cancelled the dialog
                      }
                  });
          // Create the AlertDialog object and return it
          return builder2.create();*/

            default:
                return null;
        }
    }

    String getMajorfromInt(int val) {
        switch (val) {
            case 1:
                return "Arts";
            case 2:
                return "Humanities";
            case 3:
                return "Science, Technology and Math";
            case 4:
                return "Others";
            default:
                return "";
        }
    }
}


