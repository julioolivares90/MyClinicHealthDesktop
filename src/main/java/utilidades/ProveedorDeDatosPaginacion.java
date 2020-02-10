package utilidades;

import java.util.List;

public interface ProveedorDeDatosPaginacion<T> {

    int getTotalRowCount();
    List<T> getRows(int startIndex,int endIndex);
}
