package com.example.ordermgmt.service.report;

import com.example.ordermgmt.data.DataGenerator;
import com.example.ordermgmt.domain.order.Order;
import com.example.ordermgmt.service.InvoiceReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("InvoiceReport Test")
public class InvoiceReportTest {

    private InvoiceReport report;

    private void initReportOrder() {
        Order order = DataGenerator.generateSampleOrder();
        report = new InvoiceReport();
        report.setOrder(order);
    }

    @BeforeEach
    public void init() {
        initReportOrder();
    }

    @DisplayName("Print order report")
    @Test
    void testPrintOrder() {
        report.printReport();
    }
}
