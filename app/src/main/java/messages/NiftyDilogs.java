package messages;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.Objects;
import java.util.Random;

import zw.co.zimcybers.kinsleykajiva.a263chatcom.R;

/**
 * Created by kinsley kajiva on 1/17/2016.
 */
public class NiftyDilogs {

    Context mContext;
    NiftyDialogBuilder dialogBuilder;

    public NiftyDilogs(){}
    public NiftyDilogs(Context mContext ){
        this.mContext=mContext;
    }
    public void dialog_ok_cancel(String message_txt){
        Objects mObjects;
        dialogBuilder= NiftyDialogBuilder.getInstance(mContext);//dilaogbox
        dialogBuilder.withTitle("Infomation").withTitleColor("#FFFFFF").withDividerColor("#11000000")
                     .withMessage(message_txt).withMessageColor("#FFFFFFFF").withDialogColor(ContextCompat.getColor(mContext, R.color.colorPrimary))//def  | withDialogColor(int resid)
                    .withIcon(R.drawable.ic_infomation).withDuration(700).withEffect(stylepop_up())//def Effectstype.Slidetop
                    .withButton1Text("OK").withButton2Text("Cancel").isCancelableOnTouchOutside(false)
                  //  .setCustomView(R.layout.custom_view, v.getContext())         //.setCustomView(View or ResId,context)
                    .setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
                        }
                    })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "i'm btn2", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();


    }
    public void dialog_ok(String String_message){
        dialogBuilder= NiftyDialogBuilder.getInstance(mContext);//dilaogbox
        dialogBuilder.withTitle("Infomation").withTitleColor("#FFFFFF").withDividerColor("#11000000")
                .withMessage(String_message).withMessageColor("#FFFFFFFF").withDialogColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
                .withButton1Text("Ok").isCancelableOnTouchOutside(false).withEffect(stylepop_up())
                .withIcon(R.drawable.ic_infomation).withDuration(700)
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                        //Toast.makeText(v.getContext(), "ok then..", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
private Effectstype stylepop_up(){

    Techniques mteTechniques;
    int randomNum;
    int maximum = 14;
    int minimum = 1;
    randomNum = new Random().nextInt(maximum - minimum + 1) + minimum;

    switch (randomNum){
        case 1: return Effectstype.Fadein;
        case 2: return Effectstype.Slideleft;
        case 3: return Effectstype.Slidetop;
        case 4: return Effectstype.SlideBottom;
        case 5: return Effectstype.Slideright;
        case 6: return Effectstype.Fall;
        case 7: return Effectstype.Newspager;
        case 8: return Effectstype.Fliph;
        case 9: return Effectstype.Flipv;
        case 10: return Effectstype.RotateBottom;
        case 11: return Effectstype.RotateLeft;
        case 12: return Effectstype.Slit;
        case 13: return Effectstype.Shake;
        case 14: return Effectstype.Sidefill;
        default:
            return Effectstype.Fadein;
    }
}
/*
    dialogBuilder
            .withTitle("Modal Dialog")                                  //.withTitle(null)  no title
            .withTitleColor("#FFFFFF")                                  //def
    .withDividerColor("#11000000")                              //def
    .withMessage("This is a modal Dialog.")                     //.withMessage(null)  no Msg
    .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
    .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)
    .withIcon(getResources().getDrawable(R.drawable.icon))
            .withDuration(700)                                          //def
    .withEffect(effect)                                         //def Effectstype.Slidetop
    .withButton1Text("OK")                                      //def gone
    .withButton2Text("Cancel")                                  //def gone
    .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
    .setCustomView(R.layout.custom_view,v.getContext())         //.setCustomView(View or ResId,context)
            .setButton1Click(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
        }
    })
            .setButton2Click(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(),"i'm btn2",Toast.LENGTH_SHORT).show();
        }
    })
            .show();*/






    /*Effects

Fadein, Slideleft, Slidetop, SlideBottom, Slideright, Fall, Newspager, Fliph, Flipv, RotateBottom, RotateLeft, Slit, Shake, Sidefill*/
}
