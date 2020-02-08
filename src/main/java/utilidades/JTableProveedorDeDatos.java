package utilidades;

import java.util.List;

public interface JTableProveedorDeDatos <T> {
    int getTotalRowCount();
    List<T> getRows(int startIndex,int endIndex);

}
