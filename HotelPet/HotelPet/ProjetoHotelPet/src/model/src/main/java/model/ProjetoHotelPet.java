/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.mycompany.gui.FrTelaInicial;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author cayo
 */
public class ProjetoHotelPet {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("exemplo-jpa");
        
        FrTelaInicial telaInicial = new FrTelaInicial();
        telaInicial.setVisible(true);
        
        factory.close();
    }
}
