package app;

import conexion.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import modelo.orm.ClienteEntidad;

public class PruebaCRUDTransacciones {
public static void main (String[]args){
//pruebaInsertar();
    PruebaEliminar();
}

public static void pruebaInsertar(){
    EntityManager em= JpaUtil.abrir();

    EntityTransaction tx= em.getTransaction();

    try {
        ClienteEntidad cliente= new ClienteEntidad(
                null,
                "pruebaa ORM",
                "pruebaa@prueba.com",
                true
        );
        System.out.println("valor de ID antes del persist: " +cliente.getId());
        tx.begin();
        em.persist(cliente);
        System.out.println("valor de ID antes del persist:"  + cliente.getId());

       em.flush();
       throw  new RuntimeException("ERROR PROVOCADO");
      //  System.out.println("valor de ID antes del persist:"  + cliente.getId());
    }catch (RuntimeException e){

        if (tx.isActive()){
            tx.rollback();
        }
        throw  e ;

    }finally {
        em.close();
    }


}
public  static void PruebaEliminar(){

    EntityManager em= JpaUtil.abrir();

    EntityTransaction tx= em.getTransaction();

    try {
        tx.begin();
        ClienteEntidad cliente= new ClienteEntidad(
                null,
                "prueba ORM eliminacion",
                "pruebae@prueba.com",
                true
        );
        em.persist(cliente);
        em.flush();

        Integer id= cliente.getId();
        System.out.println("ID de cliente : "+id);

        em.remove(cliente);
        tx.commit();

    } catch (RuntimeException e){
        if (tx.isActive()){
            tx.rollback();
        }
       throw e;
    }

    }

        }
