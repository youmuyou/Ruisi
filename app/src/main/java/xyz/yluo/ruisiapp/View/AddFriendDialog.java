package xyz.yluo.ruisiapp.View;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import xyz.yluo.ruisiapp.R;

/**
 * Created by free2 on 16-3-14.
 * 添加好友diaog
 *
 */
public class AddFriendDialog extends DialogFragment{
    private EditText content;
    private String userName = "";
    private String userImage = "";
    private AddFriendListener dialogListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_friend_dialog,null);
        builder.setView(view);

        builder.setTitle("添加好友");
        content = (EditText) view.findViewById(R.id.message);
        TextView textView = (TextView) view.findViewById(R.id.user_name);
        textView.setText(userName);
        TextView btn_cancel = (TextView) view.findViewById(R.id.btn_cancel);
        TextView btn_send = (TextView) view.findViewById(R.id.btn_send);
        ImageView imageView = (ImageView) view.findViewById(R.id.user_image);
        Picasso.with(getActivity()).load(userImage).resize(40,40).placeholder(R.drawable.image_placeholder).into(imageView);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput()){
                    dialogListener.OkClick(AddFriendDialog.this,content.getText().toString());
                    AddFriendDialog.this.getDialog().cancel();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }

    public interface AddFriendListener {
        void OkClick(DialogFragment dialog, String mes);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            dialogListener = (AddFriendListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    private boolean checkInput(){
        String str = content.getText().toString();
        if(str.length()>10){
            content.setError("字数太多了");
            return false;
        }
        return true;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}