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
import team.com.ibus.R;

/**
 * Created by Yuri on 10/12/2016.
 */

public class OnibusService {

    protected Context context;
    private String NAMESPACE;
    private String URL;
    private ProgressDialog pDialog;

    public OnibusService(Context context, ProgressDialog pDialog){

        this.context = context;
        this.pDialog = pDialog;
        NAMESPACE = context.getResources().getString(R.string.namespace_server);
        URL = "http://" + context.getResources().getString(R.string.ip_server) + ":8080/WebServiceiBus/services/OnibusDAO?wsdl";
    }

    public String insertOnibus(Onibus onibus) {

        OnibusWSAsync onibusWSAsync = new OnibusWSAsync("insertOnibus", onibus);
        return onibusWSAsync.callInsertOnibus();

    }

    public Onibus [] buscarOnibus(){

        OnibusWSAsync onibusWSAsync = new OnibusWSAsync("buscarOnibus");
        String listaOnibusJson = onibusWSAsync.callBuscarOnibus();

        Gson gson = new Gson();
        Onibus [] listaOnibus = gson.fromJson(listaOnibusJson, Onibus[].class);

        return listaOnibus;
    }

    public String updateOnibus(Onibus onibus){

        OnibusWSAsync onibusWSAsync = new OnibusWSAsync("updateOnibus", onibus);
        return onibusWSAsync.callUpdateOnibus();
    }

    public String deleteOnibus(Onibus onibus){

        OnibusWSAsync onibusWSAsync = new OnibusWSAsync("deleteOnibus", onibus);
        return onibusWSAsync.callDeleteOnibus();
    }

    private class OnibusWSAsync extends AsyncTask<Void, Void, Void> {

        private String METODO;
        private Gson gson;
        private Onibus onibus;
        private String retornoWS = "";

        OnibusWSAsync(String METODO) {

            this.METODO = METODO;
            gson = new Gson();
        }

        OnibusWSAsync(String METODO, Onibus onibus) {

            this.METODO = METODO;
            gson = new Gson();
            this.onibus = onibus;
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

            if (METODO == "insertOnibus")
                pDialog.setMessage("Cadastrando...");
            else if (METODO == "buscarOnibus")
                pDialog.setMessage("Buscando Ônibus");
            else if (METODO == "updateOnibus")
                pDialog.setMessage("Atualizando...");
            else
                pDialog.setMessage("Deletando...");

            pDialog.show();

        }

        private String callInsertOnibus() {


            Thread thread = new Thread(new Runnable() {

                public void run() {
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

                }
            });

            thread.start();

            return retornoWS;
        }

        private String callBuscarOnibus() {

            Thread thread = new Thread(new Runnable() {

                public void run() {
                    try {


                        SoapObject buscarOnibus = new SoapObject(NAMESPACE, METODO);

                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope.setOutputSoapObject(buscarOnibus);

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

                }
            });

            thread.start();

            return retornoWS;
        }

        private String callUpdateOnibus() {


            final String onibusJson = gson.toJson(onibus);

            Thread thread = new Thread(new Runnable() {

                public void run() {
                    try {

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
            });

            thread.start();

            return retornoWS;

        }

        private String callDeleteOnibus() {

            final String onibusJson = gson.toJson(onibus);
            Thread thread = new Thread(new Runnable() {

                public void run() {
                    try {

                        SoapObject deletarOnibus = new SoapObject(NAMESPACE, METODO);
                        deletarOnibus.addProperty("objOnibus", onibusJson);

                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope.setOutputSoapObject(deletarOnibus);

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
            });

            thread.start();

            return retornoWS;

        }

    }
}
