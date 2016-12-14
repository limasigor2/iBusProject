package team.com.ibus.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
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
import team.com.ibus.Dominio.PontoDeParada;
import team.com.ibus.R;
import team.com.ibus.service.PontoParadaService;

public class CadastrarPontoParada extends AppCompatActivity {

    Context context;
    ProgressDialog pdialog;
    Bundle bundle;
    int idPontoParada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_ponto_parada);

        context = this;

        final PontoParadaService pontoParadaService = new PontoParadaService(context,pdialog);

        bundle = getIntent().getExtras();

        Button btCadastrar = (Button) findViewById(R.id.bt_cadastrar_ponto_parada);

        TextInputLayout tilNome = (TextInputLayout) findViewById(R.id.til_nome_ponto_parada);
        TextInputLayout tilEndereceo = (TextInputLayout) findViewById(R.id.til_endereco_ponto_parada);
        TextInputLayout tilDescricao = (TextInputLayout) findViewById(R.id.til_descricao_ponto_parada);

        if(bundle != null){

            idPontoParada = bundle.getInt("id_ponto_parada");
            tilNome.getEditText().setText(bundle.getString("nome_ponto_parada"));
            tilEndereceo.getEditText().setText(bundle.getString("endereco_ponto_parada"));
            tilDescricao.getEditText().setText(bundle.getString("descricao_ponto_parada"));
            btCadastrar.setText("Atualizar");
        }


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputLayout tilNome = (TextInputLayout) findViewById(R.id.til_nome_ponto_parada);
                TextInputLayout tilEndereceo = (TextInputLayout) findViewById(R.id.til_endereco_ponto_parada);
                TextInputLayout tilDescricao = (TextInputLayout) findViewById(R.id.til_descricao_ponto_parada);

                //faz verificações
                String nome = tilNome.getEditText().getText().toString();
                String endereco = tilEndereceo.getEditText().getText().toString();
                String descricao = tilDescricao.getEditText().getText().toString();
                boolean cadastrar = true;


                if(nome.length() < 3 ){
                    cadastrar = false;
                    tilNome.setError("Quantidade de caracteres inválidos. Ex: UFC");
                }else{
                    tilNome.setErrorEnabled(false);
                }

                if(endereco.length() < 10){
                    cadastrar = false;
                    tilEndereceo.setError("Endereço inválido. Ex : Rua José Caetano, 200.");
                }else{

                    tilEndereceo.setErrorEnabled(false);
                }

                if(descricao.length() < 4){
                    cadastrar = false;
                    tilDescricao.setError("Descrição muito curta. Ex : Próximo à igreja Adventista.");
                }else{

                    tilDescricao.setErrorEnabled(false);
                }

                if(cadastrar){

                    PontoDeParada pontoDeParada = new PontoDeParada(idPontoParada, nome,endereco,descricao,null);

                    String retornoService;

                    try{

                        //se for nulo é porque não veio dados de oura activity, então foi chamado pela tela de cadastro.
                        if(bundle != null)
                            retornoService = pontoParadaService.insertPontoParada(pontoDeParada);
                        else
                            retornoService = pontoParadaService.updatePontoParada(pontoDeParada);


                    }catch (Exception ex){

                        Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }

            }
        });


    }

}
