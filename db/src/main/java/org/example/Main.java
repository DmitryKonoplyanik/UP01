package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {

    private JTable employeeTable;
    private JTable orderTable;
    private JTable orderDetailsTable;
    private JTable dishTable;
    private JTable tableTable;

    private DefaultTableModel employeeTableModel;
    private DefaultTableModel orderTableModel;
    private DefaultTableModel orderDetailsTableModel;
    private DefaultTableModel dishTableModel;
    private DefaultTableModel tableTableModel;

    private Connection connection;

    public Main() {
        super("���� ������ ���������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // ������� �������
        employeeTable = new JTable();
        orderTable = new JTable();
        orderDetailsTable = new JTable();
        dishTable = new JTable();
        tableTable = new JTable();

        // ������� ������ ������
        employeeTableModel = new DefaultTableModel();
        orderTableModel = new DefaultTableModel();
        orderDetailsTableModel = new DefaultTableModel();
        dishTableModel = new DefaultTableModel();
        tableTableModel = new DefaultTableModel();

        // ������������� ������ ��� ������
        employeeTable.setModel(employeeTableModel);
        orderTable.setModel(orderTableModel);
        orderDetailsTable.setModel(orderDetailsTableModel);
        dishTable.setModel(dishTableModel);
        tableTable.setModel(tableTableModel);

        // ������� �������
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("����������", new JScrollPane(employeeTable));
        tabbedPane.addTab("������", new JScrollPane(orderTable));
        tabbedPane.addTab("������ ������", new JScrollPane(orderDetailsTable));
        tabbedPane.addTab("�����", new JScrollPane(dishTable));
        tabbedPane.addTab("�����", new JScrollPane(tableTable));

        // ������� ������ "��������"
        JButton updateButton = new JButton("��������");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTables();
            }
        });

        // ��������� ������ � ������ ������������
        JToolBar toolBar = new JToolBar();
        toolBar.add(updateButton);

        // ��������� ������� � ������ � �����
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // ������������� ���������� � ����� ������
        connectToDatabase();

        // ��������� �������
        updateTables();

        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "password");
            System.out.println("���������� � ����� ������ �����������.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "������ ��� ����������� � ���� ������: " + e.getMessage(), "������", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTables() {
        try {
            // ��������� ������ � ������� "����������"
            updateEmployeeTable();

            // ��������� ������ � ������� "������"
            updateOrderTable();

            // ��������� ������ � ������� "������ ������"
            updateOrderDetailsTable();

            // ��������� ������ � ������� "�����"
            updateDishTable();

            // ��������� ������ � ������� "�����"
            updateTableTable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "������ ��� ���������� ������: " + e.getMessage(), "������", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ������ ��� ���������� ������
    private void updateEmployeeTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
        employeeTableModel.setDataVector(getResultSetData(rs), new String[]{"ID", "���", "�������", "���������"});
        rs.close();
        stmt.close();
    }

    private void updateOrderTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Order");
        orderTableModel.setDataVector(getResultSetData(rs), new String[]{"ID ������", "���� ������", "����", "������ ������"});
        rs.close();
        stmt.close();
    }

    private void updateOrderDetailsTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM OrderDetails");
        orderDetailsTableModel.setDataVector(getResultSetData(rs), new String[]{"ID ������ ������", "ID ������", "ID �����", "����������"});
        rs.close();
        stmt.close();
    }

    private void updateDishTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Dish");
        dishTableModel.setDataVector(getResultSetData(rs), new String[]{"ID �����", "��������", "����"});
        rs.close();
        stmt.close();
    }

    private void updateTableTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Table");
        tableTableModel.setDataVector(getResultSetData(rs), new String[]{"ID �����", "����� �����", "���������� ����"});
        rs.close();
        stmt.close();
    }

    private Object[][] getResultSetData(ResultSet rs) throws SQLException {
        // �������� ���������� ��������
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // �������� ���������� �����
        rs.last();
        int rowCount = rs.getRow();
        rs.beforeFirst();

        // ������� ��������� ������ ��� �������� ������
        Object[][] data = new Object[rowCount][columnCount];

        // ��������� ������ �������
        int row = 0;
        while (rs.next()) {
            for (int col = 0; col < columnCount; col++) {
                data[row][col] = rs.getObject(col + 1);
            }
            row++;
        }
        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
