/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.ejb.dao;

import cl.auter.ejb.entity.bmp.DocumentoEntity;
import javax.persistence.EntityManager;

/**
 *
 * @author Marco
 */
public class DocumentoDAO extends BaseDAO<DocumentoEntity>{

    public DocumentoDAO(Class<DocumentoEntity> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    
}
