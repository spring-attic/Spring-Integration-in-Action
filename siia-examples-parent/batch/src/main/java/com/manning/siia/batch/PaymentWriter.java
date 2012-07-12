package com.manning.siia.batch;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @author Marius Bogoevici
 */
public class PaymentWriter implements ItemWriter<Payment> {

    private SimpleJdbcInsert paymentInsert;
    private JdbcTemplate accountUpdate;

   public PaymentWriter(DataSource dataSource) {
        paymentInsert = new SimpleJdbcInsert(dataSource).withTableName("PAYMENTS").usingColumns("RECIPIENT", "PAYEE", "AMOUNT", "PAY_DATE");
        accountUpdate = new JdbcTemplate(dataSource);
    }

    @Override
    public void write(List<? extends Payment> payments) throws Exception {
        for (Payment payment : payments) {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("RECIPIENT", payment.getDestinationAccountNo()).addValue("PAYEE", payment.getSourceAccountNo())
                    .addValue("AMOUNT", payment.getAmount()).addValue("DATE", payment.getDate());
            accountUpdate.update("UPDATE ACCOUNTS SET BALANCE = BALANCE + ? WHERE ID = ?", payment.getAmount(), payment.getDestinationAccountNo());
            accountUpdate.update("UPDATE ACCOUNTS SET BALANCE = BALANCE - ? WHERE ID = ?", payment.getAmount(), payment.getSourceAccountNo());
            paymentInsert.execute(parameterSource);
           System.out.println("Executing the step " + payment.getDate());
        }
    }
}
