package team.com.ibus.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Arrays;
import java.util.List;

import team.com.ibus.Dominio.PontoDeParada;
import team.com.ibus.Dominio.Posicao;
import team.com.ibus.Dominio.Rota;
import team.com.ibus.R;
import team.com.ibus.adapter.AdapterListViewPontoParada;
import team.com.ibus.adapter.AdapterListViewRota;
import team.com.ibus.service.OnibusService;
import team.com.ibus.service.RotaService;

public class CadastrarRota extends AppCompatActivity {

    ProgressDialog pdialog;
    String objRotaJson;
    Bundle bundle;
    Context context;
    RotaService rotaService;
    TextInputLayout tilOrigem;
    TextInputLayout tilDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_rota);

        rotaService = new RotaService(context,pdialog);
        bundle = getIntent().getExtras();

        tilOrigem = (TextInputLayout) findViewById(R.id.til_ponto_origem_rota);
        tilDestino = (TextInputLayout) findViewById(R.id.til_ponto_destino_rota);

        Spinner spinner = (Spinner)findViewById(R.id.spinner_escolher_rota_cadastrar_trajeto);

        //preenche o spinner com os dados vindos do banco.
        AdapterListViewRota adapterListViewRota = new AdapterListViewRota(context,rotaService.buscarRota());
        spinner.setAdapter(adapterListViewRota);


        //pegar o nome de todos os pontos de parada cadastrados

        Button btCadastrar = (Button) findViewById(R.id.bt_cadastrar_rota);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean cadastrar = true;
                tilOrigem = (TextInputLayout) findViewById(R.id.til_ponto_origem_rota);
                tilDestino = (TextInputLayout) findViewById(R.id.til_ponto_destino_rota);

                String origem = tilOrigem.getEditText().getText().toString();
                String destino = tilDestino.getEditText().getText().toString();

                if(origem != "IFCE" && origem != "Praça do Leão"){
                    tilOrigem.setError("Ponto de Origem inválido");
                    cadastrar = false;

                }else{
                    tilOrigem.setErrorEnabled(false);
                }

                if(destino != "IFCE" && destino != "Praça do Leão"){
                    tilDestino.setError("Ponto de Origem inválido");
                    cadastrar = false;

                }else{

                    tilDestino.setErrorEnabled(false);
                }

                if(cadastrar){

                    Posicao posicaoPracaLeao = new Posicao(-4.970186 , -39.015866);
                    Posicao posicaoIFCE = new Posicao(-4.970186 , -39.015866);

                    Rota rota;
                    if(origem == "IFCE")
                        rota = new Rota(posicaoIFCE,posicaoPracaLeao,null);
                    else
                        rota = new Rota(posicaoPracaLeao,posicaoIFCE,null);

                    Gson gson = new Gson();
                    objRotaJson = gson.toJson(rota);


                    try{

                        rotaService.insertRota(rota);
                        String retornoWS = "";

                        //se for nulo é porque não veio dados de oura activity, então foi chamado pela tela de cadastro.
                        if(bundle != null)
                            retornoWS = rotaService.insertRota(rota);
                        else
                            retornoWS = rotaService.updateRota(rota);

                        //TODO: fazer algo com o retorno do service...


                    }catch (Exception ex){

                        Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final boolean[] checkedPontosParadas = new boolean[]{
                        false, // Anexo
                        false, // Seminário
                        false, // Igreja Adventista
                        false, // Churrascaria
                        false // Rodoviária

                };

                AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarRota.this);

                // String array for alert dialog multi choice items
                String[] pontosParadaArray = new String[]{
                        "Anexo",
                        "Seminário",
                        "Igreja Adventista",
                        "Churrascaria",
                        "Rodoviária"
                };

                // Boolean array for initial selected items

                // Convert the color array to list
                final List<String> pontosParadaList = Arrays.asList(pontosParadaArray);

                builder.setMultiChoiceItems(pontosParadaArray, checkedPontosParadas, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        // Update the current focused item's checked status
                        checkedPontosParadas[which] = isChecked;

                        // Get the current focused item
                        String currentItem = pontosParadaList.get(which);

                    }
                });

                // Specify the dialog is not cancelable
                builder.setCancelable(false);

                // Set a title for alert dialog
                builder.setTitle("Escolha os pontos de parada ");

                // Set the positive/yes button click listener
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //guarda numa lista para mandar pro webservice

                    }
                });

                // Set the negative/no button click listener
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the negative button
                    }

                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        });
    }



}