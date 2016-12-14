package team.com.ibus.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import team.com.ibus.Dominio.Onibus;
import team.com.ibus.R;
import team.com.ibus.service.OnibusService;

public class CadastrarOnibus extends AppCompatActivity {

    ProgressDialog pdialog;
    Context context;
    Onibus onibus;
    Bundle bundle;
    int idOnibus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_onibus);

        context = this;

        bundle = getIntent().getExtras();


        Button btCadastrar = (Button) findViewById(R.id.bt_cadastrar_onibus);

        TextInputLayout tilPlaca = (TextInputLayout) findViewById(R.id.textInputLayoutPlaca);
        TextInputLayout tilCor = (TextInputLayout) findViewById(R.id.textInputLayoutCor);

        if(bundle != null){

            tilPlaca.getEditText().setText(bundle.getString("placa_onibus"));
            tilCor.getEditText().setText(bundle.getString("cor_onibus"));
            idOnibus = bundle.getInt("id");
            btCadastrar.setText("Atualizar");
        }

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputLayout tilPlaca = (TextInputLayout) findViewById(R.id.textInputLayoutPlaca);
                TextInputLayout tilCor = (TextInputLayout) findViewById(R.id.textInputLayoutCor);

                //faz verificações
                String placa = tilPlaca.getEditText().getText().toString();
                String cor = tilCor.getEditText().getText().toString();
                boolean cadastrar = true;


                if(placa.length() != 8){
                    cadastrar = false;
                    tilPlaca.setError("Quantidade de caracteres inválidos. Ex: ABC-1234");
                }else{
                    tilPlaca.setErrorEnabled(false);
                }

                if(cor.length() < 4){
                    cadastrar = false;
                    tilCor.setError("Cor inválida. Ex : Azul");
                }else{

                    tilCor.setErrorEnabled(false);
                }

                if(cadastrar){

                    onibus = new Onibus(placa,cor);

                    if(bundle != null)
                        onibus.setId(idOnibus);
                    try{

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                OnibusWSAsync onibusWSAsync = new OnibusWSAsync(context,"insertOnibus",onibus);
//
                                //se for nulo é porque não veio dados de oura activity, então foi chamado pela tela de cadastro.
                                if(bundle == null)
                                    onibusWSAsync = new OnibusWSAsync(context,"insertOnibus",onibus);
                                else
                                    onibusWSAsync = new OnibusWSAsync(context,"updateOnibus",onibus);

                                onibusWSAsync.execute();
                            }
                        });



                        //TODO: fazer algo com o retorno do service...

                    }catch (Exception ex){

                        Toast.makeText(CadastrarOnibus.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }

    private class OnibusWSAsync extends AsyncTask<Void, Void, Void> {

        private String METODO;
        private Gson gson;
        private Onibus onibus;
        private String onibusJson;
        private String retornoWS = "";
        protected Context context;
        private String NAMESPACE;
        private String URL;
        private ProgressDialog pDialog;

        OnibusWSAsync() {
        }

        OnibusWSAsync(Context context,String METODO) {

            this.METODO = METODO;
            gson = new Gson();
        }

        OnibusWSAsync(Context context, String METODO, Onibus onibus) {

            this.METODO = METODO;
            gson = new Gson();
            this.onibus = onibus;
            NAMESPACE = context.getResources().getString(R.string.namespace_server);
            URL = "http://" + context.getResources().getString(R.string.ip_server) + ":8080/WebServiceiBus/services/OnibusDAO?wsdl";
            onibusJson = gson.toJson(onibus);
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {

            if(METODO == "insertOnibus"){

                try {

                    String onibusJson = gson.toJson(onibus);

                    SoapObject inserirOnibus = new SoapObject(NAMESPACE, METODO);
                    inserirOnibus.addProperty("objOnibus", onibusJson);

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    envelope.setOutputSoapObject(inserirOnibus);

                    envelope.implicitTypes = true;

                    HttpTransportSE httpTransport = new HttpTransportSE(URL);
                    httpTransport.call("uri" + METODO, envelope);

                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    retornoWS = response.toString();

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Onibus cadastrado com sucesso : ", Toast.LENGTH_LONG).show();

                        }
                    }, 0);

                } catch (final Exception e) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Erro : " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }, 500);

                }


            }else{

                try {

                    SoapObject updateOnibus = new SoapObject(NAMESPACE, METODO);
                    updateOnibus.addProperty("objOnibus", onibusJson);

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                    envelope.setOutputSoapObject(updateOnibus);

                    envelope.implicitTypes = true;

                    HttpTransportSE httpTransport = new HttpTransportSE(URL);
                    httpTransport.call("uri" + METODO, envelope);

                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    retornoWS = response.toString();

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Onibus atualizado com sucesso", Toast.LENGTH_LONG).show();

                        }
                    }, 0);

                } catch (final Exception e) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Erro : " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }, 500);
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();


        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(context);

            if (METODO == "insertOnibus")
                pDialog.setMessage("Cadastrando...");
            else if (METODO == "updateOnibus")
                pDialog.setMessage("Atualizando...");

            pDialog.show();

        }


    }

}
