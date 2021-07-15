/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author excus
 */
public class Worker {

    private String id;
    private String fullname;
    private Double salary;

    public Worker() {
    }

    public Worker(String id, String fullname, Double salary) {
        this.id = id;
        this.fullname = fullname;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Worker{" + "id=" + id + ", fullname=" + fullname + ", salary=" + salary + '}';
    }

    

}
