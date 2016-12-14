package team.com.ibus.Activity;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import team.com.ibus.R;
import team.com.ibus.Util;


public class CadastrarUsuario extends AppCompatActivity {

    Util util = new Util();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        EditText nome = (EditText) findViewById(R.id.til_nome);
        validaNome(nome);

        EditText login = (EditText) findViewById(R.id.til_login);
        validaLogin(login);

        EditText senha = (EditText) findViewById(R.id.til_senha);
        validaSenha(senha);

        EditText confirmacaoSenha = (EditText) findViewById(R.id.til_confirmar_senha);
        comparaSenha(senha, confirmacaoSenha);

        Button btCadastrar = (Button) findViewById(R.id.bt_cadastrar_usuario);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cadastra usuário


            }
        });

        btCadastrar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //exibe o layout adicional para o Funcionario

                return true;
            }
        });


    }

    private void validaNome(EditText et) {
        //et.setErrorEnabled(true);
        String nome = et.getText().toString();

        if (nome.length() < 3) {
            et.setError("Acho que ninguém tem um nome tao curto! Coloca de novo, por favor");
        }
        if (nome.contains("1") || nome.contains("2") || nome.contains("3") || nome.contains("4")
                || nome.contains("5") || nome.contains("6") || nome.contains("7") || nome.contains("8")
                || nome.contains("9") || nome.contains("0")) {
            et.setError("Um nome com numero, acho que nao existe hein!");
        }
        if(nome.contains("!") || nome.contains("!") || nome.contains("!") || nome.contains("!") || nome.contains("!")){
            et.setError("Acho que você digitou algum caractere errado, tente outra vez!");
        }
    }

    private void validaLogin(EditText et){
        String login = et.getText().toString();
        if(login.length() < 5){
            et.setError("Que tal um login um pouco maior?");
        }
        //Que login legal
    }

    private void validaSenha(EditText et){
        String senha = et.getText().toString();

        if(senha.length() < 6)
            et.setError("Senha curta, sei que você consegue pensar em algo mais seguro");
        if(util.soTemLetra(senha))
            et.setError("Só tem letra, tenta colocar numeros também!");
        //senha ótima
    }

    private void comparaSenha(EditText et1, EditText et2){
        if(et1.getText().toString() == et2.getText().toString())
            et2.setError("As senhas não são iguais, tenta de novo");
    }

}

