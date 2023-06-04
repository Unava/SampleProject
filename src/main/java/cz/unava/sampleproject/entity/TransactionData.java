package cz.unava.sampleproject.entity;

import cz.unava.sampleproject.enums.ResponseCodeEnum;
import cz.unava.sampleproject.model.ArchiveTransaction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class TransactionData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_data_seq")
    @SequenceGenerator(name = "transaction_data_seq", sequenceName = "transaction_data_id_seq", allocationSize = 1)
    private Long id;

    private Date startTransaction;

    private Date endTransaction;

    private ResponseCodeEnum responseCode;

    private Long transactionAmount;

    public void setStartTransaction() {
        this.startTransaction = new Date();
    }

    public void setEndTransaction() {
        this.endTransaction = new Date();
    }

    public ArchiveTransaction mapToJson() {
        final ArchiveTransaction archiveTransaction = new ArchiveTransaction();
        archiveTransaction.setEndTransaction(endTransaction);
        archiveTransaction.setStartTransaction(startTransaction);
        archiveTransaction.setResponseCode(responseCode.name());
        archiveTransaction.setAmount(transactionAmount);
        return archiveTransaction;
    }

}
