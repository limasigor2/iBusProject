package team.com.ibus.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import team.com.ibus.Dominio.Rota;
import team.com.ibus.R;

/**
 * Created by Yuri on 10/12/2016.
 */

public class RotaService {

    protected Context context;
    private String NAMESPACE;
    private String URL;
    private ProgressDialog pDialog;

    public RotaService(Context context, ProgressDialog pDialog){

        this.context = context;
        this.pDialog = pDialog;
        NAMESPACE = context.getResources().getString(R.string.namespace_server);
        URL = "http://" + context.getResources().getString(R.string.ip_server) + ":8080/WebServiceiBus/services/RotaDAO?wsdl";
    }

    public String insertRota(Rota rota) {

        RotaWSAsync rotaWSAsync = new RotaWSAsync("insertRota", rota);
        return rotaWSAsync.callInsertRota();

    }

    public Rota [] buscarRota(){

        RotaWSAsync rotaWSAsync = new RotaWSAsync("buscarRota");

        String listaRotaJson = rotaWSAsync.callBuscarRota();

        Gson gson = new Gson();
        Rota [] listaRotas = gson.fromJson(listaRotaJson, Rota[].class);

        return listaRotas;
    }

    public String updateRota(Rota rota){

        RotaWSAsync rotaWSAsync = new RotaWSAsync ("updateRota", rota);
        return rotaWSAsync.callUpdateRota();
    }

    public String deleteRota(Rota rota){

        RotaWSAsync rotaWSAsync = new RotaWSAsync("deleteRota", rota);
        return rotaWSAsync.callDeleteRota();
    }

    private class RotaWSAsync extends AsyncTask<Void, Void, Void> {

        private String METODO;
        private Gson gson;
        private Rota rota;

        RotaWSAsync(String METODO){

            this.METODO = METODO;
            gson = new Gson();
        }

        RotaWSAsync(String METODO, Rota rota){

            this.METODO = METODO;
            gson = new Gson();
            this.rota = rota;
        }

        @Override
        protected Void doInBackground(Void... params) {

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

            if(METODO == "insertRota")
                pDialog.setMessage("Cadastrando...");
            else if(METODO == "buscarRota")
                pDialog.setMessage("Buscando Rota");
            else if(METODO == "updateRota")
                pDialog.setMessage("Atualizando...");
            else
                pDialog.setMessage("Deletando...");

            pDialog.show();

        }

        private String callInsertRota(){

            String retornoWS = "";

            String onibusRota = gson.toJson(rota);

            try {

                SoapObject inserirOnibus = new SoapObject(NAMESPACE,METODO);
                inserirOnibus.addProperty("objRota",onibusRota);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(inserirOnibus);

                envelope.implicitTypes = true;

                HttpTransportSE httpTransport = new HttpTransportSE(URL);
                httpTransport.call("uri"+METODO, envelope);

                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                retornoWS = response.toString();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(context, "Rota cadastrado com sucesso : ", Toast.LENGTH_LONG).show();

                    }
                },0);

            } catch (final Exception e) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(context, "Erro : " + e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }, 500);

            }

            return retornoWS;

        }

        private String callBuscarRota(){

            String retornoWS = "";

            try {

                SoapObject buscarRota = new SoapObject(NAMESPACE,METODO);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(buscarRota);

                envelope.implicitTypes = true;

                HttpTransportSE httpTransport = new HttpTransportSE(URL);
                httpTransport.call("uri"+METODO, envelope);

                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                retornoWS = response.toString();

            } catch (final Exception e) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(context, "Erro : " + e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }, 1);
            }

            return retornoWS;
        }

        private String callUpdateRota(){

            String retornoWS = "";
            String rotaJson = gson.toJson(rota);

            try {

                SoapObject inserirRota = new SoapObject(NAMESPACE,METODO);
                inserirRota.addProperty("objRota",rotaJson);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(inserirRota);

                envelope.implicitTypes = true;

                HttpTransportSE httpTransport = new HttpTransportSE(URL);
                httpTransport.call("uri" + METODO, envelope);

                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                retornoWS = response.toString();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Rota atualizado com sucesso", Toast.LENGTH_LONG).show();

                    }
                },0);

            } catch (final Exception e) {

                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(context, "Erro : " + e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }, 500);
            }

            return retornoWS;
        }

        private String callDeleteRota() {

            String retornoWS = "";
            String rotaJson = gson.toJson(rota);

            try {

                SoapObject deletarRota = new SoapObject(NAMESPACE, METODO);
                deletarRota.addProperty("objRota", rotaJson);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(deletarRota);

                envelope.implicitTypes = true;

                HttpTransportSE httpTransport = new HttpTransportSE(URL);
                httpTransport.call("uri" + METODO, envelope);

                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                retornoWS = response.toString();

                if (retornoWS == "true") {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Rota excluído com sucesso", Toast.LENGTH_LONG).show();

                        }
                    }, 0);

                } else {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Rota excluído com sucesso", Toast.LENGTH_LONG).show();
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

            return retornoWS;
        }

    }

}
