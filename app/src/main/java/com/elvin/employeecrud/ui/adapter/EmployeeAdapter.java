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
import com.elvin.employeecrud.core.RetrofitUtils;
import com.elvin.employeecrud.model.entity.Employee;
import com.elvin.employeecrud.model.service.EmployeeService;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Elvin Shrestha on 23/12/19
 */
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private Context context;
    private List<Employee> employees;
    private EmployeeListener employeeListener;

    public interface EmployeeListener {
        void onDelete();
    }

    public EmployeeAdapter(Context context, List<Employee> employees, EmployeeListener employeeListener) {
        this.context = context;
        this.employees = employees;
        this.employeeListener = employeeListener;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EmployeeViewHolder holder, final int position) {
        Employee employee = this.employees.get(position);
        holder.civEmployeeImage.setImageResource(R.drawable.trans);
        holder.tvName.setText(employee.getName());
        holder.tvAge.setText(String.valueOf(employee.getAge()));
        holder.tvSalary.setText(String.valueOf(employee.getSalary()));
        holder.tvId.setText(String.valueOf(employee.getId()));

        holder.civEmployeeImage.setOnClickListener(v -> Toast.makeText(context, String.format("Hello this is: %s", holder.tvName.getText().toString()), Toast.LENGTH_SHORT).show());
        holder.ibtnRemove.setOnClickListener(v -> {
            RetrofitUtils.getInstance().create(EmployeeService.class).delete(Long.valueOf(holder.tvId.getText().toString())).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    employeeListener.onDelete();
                    Toast.makeText(context, "Deleted Successfully!!!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(context, "Failed to deleted!!!", Toast.LENGTH_SHORT).show();
                }
            });
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return this.employees.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        CircleImageView civEmployeeImage;
        TextView tvName, tvAge, tvSalary, tvId;
        ImageButton ibtnRemove;

        public EmployeeViewHolder(@NonNull final View itemView) {
            super(itemView);
            civEmployeeImage = itemView.findViewById(R.id.civEmployeeImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvSalary = itemView.findViewById(R.id.tvSalary);
            tvId = itemView.findViewById(R.id.tvId);
            ibtnRemove = itemView.findViewById(R.id.ibtnRemove);
        }
    }

}
