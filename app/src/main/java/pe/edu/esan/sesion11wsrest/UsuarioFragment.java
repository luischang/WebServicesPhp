package pe.edu.esan.sesion11wsrest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class UsuarioFragment extends Fragment {
    EditText txtCorreoF;
    EditText txtClaveF;
    EditText txtConfirmarClaveF;
    EditText txtNombreCompletoF;
    Button btnGrabar;
    Button btnNotificar;
    ListView lsvUsuario;


    public UsuarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        txtCorreoF = view.findViewById(R.id.txtCorreoF);
        txtClaveF = view.findViewById(R.id.txtClaveF);
        txtConfirmarClaveF = view.findViewById(R.id.txtConfirmarClaveF);
        txtNombreCompletoF = view.findViewById(R.id.txtNombreCompletoF);
        lsvUsuario = view.findViewById(R.id.lsvUsuario);
        btnGrabar = view.findViewById(R.id.btnGrabar);
        //btnNotificar = view.findViewById(R.id.btnNotificar);
        btnGrabar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                GrabarUsuario(txtCorreoF.getText().toString(), txtClaveF.getText().toString(),txtNombreCompletoF.getText().toString());
                CargarUsuario();
            }
        });

        this.CargarUsuario();
        return view;
    }

    private void GrabarUsuario(String correo, String clave, String nombreCompleto){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final int SIMPLE_REQUEST = 1;
        String url = "http://www.kreapps.biz/esan/grabar.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, "CORREO=" + correo+ "&CLAVE="+clave + "&NOMBRECOMPLETO="+nombreCompleto, new Response.Listener<JSONArray>() {
            String valCor = "";

            @Override
            public void onResponse(JSONArray response) {
                //Procesar JSONObject
                //Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                try {
                    Toast.makeText(getActivity().getApplicationContext(), "SE REGISTRO EXITOSAMENTE", Toast.LENGTH_LONG).show();

                } catch (Error error) {
                    Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Procesar VolleyError
                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        //request.setTag(SIMPLE_REQUEST);
        requestQueue.add(request);



    }

    private void CargarUsuario() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final int SIMPLE_REQUEST = 1;
        String url = "http://www.kreapps.biz/esan/usuario.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, "ID=-1", new Response.Listener<JSONArray>() {
            String valCor = "";

            @Override
            public void onResponse(JSONArray response) {
                //Procesar JSONObject
                Toast.makeText(getActivity().getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                try {
                    List<Usuario> listadoUsuario = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonChildNode = response.getJSONObject(i);
                        String correo = jsonChildNode.optString("Correo");
                        String nombreCompleto = jsonChildNode.optString("NombreCompleto");

                        listadoUsuario.add(new Usuario("", correo, nombreCompleto));
                    }
                    ListAdapter listAdapter = new ListAdapter(getActivity().getBaseContext(), listadoUsuario);
                    lsvUsuario.setAdapter(listAdapter);

                } catch (JSONException error) {
                    Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Procesar VolleyError
                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        //request.setTag(SIMPLE_REQUEST);
        requestQueue.add(request);
    }


}
