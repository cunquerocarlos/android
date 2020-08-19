package com.example.prueba;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StatementRecyclerView extends RecyclerView.Adapter<StatementRecyclerView.ViewHolder> {

    private List<Statement> statementList;
    private Context context;
    public StatementRecyclerView(Context context , List<Statement> statementList){
        this.statementList = statementList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statement_item_list, parent, false);

        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Statement statement = this.statementList.get(position);
        String symbol = "";
        String currency = "";
        holder.tvDescription.setText(statement.getDescription());
        if(statement.getType().equals("DEBIT")) {
            holder.tvAmount.setTextColor(this.context.getResources().getColor(R.color.colorDebit));
            symbol = "-";
        }else if(statement.getType().equals("CREDIT")) {
            holder.tvAmount.setTextColor(this.context.getResources().getColor(R.color.colorCredit));
            symbol = "+";
        }
        if(statement.getCurrency().equals("USD"))
            currency = "$";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            Date date = dateFormat.parse(statement.getDate());
            Calendar cal = Calendar.getInstance();
            assert date != null;
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);

            holder.tvDate.setText(day + "/" + (month +1 )  + "/" + year + " - " + hour + ":" + minute);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvAmount.setText(symbol + " " + currency + " " + statement.getAmount());

    }

    @Override
    public int getItemCount() {
        return this.statementList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        private TextView tvDescription, tvDate, tvAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvAmount = itemView.findViewById(R.id.tv_amount);
        }
    }


}
