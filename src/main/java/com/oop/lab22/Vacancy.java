package com.oop.lab22;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Vacancy {

    int id;
    String name;
    String description;
    String required_experience;
    Boolean diploma;
    double salary;

    /*
    Конструктор з параметрами
     */
    public Vacancy(String name, String description, String required_experience, Boolean diploma, double salary) {
        this.name = name;
        this.description = description;
        this.required_experience = required_experience;
        this.diploma = diploma;
        this.salary = salary;
    }

    /*
    Конструктор з параметрами
     */

    public Vacancy() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRequired_experience() {
        return required_experience;
    }

    public Boolean getDiploma() {
        return diploma;
    }

    public double getSalary() {
        return salary;
    }

    /*
    toString метод
     */
    @Override
    public String toString() {
        return name + ": "
                + description
                +", experience: " + required_experience
                +", diploma required: " + diploma +
                ", salary: " + salary;
    }

    /*
    Метод getAll
    З'єднується з базою даних
    Ствроює об'єкти класу Vacancy, записує у список
    Повертає список об'єктів
     */
    public List<Vacancy> getAll(){
        List<Vacancy> vacancies = new LinkedList<>();
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employment_service", "root", "springcourse");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from vacancy");
            while (resultSet.next()){
                Vacancy product = new Vacancy(resultSet.getString("name"),
                        resultSet.getString("description"), resultSet.getString("required_experience"),
                        resultSet.getBoolean("diploma"), resultSet.getDouble("salary"));
                vacancies.add(product);

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return vacancies;
    }

    /*
    Метод toFile, який створює файл (якщо такий не існує) за шляхом C:/Users/-/Desktop/result.txt
    Записує у файл дані із таблиці
     */
    public void toFile() throws IOException {
        File result = new File("C:/Users/-/Desktop/result.txt");
        if(result.exists()){
            System.out.println("File exists");
        }else{
            result.createNewFile();

        }
        FileWriter fileWriter = new FileWriter(result);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        Vacancy vacancy = new Vacancy();
        for(int i=0; i<vacancy.getAll().size(); i++) {

            String string = vacancy.getAll().get(i).toString();
            string+="\n";
            bufferedWriter.write(string);
        }
        bufferedWriter.close();
    }

    /*
    Метод showResults виводить у консоль результати методів getAll та toFile
     */
    public void showResults() throws IOException {
        Vacancy vacancy = new Vacancy();
        System.out.println("Data from table:");
        for(int i=0; i<vacancy.getAll().size(); i++) {
            System.out.println(vacancy.getAll().get(i).toString());
        }

        System.out.println("Data from file:");
        vacancy.toFile();
        FileReader file = new FileReader("C:/Users/-/Desktop/result.txt");
        BufferedReader bufferedReader = new BufferedReader(file);
        String string = bufferedReader.readLine();
        while (string != null){
            System.out.println(string);
            string = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    /*
    Метод addVacancy приймає String name, String description, String required_experience, Boolean diploma, double salary
    З'єднується з базою даних employment_service
    Додає поле у таблицю vacancy із введеними значеннями
     */
    public void addVacancy(String name, String description, String required_experience, Boolean diploma, double salary){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employment_service", "root", "springcourse");
            Statement statement = connection.createStatement();

            PreparedStatement sql = connection.prepareStatement("insert into vacancy "
                    +" (`name`, `description`, `required_experience`, `diploma`, `salary`)"
                    +" values (?, ?, ?, ?, ?)");

            sql.setString(1, name);
            sql.setString(2, description);
            sql.setString(3, required_experience);
            sql.setBoolean(4, diploma);
            sql.setDouble(5, salary);
            sql.executeUpdate();


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
