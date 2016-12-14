package team.com.ibus.Activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import team.com.ibus.R;

public class CadastrarTrajeto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_trajeto);

        Spinner spinnerOnibus = (Spinner) findViewById(R.id.spinner_escolher_onibus_cadastrar_trajeto);
        Spinner spinnerRota = (Spinner) findViewById(R.id.spinner_escolher_rota_cadastrar_trajeto);

        //preencher os spinners aqui

        Button btCadastrarTrajeto = (Button) findViewById(R.id.bt_cadastrar_trajeto);

        btCadastrarTrajeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputLayout tilSaidaPrevista = (TextInputLayout) findViewById(R.id.til_ponto_origem_rota);
                TextInputLayout tilChegadaPrevista = (TextInputLayout) findViewById(R.id.til_chegada_prevista);

                boolean cadastrar = true;
                String saidaPrevista = tilSaidaPrevista.getEditText().getText().toString();
                String chegadaPrevista = tilChegadaPrevista.getEditText().getText().toString();

                Pattern regexHorarios = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
                Matcher matcherHorarios = regexHorarios.matcher(saidaPrevista);


                if(!matcherHorarios.matches()){

                    cadastrar = false;
                    tilSaidaPrevista.setError("Formato da hora inválido. Ex: 13:30");
                }else{

                    tilSaidaPrevista.setErrorEnabled(false);
                }

                matcherHorarios = regexHorarios.matcher(chegadaPrevista);

                if(!matcherHorarios.matches()){
                    cadastrar = false;
                    tilSaidaPrevista.setError("Formato da hora inválido. Ex: 13:50");

                }else{

                    tilChegadaPrevista.setErrorEnabled(false);
                }

                if(cadastrar){

//                    onibus = new Onibus(placa,cor);
//
//                    //serialização do objeto
//                    Gson gson = new Gson();
//                    onibusJson = gson.toJson(onibus);
//
//                    //objeto pronto para ser enviado pelo web service
//
//                    try{
//
//                        // invoking the OnibusWSAsync AsyncTask
//                        CadastrarOnibus.OnibusWSAsync onibusWs = new CadastrarOnibus.OnibusWSAsync();
//                        onibusWs.execute();
//
//                    }catch (Exception ex){
//
//                        Toast.makeText(CadastrarOnibus.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//                    }

                    Toast.makeText(CadastrarTrajeto.this, "Cadastrar a rota", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
