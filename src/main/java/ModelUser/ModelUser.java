package ModelUser;

import Default.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ModelUser {

    public ModelUser() {
    }
    
    private static EntityManagerFactory emf;

    private static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Usuarios");
        }
        return emf.createEntityManager();
    }

    public List<Usuarios> getUsuarios() {
        List<Usuarios> usuarios = new ArrayList<>();

        EntityManager em = getEntityManager();

        Query query = em.createQuery("select u from Usuario", Usuarios.class);
        usuarios = query.getResultList();

        return usuarios;
    }

    public boolean addUsuario(String nome, String usuario, String senha, String cpf) {
        Usuarios user = new Usuarios(nome, usuario, senha, cpf);
        try {
            EntityManager em = getEntityManager();

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            em.close();
            return true;
        } catch (NonUniqueResultException e) {
            return false;
        }
    }

    public boolean logar(String usuario, String senha) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("select u from Usuarios u where usuario = :usuario and senha = :senha")
                    .setParameter("usuario", usuario).setParameter("senha", senha);
            Usuarios verifica = (Usuarios) query.getSingleResult();

            em.close();
            if (verifica == null) {
                return false;
            } else {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        }
    }

    public boolean vfExisteUsuario(String usuario) {
        try {
            EntityManager em = emf.createEntityManager();
            Usuarios doBanco = (Usuarios) em.createQuery("select u from Usuarios u where usuario = :usuario")
                    .setParameter("usuario", usuario).getSingleResult();
            String verificaUser;
            if (doBanco != null) {
                verificaUser = doBanco.getUsuario();
                if (verificaUser.equals(usuario)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean vfExisteId(String id) {
        try {
            EntityManager em = emf.createEntityManager();
            Usuarios doBanco = (Usuarios) em.createQuery("select u from userProjeto u where id = :id")
                    .setParameter("id", id).getSingleResult();
            String idBanco = doBanco.getId();
            
            if(idBanco.equals(id)){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public Usuarios validaLogin(String usuario, String senha) {
        try {
            EntityManager em = emf.createEntityManager();
            Usuarios dobanco = (Usuarios) em.createQuery("select u from userProjeto u where usuario = :usuario and senha = :senha and ")
                    .setParameter("usuario", usuario).setParameter("senha", senha).getSingleResult();
            return dobanco;
        } catch (Exception e) {
            return null;
        }
    }
}
