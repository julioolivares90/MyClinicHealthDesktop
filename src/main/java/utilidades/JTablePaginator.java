package utilidades;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public abstract class JTablePaginator<T> extends AbstractTableModel {
    private List<T> lisadoDeFilas = new ArrayList<T>();

    public List<T> getLisadoDeFilas() {
        return lisadoDeFilas;
    }

    public void setLisadoDeFilas(List<T> lisadoDeFilas) {
        this.lisadoDeFilas = lisadoDeFilas;
    }

    public int getRowCount() {
        return lisadoDeFilas.size();
    }


    public Object getValueAt(int filas, int columnas) {
        T t = lisadoDeFilas.get(filas);
        return getValueAt(t,columnas);
    }
    public abstract Object getValueAt(T t,int columnas);

    @Override
    public abstract String getColumnName(int columnas);
}
