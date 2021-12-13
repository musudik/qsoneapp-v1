package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.qsone.entity.Invoice;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
}