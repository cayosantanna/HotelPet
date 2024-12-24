/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.HistoricoReserva;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author neidi
 */
public class HistoricoReservaDao extends DAO {
    
    private List<HistoricoReserva> lst;

    public HistoricoReservaDao() {
        super("historico_reserva.csv");
        this.lst = new ArrayList<>();
    }
    

    public HistoricoReservaDao(String pathArquivo) {
        super(pathArquivo);
    }

    
    
    

    @Override
    public HistoricoReserva find(Object obj) {
       HistoricoReserva historico = (HistoricoReserva) obj;
                
        for(HistoricoReserva h: this.lst){
            if(h.equals(historico))
                return h;
        }
        
        return null;
    }


    public List<HistoricoReserva> findAll() {
        this.lst = loadArquivo();
        
        if(this.lst == null)
            return new ArrayList<>();
        else
            return this.lst; 
    }

    // Converte uma linha de texto (CSV) para um objeto HistoricoReserva
    private HistoricoReserva fromCSV(String linha) {
        String[] partes = linha.split(",");
        HistoricoReserva reserva = new HistoricoReserva();
        reserva.setNomePet(partes[0]);
        reserva.setCpf(partes[1]);
        return reserva;
    }

    // Converte um objeto HistoricoReserva para uma string no formato CSV
    private String toCSV(HistoricoReserva reserva) {
        return reserva.getNomePet() + "," + reserva.getCpf();
    }

    // Salva todas as reservas no arquivo
    private void saveAll(List<HistoricoReserva> reservas) {
        StringBuilder sb = new StringBuilder();
        for (HistoricoReserva reserva : reservas) {
            sb.append(toCSV(reserva)).append("\n");
        }
        save(sb.toString());
    }

    
   
    

     
    /**
     * Este metodo é um bom exemplo para usar heranca e metodos abstratos
     * Dá para subir com este metodo para SUPER.
     * O metodo é igual para ALUNO e PROFESSOR e tem a mesma chamada de CSVToAtributo. Logo este pode
     * subir e temos que garantir que ALUNO e PROFESSOR implementem CSVToAtributo
     * @return 
     */
    private List<HistoricoReserva> loadArquivo() {
        FileReader f = null;
        try {
            f = new FileReader(this.pathArquivo);//"ListagemProfessores.csv");
            Scanner arquivoLido = new Scanner(f);
            arquivoLido.useDelimiter("\n");
            
            List<HistoricoReserva> lista = new ArrayList<>();
            String linhaLida = arquivoLido.next();
            while (arquivoLido.hasNext()) {
                linhaLida = arquivoLido.next();

                HistoricoReserva aluno = new HistoricoReserva();
                aluno.CSVToAtributo(linhaLida);
                lista.add(aluno);
            }
            return lista;
        } catch (FileNotFoundException ex) {
            
        } finally {
            try {
                f.close();
            } catch (IOException ex) {
                
            }
        }
        return null;
    }

    @Override
    public boolean delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

