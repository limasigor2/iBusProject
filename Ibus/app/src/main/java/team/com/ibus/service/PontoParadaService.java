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

import team.com.ibus.Dominio.Onibus;
import team.com.ibus.Dominio.PontoDeParada;
import team.com.ibus.R;

/**
 * Created by Yuri on 10/12/2016.
 */

public class PontoParadaService {

    protected Context context;
    private String NAMESPACE;
    private String URL;
    private ProgressDialog pDialog;

    public PontoParadaService(Context context, ProgressDialog pDialog) {

        this.context = context;
        this.pDialog = pDialog;
        NAMESPACE = context.getResources().getString(R.string.namespace_server);
        URL = "http://" + context.getResources().getString(R.string.ip_server) + ":8080/WebServiceiBus/services/PontoParadaDAO?wsdl";
    }

    public String insertPontoParada(PontoDeParada pontoDeParada) {

        PontoParadaWSAsync onibusWSAsync = new PontoParadaWSAsync("insertPontoParada", pontoDeParada);
        return onibusWSAsync.callInsertPontoParada();

    }

    public PontoDeParada[] buscarPontoParada() {

        PontoParadaWSAsync onibusWSAsync = new PontoParadaWSAsync("buscarPontoParada");
        String listaPontoParadaJson = onibusWSAsync.callBuscarPontoParada();

        Gson gson = new Gson();
        PontoDeParada [] listaPontoParada = gson.fromJson(listaPontoParadaJson, PontoDeParada[].class);

        return listaPontoParada;
    }

    public String updatePontoParada(PontoDeParada pontoDeParada) {

        PontoParadaWSAsync onibusWSAsync = new PontoParadaWSAsync("updatePontoParada", pontoDeParada);
        return onibusWSAsync.callUpdatePontoParada();
    }

    public String deletePontoParada(PontoDeParada pontoDeParada) {

        PontoParadaWSAsync onibusWSAsync = new PontoParadaWSAsync("deletePontoParada", pontoDeParada);
        return onibusWSAsync.callDeletePontoParada();
    }

    private class PontoParadaWSAsync extends AsyncTask<Void, Void, Void> {

        private String METODO;
        private Gson gson;
        private PontoDeParada pontoDeParada;

        PontoParadaWSAsync(String METODO) {

            this.METODO = METODO;
            gson = new Gson();
        }

        PontoParadaWSAsync(String METODO, PontoDeParada pontoDeParada) {

            this.METODO = METODO;
            gson = new Gson();
            this.pontoDeParada = pontoDeParada;
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

            if (METODO == "insertPontoParada")
                pDialog.setMessage("Cadastrando...");

            else if (METODO == "buscarPontoParada")
                pDialog.setMessage("Buscando pontos de parada");

            else if (METODO == "updatePontoParada")
                pDialog.setMessage("Atualizando...");

            else
                pDialog.setMessage("Deletando...");

            pDialog.show();

        }

        private String callInsertPontoParada() {

            String retornoWS = "";

            String pontoParadaJson = gson.toJson(pontoDeParada);

            try {


                SoapObject inserirPontoParada = new SoapObject(NAMESPACE, METODO);
                inserirPontoParada.addProperty("objPontoParada", pontoParadaJson);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(inserirPontoParada);

                envelope.implicitTypes = true;

                HttpTransportSE httpTransport = new HttpTransportSE(URL);
                httpTransport.call("uri" + METODO, envelope);

                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                retornoWS = response.toString();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(context, "Ponto de Parada cadastrado com sucesso : ", Toast.LENGTH_LONG).show();

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

            return retornoWS;

        }

        private String callBuscarPontoParada() {

            String retornoWS = "";

            try {

                SoapObject buscarPontoParada = new SoapObject(NAMESPACE, METODO);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(buscarPontoParada);

                envelope.implicitTypes = true;

                HttpTransportSE httpTransport = new HttpTransportSE(URL);
                httpTransport.call("uri" + METODO, envelope);

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

        private String callUpdatePontoParada() {

            String retornoWS = "";
            String pontoParadaJson = gson.toJson(pontoDeParada);

            try {
                SoapObject inserirOnibus = new SoapObject(NAMESPACE, METODO);
                inserirOnibus.addProperty("objPontoParada", pontoParadaJson);

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
                        Toast.makeText(context, "Ponto de Parada atualizado com sucesso", Toast.LENGTH_LONG).show();

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

            return retornoWS;
        }

        private String callDeletePontoParada() {

            String retornoWS = "";
            String pontoParadaJson = gson.toJson(pontoDeParada);

            try {

                SoapObject deletarPontoParada = new SoapObject(NAMESPACE, METODO);
                deletarPontoParada.addProperty("objPontoParada", pontoParadaJson);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(deletarPontoParada);

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

                            Toast.makeText(context, "Ponto de parada excluído com sucesso", Toast.LENGTH_LONG).show();

                        }
                    }, 0);

                } else {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(context, "Ponto de Parada excluído com sucesso", Toast.LENGTH_LONG).show();
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
