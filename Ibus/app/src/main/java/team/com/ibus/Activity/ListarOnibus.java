package team.com.ibus.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import team.com.ibus.Dominio.Onibus;
import team.com.ibus.R;
import team.com.ibus.adapter.AdapterListViewOnibus;
import team.com.ibus.service.OnibusService;

public class ListarOnibus extends AppCompatActivity {

    ProgressDialog pdialog;
    Context context;
    ListView listViewOnibus;
    Onibus[] onibusList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_onibus);

        context = this;

        try{

            // invoking the OnibusWSAsync AsyncTask


            OnibusWSAsync onibusWSAsync = new OnibusWSAsync(context,"buscarOnibus");
            onibusWSAsync.execute();



        }catch (Exception ex){

            Toast.makeText(ListarOnibus.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        listViewOnibus = (ListView) findViewById(R.id.listview_listarOnibus);

        listViewOnibus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) view.findViewById(R.id.tv_item_id_onibus);
                TextView tvPlaca= (TextView) view.findViewById(R.id.tv_item_placa_onibus);
                TextView tvCor= (TextView) view.findViewById(R.id.tv_item_cor_onibus);

                Bundle bundle = new Bundle();
                bundle.putInt("id",Integer.parseInt(tvId.getText().toString()));
                bundle.putString("placa_onibus",tvPlaca.getText().toString());
                bundle.putString("cor_onibus",tvCor.getText().toString());


                Intent intent = new Intent(context, CadastrarOnibus.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        listViewOnibus.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //deletar o onibus aqui.

                TextView tvId = (TextView) view.findViewById(R.id.tv_item_id_onibus);
                TextView tvPlaca= (TextView) view.findViewById(R.id.tv_item_placa_onibus);
                TextView tvCor= (TextView) view.findViewById(R.id.tv_item_cor_onibus);

                final int idOnibus = Integer.parseInt(tvId.getText().toString());
                final String placa = tvPlaca.getText().toString();
                final String cor = tvCor.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Excluir Ônibus")
                        .setMessage("Você tem certeza que quer excluir o ônibus de placa : " + placa + " e cor : " + cor+" ?")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Sim, Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Gson gson = new Gson();
                                Onibus onibus = new Onibus(idOnibus,placa,cor);

                                OnibusWSAsync onibusWSAsync = new OnibusWSAsync(context,"deleteOnibus",onibus);
                                onibusWSAsync.execute();

                                //fazer alguma coisa com o retorno

                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
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

        OnibusWSAsync(Context context, String METODO) {

            this.METODO = METODO;
            gson = new Gson();
            this.onibus = onibus;
            NAMESPACE = context.getResources().getString(R.string.namespace_server);
            URL = "http://" + context.getResources().getString(R.string.ip_server) + ":8080/WebServiceiBus/services/OnibusDAO?wsdl";
            onibusJson = gson.toJson(onibus);
            this.context = context;
        }

        OnibusWSAsync(Context context,String METODO, Onibus onibus) {

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


            if(METODO == "buscarOnibus"){

                try {


                    SoapObject buscarOnibus = new SoapObject(NAMESPACE, METODO);

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(buscarOnibus);

                    envelope.implicitTypes = true;

                    HttpTransportSE httpTransport = new HttpTransportSE(URL);
                    httpTransport.call("uri" + METODO, envelope);

                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    retornoWS = response.toString();

                    onibusList = gson.fromJson(retornoWS,Onibus [] .class);

                } catch (final Exception e) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Erro : " + e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }, 1);

                }


            }else{

                try{

                SoapObject deletarOnibus = new SoapObject(NAMESPACE, METODO);
                deletarOnibus.addProperty("objOnibus", onibusJson);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(deletarOnibus);

                envelope.implicitTypes = true;

                HttpTransportSE httpTransport = new HttpTransportSE(URL);

                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                retornoWS = response.toString();

                if (retornoWS == "true") {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Onibus excluído com sucesso", Toast.LENGTH_LONG).show();

                        }
                    }, 0);

                } else {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Onibus excluído com sucesso", Toast.LENGTH_LONG).show();
                            //Toast.makeText(context, "Ocorreu algum erro. Tente novamente daqui alguns segundos", Toast.LENGTH_LONG).show();

                        }
                    }, 0);

                }

            } catch (final Exception e) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(context, "Erro : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }, 1);
            }

            }


            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pDialog.dismiss();

            AdapterListViewOnibus adapterListViewOnibus = new AdapterListViewOnibus(context,onibusList);
            listViewOnibus.setAdapter(adapterListViewOnibus);

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(context);

             if (METODO == "buscarOnibus")
                pDialog.setMessage("Buscando Ônibus");
            else
                pDialog.setMessage("Deletando...");

            pDialog.show();

        }

    }

}


