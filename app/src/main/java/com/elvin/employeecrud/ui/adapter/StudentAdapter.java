package com.elvin.employeecrud.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elvin.employeecrud.R;
import com.elvin.employeecrud.core.InAppDataHolder;
import com.elvin.employeecrud.model.entity.Student;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author Elvin Shrestha on 13/12/19
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> students;
    private OnUpdateListener onUpdateListener;

    public interface OnUpdateListener {
        void onUpdate();
    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }

    public StudentAdapter(Context context, List<Student> students, OnUpdateListener onUpdateListener) {
        this.context = context;
        this.students = students;
        this.setOnUpdateListener(onUpdateListener);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentViewHolder holder, final int position) {
        Student student = this.students.get(position);
        switch (student.getGender()) {
            case MALE:
                holder.civStudentImage.setImageResource(R.drawable.male);
                break;
            case FEMALE:
                holder.civStudentImage.setImageResource(R.drawable.female);
                break;
            case OTHER:
                holder.civStudentImage.setImageResource(R.drawable.trans);
                break;
        }
        holder.tvName.setText(student.getFullName());
        holder.tvAge.setText(String.valueOf(student.getAge()));
        holder.tvAddress.setText(student.getAddress());
        holder.tvGender.setText(student.getGender().toString());

        holder.civStudentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.format("Hello this is: %s", holder.tvName.getText().toString()), Toast.LENGTH_SHORT).show();
            }
        });
        holder.ibtnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InAppDataHolder.students.remove(position);
//                notifyItemChanged(position);
                onUpdateListener.onUpdate(); // custom notifyItemChanged()
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        CircleImageView civStudentImage;
        TextView tvName, tvAge, tvAddress, tvGender;
        ImageButton ibtnRemove;

        public StudentViewHolder(@NonNull final View itemView) {
            super(itemView);
            civStudentImage = itemView.findViewById(R.id.civStudentImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvGender = itemView.findViewById(R.id.tvGender);
            ibtnRemove = itemView.findViewById(R.id.ibtnRemove);
        }
    }

}
