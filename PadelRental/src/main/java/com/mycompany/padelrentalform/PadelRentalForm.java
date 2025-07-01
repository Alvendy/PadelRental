package com.mycompany.padelrentalform;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PadelRentalForm extends JFrame {
    private JTextField tfNama, tfNoHP, tfTanggal, tfJamMulai, tfJamSelesai;
    private JComboBox<String> cbLapangan;
    private ArrayList<String[]> dataList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;

    public PadelRentalForm() {
        setTitle("Form Sewa Lapangan Padel");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel labelPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        labelPanel.add(new JLabel("Nama Penyewa:"));
        labelPanel.add(new JLabel("No HP:"));
        labelPanel.add(new JLabel("Tanggal Sewa:"));
        labelPanel.add(new JLabel("Jam Mulai:"));
        labelPanel.add(new JLabel("Jam Selesai:"));
        labelPanel.add(new JLabel("Lapangan:"));
        labelPanel.add(new JLabel(""));

        JPanel inputPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        tfNama = new JTextField();
        tfNoHP = new JTextField();
        tfTanggal = new JTextField("yyyy-mm-dd");
        tfJamMulai = new JTextField("08:00");
        tfJamSelesai = new JTextField("09:00");
        String[] lapanganOptions = { "Lapangan 1", "Lapangan 2", "Lapangan 3", "Lapangan 4" };
        cbLapangan = new JComboBox<>(lapanganOptions);
        JButton btnSimpan = new JButton("Simpan");
        JButton btnHapus = new JButton("Hapus");
        JButton btnEdit = new JButton("Edit");

        inputPanel.add(tfNama);
        inputPanel.add(tfNoHP);
        inputPanel.add(tfTanggal);
        inputPanel.add(tfJamMulai);
        inputPanel.add(tfJamSelesai);
        inputPanel.add(cbLapangan);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnEdit);
        inputPanel.add(buttonPanel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(labelPanel, BorderLayout.WEST);
        topPanel.add(inputPanel, BorderLayout.CENTER);

        String[] columnNames = {"Nama", "No HP", "Tanggal", "Jam Mulai", "Jam Selesai", "Lapangan"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnSimpan.addActionListener(e -> {
            String nama = tfNama.getText();
            String noHP = tfNoHP.getText();
            String tanggal = tfTanggal.getText();
            String jamMulai = tfJamMulai.getText();
            String jamSelesai = tfJamSelesai.getText();
            String lapangan = (String) cbLapangan.getSelectedItem();

            if (!nama.isEmpty() && !noHP.isEmpty() && !tanggal.isEmpty()) {
                dataList.add(new String[]{nama, noHP, tanggal, jamMulai, jamSelesai, lapangan});
                tableModel.addRow(new Object[]{nama, noHP, tanggal, jamMulai, jamSelesai, lapangan});
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                tfNama.setText(""); tfNoHP.setText(""); tfTanggal.setText("yyyy-mm-dd");
                tfJamMulai.setText("08:00"); tfJamSelesai.setText("09:00");
                cbLapangan.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Isi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnHapus.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                dataList.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data pada tabel yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        btnEdit.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String nama = tfNama.getText();
                String noHP = tfNoHP.getText();
                String tanggal = tfTanggal.getText();
                String jamMulai = tfJamMulai.getText();
                String jamSelesai = tfJamSelesai.getText();
                String lapangan = (String) cbLapangan.getSelectedItem();

                if (!nama.isEmpty() && !noHP.isEmpty() && !tanggal.isEmpty()) {
                    dataList.set(selectedRow, new String[]{nama, noHP, tanggal, jamMulai, jamSelesai, lapangan});
                    tableModel.setValueAt(nama, selectedRow, 0);
                    tableModel.setValueAt(noHP, selectedRow, 1);
                    tableModel.setValueAt(tanggal, selectedRow, 2);
                    tableModel.setValueAt(jamMulai, selectedRow, 3);
                    tableModel.setValueAt(jamSelesai, selectedRow, 4);
                    tableModel.setValueAt(lapangan, selectedRow, 5);
                    JOptionPane.showMessageDialog(this, "Data berhasil diedit!");
                    tfNama.setText(""); tfNoHP.setText(""); tfTanggal.setText("yyyy-mm-dd");
                    tfJamMulai.setText("08:00"); tfJamSelesai.setText("09:00");
                    cbLapangan.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this, "Isi semua data!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data pada tabel yang akan diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    tfNama.setText((String) tableModel.getValueAt(row, 0));
                    tfNoHP.setText((String) tableModel.getValueAt(row, 1));
                    tfTanggal.setText((String) tableModel.getValueAt(row, 2));
                    tfJamMulai.setText((String) tableModel.getValueAt(row, 3));
                    tfJamSelesai.setText((String) tableModel.getValueAt(row, 4));
                    cbLapangan.setSelectedItem(tableModel.getValueAt(row, 5));
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PadelRentalForm().setVisible(true));
    }
}
