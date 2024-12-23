/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.HistoricoReserva;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author neidi
 */
public class HistoricoReservaDao extends DAO {


    public HistoricoReservaDao() {
        super("historico_reserva.csv");
    }

    public HistoricoReservaDao(String pathArquivo) {
        super(pathArquivo);
    }

    @Override
    public boolean delete(Object obj) {
        if (obj instanceof HistoricoReserva) {
            HistoricoReserva reserva = (HistoricoReserva) obj;
            List<HistoricoReserva> reservas = findAll();

            boolean removido = reservas.removeIf(r -> r.getCpf().equals(reserva.getCpf()) 
                                                   && r.getNomePet().equals(reserva.getNomePet()));
            if (removido) {
                saveAll(reservas);
                return true;
            }
        }
        return false;
    }

    @Override
    public HistoricoReserva find(Object obj) {
        if (obj instanceof HistoricoReserva) {
            HistoricoReserva reserva = (HistoricoReserva) obj;
            List<HistoricoReserva> reservas = findAll();

            for (HistoricoReserva r : reservas) {
                if (r.getCpf().equals(reserva.getCpf()) && r.getNomePet().equals(reserva.getNomePet())) {
                    return r;
                }
            }
        }
        return null;
    }


    public List<HistoricoReserva> findAll() {
        List<HistoricoReserva> reservas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.pathArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                HistoricoReserva reserva = fromCSV(linha);
                reservas.add(reserva);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
        return reservas;
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

    public HistoricoReserva findByCpf(String cpf) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

