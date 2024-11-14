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
        super("База данных ресторана");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Создаем таблицы
        employeeTable = new JTable();
        orderTable = new JTable();
        orderDetailsTable = new JTable();
        dishTable = new JTable();
        tableTable = new JTable();

        // Создаем модели таблиц
        employeeTableModel = new DefaultTableModel();
        orderTableModel = new DefaultTableModel();
        orderDetailsTableModel = new DefaultTableModel();
        dishTableModel = new DefaultTableModel();
        tableTableModel = new DefaultTableModel();

        // Устанавливаем модели для таблиц
        employeeTable.setModel(employeeTableModel);
        orderTable.setModel(orderTableModel);
        orderDetailsTable.setModel(orderDetailsTableModel);
        dishTable.setModel(dishTableModel);
        tableTable.setModel(tableTableModel);

        // Создаем вкладки
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Сотрудники", new JScrollPane(employeeTable));
        tabbedPane.addTab("Заказы", new JScrollPane(orderTable));
        tabbedPane.addTab("Детали заказа", new JScrollPane(orderDetailsTable));
        tabbedPane.addTab("Блюда", new JScrollPane(dishTable));
        tabbedPane.addTab("Столы", new JScrollPane(tableTable));

        // Создаем кнопку "Обновить"
        JButton updateButton = new JButton("Обновить");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTables();
            }
        });

        // Добавляем кнопку в панель инструментов
        JToolBar toolBar = new JToolBar();
        toolBar.add(updateButton);

        // Добавляем вкладки и кнопку в фрейм
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Устанавливаем соединение с базой данных
        connectToDatabase();

        // Заполняем таблицы
        updateTables();

        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "password");
            System.out.println("Соединение с базой данных установлено.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при подключении к базе данных: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTables() {
        try {
            // Обновляем данные в таблице "Сотрудники"
            updateEmployeeTable();

            // Обновляем данные в таблице "Заказы"
            updateOrderTable();

            // Обновляем данные в таблице "Детали заказа"
            updateOrderDetailsTable();

            // Обновляем данные в таблице "Блюда"
            updateDishTable();

            // Обновляем данные в таблице "Столы"
            updateTableTable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка при обновлении данных: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Методы для обновления таблиц
    private void updateEmployeeTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
        employeeTableModel.setDataVector(getResultSetData(rs), new String[]{"ID", "Имя", "Фамилия", "Должность"});
        rs.close();
        stmt.close();
    }

    private void updateOrderTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Order");
        orderTableModel.setDataVector(getResultSetData(rs), new String[]{"ID заказа", "Дата заказа", "Стол", "Статус заказа"});
        rs.close();
        stmt.close();
    }

    private void updateOrderDetailsTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM OrderDetails");
        orderDetailsTableModel.setDataVector(getResultSetData(rs), new String[]{"ID детали заказа", "ID заказа", "ID блюда", "Количество"});
        rs.close();
        stmt.close();
    }

    private void updateDishTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Dish");
        dishTableModel.setDataVector(getResultSetData(rs), new String[]{"ID блюда", "Название", "Цена"});
        rs.close();
        stmt.close();
    }

    private void updateTableTable() throws SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM Table");
        tableTableModel.setDataVector(getResultSetData(rs), new String[]{"ID стола", "Номер стола", "Количество мест"});
        rs.close();
        stmt.close();
    }

    private Object[][] getResultSetData(ResultSet rs) throws SQLException {
        // Получаем количество столбцов
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Получаем количество строк
        rs.last();
        int rowCount = rs.getRow();
        rs.beforeFirst();

        // Создаем двумерный массив для хранения данных
        Object[][] data = new Object[rowCount][columnCount];

        // Заполняем массив данными
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
