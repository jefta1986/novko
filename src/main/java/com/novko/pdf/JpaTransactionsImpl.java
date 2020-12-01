//package com.novko.pdf;
//
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Repository
//public class JpaTransactionsImpl implements JpaTransactions{
//
//
//    private EntityManager entityManager;
//
//    @PersistenceContext
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    @Transactional
//    public void save(JasperReportModel model) {
//        entityManager.persist(model);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public JasperReportModel getById(Long id) {
//        JasperReportModel transaction = entityManager.find(JasperReportModel.class, id);
//        return transaction;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<JasperReportModel> getByOrderId(Long id) {
//        List<JasperReportModel> transactions = entityManager.createQuery("from JasperReportModel t where t.orderId = ?1").setParameter(1, id).getResultList();
//        return transactions;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<JasperReportModel> getAll() {
//        List<JasperReportModel> transactions = entityManager.createQuery("from JasperReportModel").getResultList();
//        return transactions;
//    }
//}
