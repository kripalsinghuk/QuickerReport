package com.example.quakerreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthQuickAdapter extends ArrayAdapter<EarthQuick> {
         private static final String LOCATION_SEPARATOR = "of";

    public EarthQuickAdapter(@NonNull Context context,  @NonNull List<EarthQuick> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquick_list_item,parent,false);
        }

        EarthQuick currentWord = getItem(position);


 //{{{{{{{{{{{{{{

        TextView magnitudeText= (TextView) listItemView.findViewById(R.id.magnitude);

//        DecimalFormat orignalForamt = new DecimalFormat("0.0");
//        String output  = orignalForamt.format(currentWord.getMagnitude());

        String output = formatMagnitude(currentWord.getMagnitude());
        magnitudeText.setText(output);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeText.getBackground();

        int magnitudeColor= getMagnitudeColor(currentWord.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);



  //}}}}}}}}}}}}}}}}}}}}}}}}}


//{{{{{{{{{{{{{{{{{{{{
        String orignalLocation = currentWord.getLocation();

        String primaryLocation;
        String locationOffset;
        if(orignalLocation.contains(LOCATION_SEPARATOR))
        {
            String [] parts = orignalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0]+LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else
        {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = orignalLocation;
        }
        TextView primaryLoctionView = listItemView.findViewById(R.id.location_primary);
        primaryLoctionView.setText(primaryLocation);

        TextView locationOffsetView = listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(locationOffset);

        //}}}}}}}}}}}}}}}}}}}}}}}}}



//{{{{{{{

        Date dataObject = new Date(currentWord.getmTimeInMillisecond());

        TextView dateText = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dataObject);
        dateText.setText(formattedDate);

        TextView timeView = listItemView.findViewById(R.id.time);
        String  formattedTime = formattedTime(dataObject);
        timeView.setText(formattedTime);

//}}}}}}
        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResouceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor)
        {
            case 0:
            case 1:
                magnitudeColorResouceId=R.color.magnitude1;
                break;
            case 2: magnitudeColorResouceId=R.color.magnitude2;
                break;
            case 3: magnitudeColorResouceId=R.color.magnitude3;
                break;
            case 4: magnitudeColorResouceId=R.color.magnitude4;
                break;
            case 5: magnitudeColorResouceId=R.color.magnitude5;
                break;
            case 6: magnitudeColorResouceId=R.color.magnitude6;
                break;
            case 7: magnitudeColorResouceId=R.color.magnitude7;
                break;
            case 8: magnitudeColorResouceId=R.color.magnitude8;
                break;
            case 9: magnitudeColorResouceId=R.color.magnitude9;
                break;
        default : magnitudeColorResouceId=R.color.magnitude9;
                break;

        }
        return ContextCompat.getColor(getContext(),magnitudeColorResouceId);

    }

    private String formatDate(Date dateObject){
        SimpleDateFormat dataFormat = new SimpleDateFormat("LLL dd,yyyy");
    return dataFormat.format(dateObject);
    }
    private String formattedTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
private String formatMagnitude(double magnitude){
    DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
  return magnitudeFormat.format(magnitude);
    }

}
