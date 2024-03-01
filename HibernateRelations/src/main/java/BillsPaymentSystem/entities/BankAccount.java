package BillsPaymentSystem.entities;

public class BankAccount {

        @Column(name = "bank_name", length = 50)
        private String bankName;

        @Column(name = "SWIFT_code", length = 50)
        private String swiftCode;
}
