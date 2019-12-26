package br.com.hbsis.pedido;

import br.com.hbsis.fornecedor.Fornecedor;
import br.com.hbsis.funcionario.EmployeeSavingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvoiceDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceItemDTO.class);

    private Fornecedor cnpjFornecedor;
    private EmployeeSavingDTO employeeUuid;
    private InvoiceItemDTO invoiceItemDTOSet;
    private double totalValue;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Fornecedor cnpjFornecedor, EmployeeSavingDTO employeeUuid, InvoiceItemDTO invoiceItemDTOSet, double totalValue) {
        this.cnpjFornecedor = cnpjFornecedor;
        this.employeeUuid = employeeUuid;
        this.invoiceItemDTOSet = invoiceItemDTOSet;
        this.totalValue = totalValue;
    }


    public Fornecedor getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(Fornecedor cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public EmployeeSavingDTO getEmployeeUuid() {
        return employeeUuid;
    }

    public void setEmployeeUuid(EmployeeSavingDTO employeeUuid) {
        this.employeeUuid = employeeUuid;
    }

    public InvoiceItemDTO getInvoiceItemDTOSet() {
        return invoiceItemDTOSet;
    }

    public void setInvoiceItemDTOSet(InvoiceItemDTO invoiceItemDTOSet) {
        this.invoiceItemDTOSet = invoiceItemDTOSet;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
