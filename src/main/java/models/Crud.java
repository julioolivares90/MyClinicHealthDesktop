package models;

import java.util.List;

public interface Crud {
    public List listar();
    public int add(Object object);
    public int update(Object object);
    public  int delete(int id);
}
