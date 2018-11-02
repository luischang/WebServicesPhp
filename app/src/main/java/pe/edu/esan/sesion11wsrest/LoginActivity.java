package pe.edu.esan.sesion11wsrest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    final String KEY_PREFERENCES = "ESANPreferencias";
    final String KEY_EMAIL = "pf_email";
    final String KEY_PWD = "pf_password";
    final String KEY_CHECK = "pf_check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btnIngresar = findViewById(R.id.btnIngresar);
        final EditText txtCorreo = findViewById(R.id.txtCorreo);
        final EditText txtClave = findViewById(R.id.txtClave);
        final CheckBox chkRecordarme = findViewById(R.id.chkRecordarme);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String correo = txtCorreo.getText().toString();
                final String clave = txtClave.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                //final int SIMPLE_REQUEST = 1;
                String url = "http://www.kreapps.biz/esan/validar.php";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, "COR=" + correo + "&PWD=" + clave, new Response.Listener<JSONObject>() {
                    String valCor = "";

                    @Override
                    public void onResponse(JSONObject response) {
                        //Procesar JSONObject
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

                        try {
                            JSONArray jsonArray = response.getJSONArray("loginresult");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                valCor = jsonObject.getString("Correo");

                                if (!valCor.equals("")) {
                                    SharedPreferences setting = getSharedPreferences(KEY_PREFERENCES, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = setting.edit();
                                    if (chkRecordarme.isChecked()) {
                                        editor.putString(KEY_EMAIL, correo);
                                        editor.putString(KEY_PWD, clave);
                                        editor.putBoolean(KEY_CHECK, true);
                                    } else {
                                        editor.remove(KEY_EMAIL);
                                        editor.remove(KEY_PWD);
                                        editor.remove(KEY_CHECK);
                                    }
                                    editor.apply();
                                    //Se llena el objeto USUARIO con una �na instancia
                                    // Usuario usuario = new Usuario(correo, clave);
                                    //ControlUsuario.getInstance().current_usuario = usuario;

                                    Intent intent = new Intent().setClass(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Correo o Clave incorrecta", Toast.LENGTH_LONG).show();
                                }

                                //Toast.makeText(getApplicationContext(), correo, Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(), nombre, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException error) {


                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Procesar VolleyError
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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
        });

        SharedPreferences setting = getSharedPreferences(KEY_PREFERENCES, MODE_PRIVATE);
        String pf_correo = setting.getString(KEY_EMAIL, "");
        String pf_clave = setting.getString(KEY_PWD, "");
        boolean pf_check = setting.getBoolean(KEY_CHECK, false);

        txtCorreo.setText(pf_correo);
        txtClave.setText(pf_clave);
        chkRecordarme.setChecked(pf_check);

    }
}