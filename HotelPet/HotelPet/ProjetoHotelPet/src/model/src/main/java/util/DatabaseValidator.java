package util;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class DatabaseValidator {
    
    public static boolean validateConnection() {
        EntityManager em = null;
        try {
            em = EntityManagerUtil.getEntityManager();
            return em.isOpen();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao conectar ao banco de dados:\n" + e.getMessage(),
                "Erro de Conexão",
                JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
