package team.com.ibus;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


/**
 * Created by Yuri on 06/12/2016.
 */

public class ConsomeWebService extends AsyncTask<Void,Void,Void> {

    private Context context;
    private ProgressDialog pDialog;
    private String msgPreExecute;
    private String NOME_METODO;
    private final String NAMESPACE = "http://DAO.ibus.com.team";
    private final String URL = "192.168.1.4:8080/WebServiceiBus/services/OnibusDAO?wsdl";
    private SoapObject request;
    private String respostaWS;


    public ConsomeWebService(Context context, ProgressDialog pDialog, String msgPreExecute, String NOME_METODO){

        this.context = context;
        this.pDialog = pDialog;
        this.msgPreExecute = msgPreExecute;
        this.NOME_METODO = NOME_METODO;

    }

    public String consomeWS(){

        doInBackground();

        //mandar retornar o Gson do método.

        return null;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {

            request = new SoapObject(NAMESPACE, NOME_METODO);
            //adiciona a propriedade conforme quem estiver consumindo
            //talvez tenha que fazer um para cada DAO ou fazer uns métodos aqui de switch
            //request.addProperty("Celsius", tempValue);

            SoapObject inserirOnibus = new SoapObject(NAMESPACE,NOME_METODO);
            //inserir as propriedades de acordo com quem estiver chamando essa classe
            //inserirOnibus.addProperty("",onibusJson);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(inserirOnibus);

            envelope.implicitTypes = true;

            HttpTransportSE httpTransport = new HttpTransportSE(URL);
            httpTransport.call("uri" + NOME_METODO, envelope);

            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            respostaWS = response.toString();

        } catch (final Exception e) {

            respostaWS = e.getMessage();

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
        pDialog.setMessage(msgPreExecute);

    }

}
