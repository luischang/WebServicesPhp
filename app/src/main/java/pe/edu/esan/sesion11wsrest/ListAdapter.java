package pe.edu.esan.sesion11wsrest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LCHANG on 15/11/2017.
 */

public class ListAdapter extends ArrayAdapter<Usuario> {

    List<Usuario> listado;

    public ListAdapter(Context context, List<Usuario> lista) {
        super(context, R.layout.item_usuario, lista);
        listado = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.item_usuario, null);
        TextView lblCorreo = view.findViewById(R.id.txtItemCorreo);
        TextView lblNombre = view.findViewById(R.id.txtItemNombre);
        Usuario usuario = listado.get(position);
        lblCorreo.setText(usuario.getCorreo());
        lblNombre.setText(usuario.getNombre());

        return view;
    }


}
