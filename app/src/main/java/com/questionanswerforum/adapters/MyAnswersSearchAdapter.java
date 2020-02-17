package com.questionanswerforum.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.questionanswerforum.Pojo.MyAnswersPojo;
import com.questionanswerforum.R;
import com.questionanswerforum.activities.GetAnswersActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MyAnswersSearchAdapter extends BaseAdapter {
    List<MyAnswersPojo> ar9,ar1;
    Context cnt;
    public MyAnswersSearchAdapter(List<MyAnswersPojo> ar, Context cnt)
    {
        this.ar9=ar;
        this.cnt=cnt;
        this.ar1 = new ArrayList<MyAnswersPojo>();
        ar1.addAll(ar);
    }
    @Override
    public int getCount() {
        return ar1.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup)
    {
        LayoutInflater obj1 = (LayoutInflater)cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2=obj1.inflate(R.layout.recyclerview_child,null);
        TextView tv_postedby=(TextView) obj2.findViewById(R.id.tv_postedby);

        tv_postedby.setText("Post By  :"+ar1.get(position).getPost_by());
        TextView tv_subject=(TextView) obj2.findViewById(R.id.tv_subject);
        tv_subject.setText("Subject Title  :"+ar1.get(position).getSubject());
        TextView tv_qstn=(TextView) obj2.findViewById(R.id.tv_qstn);
        tv_qstn.setText("Question  :"+ar1.get(position).getQuestion());
        TextView tv_tags=(TextView) obj2.findViewById(R.id.tv_tags);
        tv_tags.setText("Tags  :"+ar1.get(position).getTags());
        Toast.makeText(cnt,""+ar1.get(position).getId(),Toast.LENGTH_LONG).show();


        Button btn_ans=(Button) obj2.findViewById(R.id.btn_ans);
        btn_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cnt, GetAnswersActivity.class);
                intent.putExtra("id",ar1.get(position).getId());
                cnt.startActivity(intent);

            }
        });
        return obj2;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ar1.clear();
        if (charText.length() == 0) {
            ar1.addAll(ar9);
        }
        else
        {
            for (MyAnswersPojo wp : ar9)
            {
                if (wp.getQuestion().toLowerCase(Locale.getDefault()).contains(charText)||wp.getTags().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    ar1.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}